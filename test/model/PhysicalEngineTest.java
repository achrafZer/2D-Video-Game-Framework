package model;

import controller.Direction;
import controller.GameApp;
import controller.Mouvement;
import controller.ProjectileMouvement;
import org.junit.Test;
import view.Rectangle;

public class PhysicalEngineTest {
    @Test
    public void getNextPositionTest() {

        GameApp.width = 500;
        GameApp.length = 500;

        EShape shape = new Rectangle(0, 0, 50, 10);
        EntityParameters snakeParameters = new EntityParameters(shape);
        Mouvement snakeMvt = new ProjectileMouvement(Direction.RIGHT);
        Entity snake = new Entity("SNAKE", snakeParameters, snakeMvt);
        System.out.println(snake);

        PhysicalEngine physicalEngine = new PhysicalEngine();
        snake.setPosition(physicalEngine.getNextPosition(snake));
        System.out.println(snake);


    }


}
