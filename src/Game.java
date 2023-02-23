import controller.Direction;
import controller.GameApp;
import controller.Mouvement;
import controller.ProjectileMouvement;
import model.EShape;
import model.Entity;
import model.EntityParameters;
import view.Circle;
import view.Rectangle;

/**Classe pour lancer un jeu de snake*/
public class Game extends GameApp {

    int FPS = 1;
    int delay = 10 * FPS;
    static int nbCells = 30;
    int bodyLenght = 3;
    static Snake snake;

    /**Fait appel au constructeur de GameApp pour mettre en place un plateau de jeu par défaut et configurer certains paramètres. Il dessinera aussi une grille.*/
    public Game(int width, int length) throws Exception {
        super(width, length);
        setAnimationParameters(this.FPS, this.delay);
        setUnitSize(Math.max(getBoardLength(), getBoardWidth()) / nbCells);
        drawGrid(unitSize);
    }

    public void setManagers() {
        SnakeAIEngine snakeAIEngine = new SnakeAIEngine();
        snakeAIEngine.setGame(this);
        setInputsManager(new SnakeInputsManager());
        setAiEngine(snakeAIEngine);
        setCollisionManager(new SnakeCollisionManager(snakeAIEngine));
    }

    public void setAnimationParameters(int FPS, int delay){
        setFPS(FPS);
        setDelay(delay);
    }

    public void initEntities() {

        int nbGridCells = (getBoardLength()/getUnitSize()) * (getBoardWidth()/getUnitSize());

        snake = new Snake(unitSize, bodyLenght, nbGridCells,5,5);

        for (int i = 0; i < snake.getBodyLenght(); i++) {
            addEntity(snake.getBodyPart(i), true);
        }
        this.setActiveEntity(snake.getBodyPart(0));

        EShape shape1 = new Circle(10*unitSize, 5*unitSize, unitSize, unitSize);
        EntityParameters appleParameters = new EntityParameters(shape1);
        Mouvement appleMvt = new ProjectileMouvement(Direction.UP);
        appleMvt.setActive(false);
        Entity apple = new Entity("APPLE", appleParameters, appleMvt);
        addEntity(apple, true);

        for (int i = snake.getBodyLenght() + 1; i < 200; i++){
            EShape s = new Rectangle(nbCells+10, nbCells+10, unitSize, unitSize);
            EntityParameters p = new EntityParameters(s);
            Mouvement m = new ProjectileMouvement(Direction.RIGHT);
            m.setActive(false);
            Entity entity = new Entity("SNAKE_HEAD", p, m);
            entity.setName("VIDE");
            Game.addEntity(entity);
        }

    }

    public static void main(String[] args) throws Exception {
        Game game = new Game(500, 500);
        game.launch();
    }

}
