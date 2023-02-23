package model;

import org.junit.Test;
import view.Rectangle;

import javax.swing.*;

public class EntityTest {

    @Test
    public void entityTest() {
        /*
        Exemple de création d'une entité snake
         */
//        ScreenPosition snakePosition = new ScreenPosition(100, 100, 10, 30);
//        Rectangle snakeShape = new Rectangle(10, 30);
//        EntityParameters snakeParameters = new EntityParameters(snakePosition, snakeShape);
//
//        Mouvement snakeMouvement = new ProjectileMouvement(Direction.RIGHT);
//        Entity snake = new Entity(snakeParameters, snakeMouvement);
    }

    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        fenetre.setSize(400, 400);

//        ScreenPosition snakePosition = new ScreenPosition(100, 100, 10, 30);
//        Rectangle snakeShape = new Rectangle(10, 30);
//        EntityParameters snakeParameters = new EntityParameters(snakePosition, snakeShape);
//
//        Mouvement snakeMouvement = new ProjectileMouvement(Direction.RIGHT);
//        Entity snake = new Entity(snakeParameters, snakeMouvement)

        Rectangle rectangle = new Rectangle(130, 30, 100, 80);
//        fenetre.add(rectangle);

//        fenetre.add(panel);
        fenetre.setVisible(true);
    }
}
