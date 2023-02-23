package SNAKE_GAME;

import controller.Direction;
import controller.GameApp;
import controller.Mouvement;
import controller.ProjectileMouvement;
import model.EShape;
import model.Entity;
import model.EntityParameters;
import view.Circle;

import java.awt.*;

public class TESTGame extends GameApp {
    int FPS = 1;
    int delay = 15 * FPS;
    static int nbCells = 40;
    int bodyLenght = 2;
    public static TESTSnake snake;
    static Entity apple;

    /**Fait appel au constructeur de GameApp pour mettre en place un plateau de jeu par défaut et configurer certains paramètres. Il dessinera aussi une grille.*/
    public TESTGame(int width, int length) throws Exception {
        super(width, length);
        setAnimationParameters(this.FPS, this.delay);
        setUnitSize(Math.max(getBoardLength(), getBoardWidth()) / nbCells);
        drawGrid(unitSize);
    }

    public void setManagers() {
        TESTSnakeAIEngine snakeAIEngine = new TESTSnakeAIEngine();
        snakeAIEngine.setGame(this);
        setInputsManager(new TESTSnakeInputManager());
        setAiEngine(snakeAIEngine);
        setCollisionManager(new TESTSnakeCollisionManager(snakeAIEngine));
    }

    public void setAnimationParameters(int FPS, int delay){
        setFPS(FPS);
        setDelay(delay);
    }


    public void initEntities() {

        int[] x = new int[getBoardLength()*getBoardWidth()];
        int[] y = new int[getBoardLength()*getBoardWidth()];

        Mouvement mvt = new TESTSnakeMouvement(Direction.RIGHT, FPS);
        TESTSnakeBodyShape shape = new TESTSnakeBodyShape(0, 5*unitSize, x, y);
        EntityParameters param = new EntityParameters(shape);
        snake = new TESTSnake(bodyLenght*unitSize,x, y,"SNAKE", param, mvt);
        addEntity(snake);
        this.setActiveEntity(snake);


        EShape shape1 = new Circle(3*unitSize, 2*unitSize, unitSize, unitSize, Color.RED);
        EntityParameters appleParameters = new EntityParameters(shape1);
        Mouvement appleMvt = new ProjectileMouvement(Direction.UP);
        appleMvt.setActive(false);
        apple = new Entity("APPLE", appleParameters, appleMvt);
        addEntity(apple, true);
    }

    public static void main(String[] args) throws Exception {
        TESTGame game = new TESTGame(500, 500);
        game.launch();
    }
}
