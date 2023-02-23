package view;

import model.EShape;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**Classe représentant une forme de base: Un ovale.
 * Cette classe peut être utilisée par l'utilisateur pour simplement dessiner un ovale.*/
public class Circle extends EShape implements IDrawable {
    //FIXME: Changer le nom de Circle à Oval?
    /**Coordonnées de l'ovale sur l'axe x*/
    private int x;
    /**Coordonnées de l'ovale sur l'axe y*/
    private int y;
    /**Largeur du rectangle circonscrit à l'ovale */
    private int width;
    /**Longeur du rectangle circonscrit à l'ovale*/
    private int height;

    private Color color;


    /**Constructeur d'un cercle à des coordonnées données et avec des dimensions données*/
    public Circle(int x, int x1, int x2, int x3, Color color) {
        this.x = x;
        this.y = x1;
        this.width = x2;
        this.height = x3;
        this.color = color;
    }

    public Circle(int x, int x1, int x2, int x3) {
        this(x, x1, x2, x3, Color.RED);
    }


    @Override
    public Rectangle getBounds() {
        return new java.awt.Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double v, double v1) {
        return false;
    }

    @Override
    public boolean contains(Point2D point2D) {
        return false;
    }

    @Override
    public boolean intersects(double v, double v1, double v2, double v3) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D rectangle2D) {
        return false;
    }

    @Override
    public boolean contains(double v, double v1, double v2, double v3) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D rectangle2D) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform, double v) {
        return null;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillOval(this.x, this.y, this.width, this.height);
    }
}
