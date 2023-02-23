package view;

import controller.GameApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**Classe reprensentant la fenêtre sur laquelle les formes des entités seront dessinées
 * @see IDrawable*/
//TODO implement ActionListener!
public class Canvas extends JPanel {
    /**Liste des formes <code>IDrawable</code> qui seront dessinées*/
    private List<IDrawable> drawables = new ArrayList<>();
    private int gridSize =0;

    public void setGridSize(int gridSize){
        this.gridSize = gridSize;
    }

    /**Dessine les formes des entités sur la fenêtre*/
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (gridSize>0){
            int widerDimension = Math.max(GameApp.getBoardLength(), GameApp.getBoardWidth());
            for (int i = 0; i < widerDimension / gridSize; i++) {
                graphics.drawLine(i*gridSize, 0, i*gridSize, GameApp.getBoardLength());
                graphics.drawLine(0, i*gridSize, GameApp.getBoardWidth(), i*gridSize);
            }
        }
        synchronized (this){
            for (IDrawable drawable : drawables) {
                drawable.draw(graphics);
            }
        }
    }

    /**Ajoute la forme donnée à la liste des drawables et la dessine sur la fenêtre*/
    public void addDrawable(IDrawable drawable) {
        this.drawables.add(drawable);
        this.repaint();
    }

    /**Retire l'entitée donnée de la liste des drawables et l'efface de la fenêtre*/
    public void removeDrawable(IDrawable drawable) {
        this.drawables.remove(drawable);
        this.repaint();
    }

    public List<IDrawable> getDrawables() {
        return drawables;
    }
}
