package controller;

import model.*;
import view.GameUI;
import view.GraphicalEngine;
import view.InputsManager;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * A class representing the kernel. This is the class that will make the link between all the engines and the user of
 * the framework.
 */
public abstract class GameApp {
    /**List of entities currently on the set*/
    public static List<Entity> components;
    /**Keyboard and mouse input manager*/
    private InputsManager inputsManager;
    /**Entity Collision Manager*/
    private static  CollisionManager collisionManager;
    /**Game physics engine*/
    private final PhysicalEngine physicalEngine;
    /**Game graphics engine*/
    private final GraphicalEngine graphicalEngine;
    /**the entity that is in motion*/
    public static Entity activeEntity;
    /**artificial intelligence engine*/
    private AIEngine aiEngine;
    /**The width of the game board*/
    public static int width;
    /**The length of the game board*/
    public static int length;
    /**The detected command to execute*/
    private static Boolean commandDetected;

    private static KeyEvent keyEvent;
    protected static GameUI gameUI;
    public static int[] grid;
    public static boolean startGame = false;

    private static GameStatus gameStatus = new GameStatus();

    /**
     * A simple setter
     * @param gameStatus the actual status of the game
     */
    public static void setGameStatus(GameStatus gameStatus) {
        GameApp.gameStatus = gameStatus;
    }

    /**
     * A simple getter
     * @return The actual game status
     */
    public static GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * A simple setter
     * @param FPS The number of frames per second
     */
    public static void setFPS(int FPS) {
        GameApp.FPS = FPS;
    }

    /**
     * A simple getter
     * @return The active entity in the game
     */
    public static Entity getActiveEntity(){return activeEntity;}

    /**
     * A simple setter
     * @param delay the movement delay
     */
    public static void setDelay(int delay) {
        GameApp.delay = delay;
    }

    /**
     * A simple getter
     * @return The movement delay
     */
    public static int getDelay(){return delay;}

    /**
     * Number of frames per second
     */
    public static int FPS;

    /**
     * The movement delay
     */
    public static int delay;

    public static int unitSize;

    /**
     * A simple setter
     * @param _unitSize The new unitSize
     */
    public void setUnitSize(int _unitSize){
        unitSize = _unitSize;
    }

    /**
     * A simple getter
     * @return The actual unit size
     */
    public int getUnitSize(){
        return unitSize;
    }

    /**
     * Initializes all game entities
     */
    public abstract void initEntities();

    public abstract void setManagers();

    public void drawGrid(int gridSize){
        //Quantités de cellules crées (ou de lignes tracées) en longeur ou largeur
        int nbCells =  Math.max(length, width) / unitSize;

        //Tableau qui stocke les coordonnées des lignes de la grille
        grid = new int[nbCells];

        for (int i = 0; i < nbCells; i++) {
            grid[i] = (i+1)*unitSize;
        }
        graphicalEngine.drawGrid(gridSize);
    }

    /**
     * A simple setter
     * @param inputsManager The new inputsManager
     */
    public void setInputsManager(InputsManager inputsManager) {
        this.inputsManager = inputsManager;
    }

    /**
     * A simple setter
     * @param aiEngine The new artificial intelligence manager
     */
    public void setAiEngine(AIEngine aiEngine) {
        this.aiEngine = aiEngine;
    }

    /**
     * A simple getter of the initial active entity
     * @param activeEntity1 the initial active entity
     */
    public void setActiveEntity(Entity activeEntity1) {
        activeEntity = activeEntity1;
    }

    /**
     * A simple collision manager setter
     * @param collisionManager the collision manager
     */
    public void setCollisionManager(CollisionManager collisionManager) {
        GameApp.collisionManager = collisionManager;
    }

    /**
     * The initial constructor of the GameApp class
     * @param width1 the width of the window, this constructor must take it as a parameter because it is important for
     *               the management of each part
     * @param length1 la longueur de la fenêtre, ce constructeur doit la prendre en paramètre parce que c'est important
     *                pour le gestion de chaque partie
     * @throws Exception dans le cas où inputsManager, aiEngine, activeEntity ou collisionManager ne sont pas tous
     * définis, une exception sera levée à l'excécution du jeu
     */
    public GameApp(int width1, int length1) throws Exception {
        width = width1;
        length = length1;
        components = Collections.synchronizedList(new ArrayList<>());
        gameUI =  new GameUI();
        gameUI.mainMenu("SNAKE");
        this.physicalEngine = new PhysicalEngine();
        this.graphicalEngine = new GraphicalEngine(width, length);
        this.setManagers();
        //command = null;
        commandDetected = false;
        FPS = 1;
    }

