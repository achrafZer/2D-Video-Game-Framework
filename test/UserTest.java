import controller.*;
import controller.ProjectileMouvement;
import model.EShape;
import model.Entity;
import model.EntityParameters;
import view.Circle;
import view.Rectangle;

public class UserTest extends GameApp {

    public UserTest(int width, int length) throws Exception {
        super(width, length);
    }

    public void setManagers() {
        SnakeAIEngine snakeAIEngine = new SnakeAIEngine();
        setInputsManager(new SnakeInputsManager());
        setAiEngine(snakeAIEngine);
        setCollisionManager(new SnakeCollisionManager(snakeAIEngine));
    }

    public void initEntities() {
        EShape shape = new Rectangle(0, 0, 100, 30);
        EntityParameters snakeParameters = new EntityParameters(shape);
        Mouvement snakeMvt = new ProjectileMouvement(Direction.RIGHT);
        Entity snake = new Entity("SNAKE", snakeParameters, snakeMvt);
        addEntity(snake);
        this.setActiveEntity(snake);

        EShape shape1 = new Circle(150, 150, 30, 30);
        EntityParameters appleParameters = new EntityParameters(shape1);
        Mouvement appleMvt = new ProjectileMouvement(Direction.UP);
        appleMvt.setActive(false);
        Entity apple = new Entity("APPLE", appleParameters, appleMvt);
        addEntity(apple);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game(500, 500);
        game.launch();
    }

}
