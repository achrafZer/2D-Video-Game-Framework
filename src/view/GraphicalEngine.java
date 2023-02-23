package view;

import SNAKE_GAME.TESTSnakeBodyShape;
import controller.GameApp;
import model.EShape;
import model.Entity;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe représentant le moteur graphique du jeu.
 * Cette classe se charge de l'affichage des entités sur la fenêtre, et la mise à jour en cas de changement de position ou d'apparence.
 */
public class GraphicalEngine extends JFrame {
    /**La fenêtre sur laquelle les formes seront dessinées*/
    private final Canvas canvas = new Canvas();

    /**
     * Construit et affiche la fenêtre du moteur graphique de longueur et largeur donnée.
     * Ajoute également un détecteur d'entrées clavier <code>keyListener</code>
     * @param width La largeur de la fenêtre
     * @param length La longueur de la fenêtre
     */
    public GraphicalEngine(int width, int length) {
        this.setSize(width, length);
        this.canvas.setSize(width, length);
        this.canvas.setVisible(true);
        this.add(this.canvas);
        this.addKeyListener(new CommandListener());
    }

    /**
     * Dessine toutes
     * @param entities La liste d'entités à mettre en place
     */
    public void initEntities(List<Entity> entities) {
        for (Entity entity : entities)
            this.drawEntity(entity);
    }


    public void drawGrid(int gridSize){
        this.canvas.setGridSize(gridSize);
    }

    /**
     * Met en place d'une entité sur l'interface graphique
     * @param entity L'entité à mettre en place
     */
    public void drawEntity(Entity entity) {
        this.canvas.addDrawable((IDrawable)entity.getEntityParameters().getShape());
    }

    /**
     * Supprime une entité de l'interface graphique
     * @param entity L'entité à enlever de l'interface graphique
     */
    public void removeEntity(Entity entity) {
        this.canvas.removeDrawable((IDrawable)entity.getEntityParameters().getShape());
    }

    /**
     * Met à jour une entité sur l'écran
     * @param entity L'entité qui remplace l'ancienne
     */
    public void update(Entity entity){
        EShape newShape;
        int eWidth = entity.getEntityParameters().getShape().getBounds().width;
        int eHeight = entity.getEntityParameters().getShape().getBounds().height;

        if (Objects.equals(entity.getEntityParameters().getShape().getType(), "RECTANGLE")) {
            newShape = new Rectangle(entity.getX(), entity.getY(), eWidth, eHeight);
        } else if (Objects.equals(entity.getEntityParameters().getShape().getType(), "CIRCLE")){
            newShape = new Circle(entity.getX(), entity.getY(), eWidth, eHeight);
        }else{
            //FIXME-------------------
           newShape = new TESTSnakeBodyShape(entity.getX(), entity.getY(), GameApp.getActiveEntity().getXs(), GameApp.getActiveEntity().getYs());
        }
        this.removeEntity(entity);
        entity.getEntityParameters().setShape(newShape);
        this.drawEntity(entity);
    }

    /**
     * Classe interne pour gérer les entrées clavier
     */
    static class CommandListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            GameApp.setKeyEvent(event);
        }
    }

}