    /**
     * The main function of this class, it triggers the part of the game, then follows the evolution of the states of
     * the parts and the keyboard inputs to change the behaviors of the components
     * @throws Exception dans le cas où inputsManager, aiEngine, activeEntity ou collisionManager ne sont pas tous
     * définis, une exception sera levée à l'excécution du jeu
     */
    public void start() throws Exception {
        /*
        On commence par vérifier que tous les paramètres sont en place
         */
        if (this.inputsManager == null) {
            throw new Exception("inputsManager ne doit pas etre null");
        }
        if (this.aiEngine == null) {
            throw new Exception("aiEngine ne doit pas etre null");
        }
        if (activeEntity == null) {
            throw new Exception("actifEntity ne doit pas etre null");
        }
        if (collisionManager == null) {
            throw new Exception("collisionManager ne doit pas etre null");
        }

        while(!AIEngine.endOfTheGame) {
            //On récupère toutes les nouvelles commandes du joueur
            if(commandDetected && activeEntity.getX() % unitSize == 0 && activeEntity.getY() % unitSize == 0) {
                //this.inputsManager.manageInputs(command);
                inputsManager.manageInputs(keyEvent);
                commandDetected = false;
            }
            //La commande d'avant modifie tous les attributs Movement des entitiés dont le mouvement doit être changé suite à un input
            move();

            Thread.sleep(delay);

            Boolean bo = commandDetected;
            KeyEvent com = keyEvent;

            //commandDetected = true;
            //Après chaque itération, le moteur IA doit mettre à jour ses valeurs pour que des évenements comme la fin de partie soient détectés
            this.aiEngine.update();

            //Après chaque coup on doit gérer toutes les collisions qu'il y'a
//            collisionManager.manageCollisions();

        }
    }

    /**
     * Browses the list of entities, and asks the graphics engine to trigger the movements of those whose isActive
     * parameter is equal to true
     */
    private synchronized void move() throws Exception {
        ArrayList<Entity> copyComponents = new ArrayList<>(components);
        for (Entity entity : copyComponents) {
            if (entity.isMoving()) {
                this.physicalEngine.move(entity);
                graphicalEngine.update(entity);
            }
        }
        Set<Entity> union = new HashSet<>();
        union.addAll(copyComponents);
        union.addAll(components);
        components.clear();
        components.addAll(union);
    }

    /**
     * Add an entity given as a parameter in the list of components
     * @param entity The entity to add
     */
    public static void addEntity(Entity entity) {
        components.add(entity);
    }

    //Elle ne manipule pas directement la liste components pour éviter problèmes de concurrence
    public static void addEntity(Entity entity, boolean isInitialComponent) {
        components.add(entity);
    }

    /**
     * Removes an entity from the entity list
     * @param entity The entity to add
     */
    public static void removeEntity(Entity entity) {
        components.remove(entity);

    }

    /**
     * The user function must call right after the constructor, it triggers all the movements
     */
    public void launch() throws Exception {
        //On met dans la liste components toutes les entités
        initEntities();
        //gameUI.launch(graphicalEngine, gameStatus);
        //On affiche toutes les entités sur l'écran
        graphicalEngine.initEntities(components);

        while (!startGame){
            Thread.sleep(0);
            //System.out.print(".");
        }
        this.graphicalEngine.setVisible(true);
        //On commence la boucle de traitement
        gameStatus.startStopwatch();
        start();
    }

    /**
     * Function used by the physics engine to notify that an entity must change position
     * @param entity the entity that changes position
     * @param newPosition the new position of the entity entity
     */
    public static void notifyNewPosition_Physical(Entity entity, ScreenPosition newPosition, Direction newDirection) {
        //met a jour le previous direction aussi
        //La direction de l'entité active est déjà changée lors de la détection de l'entrée clavier.
        //La changer à nouveau va changer le previous direction!
        if (!activeEntity.equals(entity)){
            entity.setDirection(newDirection);
        }else {
            for (int i = 0; i <grid.length; i++) {
                if ((newPosition.getxAxis()% grid[i]) == 0 &&(newPosition.getyAxis()% grid[i]) == 0){
                    //System.out.println("can turn");
                }
                if ((newPosition.getyAxis()% grid[i]) == 0){
                //    System.out.println("can turn up down");
                }
                if (newPosition.getxAxis() % width == unitSize){
                  //  System.out.println("turn");
                }

            }

        }
        entity.setPosition(newPosition);
    }

    /**
     * Defines the detected keyboard event
     * @param event The detected keyEvent
     */
    public static void setKeyEvent(KeyEvent event){
        commandDetected = true;
        keyEvent = event;
    }

    /**
     * Upon receipt of a Direction type keyboard input, this function takes care of changing the direction of the
     * active entity
     * @param direction The new direction
     */
    public static void setActiveEntityDirection(Direction direction) {
            activeEntity.setDirection(direction);
    }

    /**
     * Simple getter
     * @return The board width
     */
    public static int getBoardWidth() {
        return width;
    }

    /**
     * Simple getter
     * @return The board length
     */
    public static int getBoardLength() {
        return length;
    }

    /**
     * Function called to trigger the end of the game
     */
    public static void endTheGame() {
        gameStatus.stopStopwatch();
        AIEngine.endOfTheGame = true;
        String message = "GAME OVER!\nSCORE: " + gameStatus.getGameScore() + "\nTIME: " + gameStatus.getFormattedGameTime();
        gameUI.popUpMessage(message);
        gameUI.setVisible(true);
        System.exit(0);
    }

    /**
     * Simple getter
     * @return The collision manager
     */
    public static CollisionManager getCollisionManager() {
        return collisionManager;
    }

    /**
     * Updates game graphics
     * @param entity The entity that changes its parameters
     */
    public void updateGraphics(Entity entity){
        this.graphicalEngine.update(entity);
    }

}
