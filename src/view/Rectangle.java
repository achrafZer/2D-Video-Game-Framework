package view;

import model.EShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends EShape implements IDrawable {
    private int x;
    private int y;
    private int width;
    private int height;

    private Color color;


    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Rectangle(int x, int y, int width, int height) {
        this(x, y, width, height, Color.BLACK);
    }


    /*public void paint(Graphics g) {
        g.fillRect(this.x, this.y, this.width, this.height);
    }*/

    @Override
    public java.awt.Rectangle getBounds() {
        return new java.awt.Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        double x0 = this.x;
        double y0 = this.y;
        return (x + w > x0 &&
                y + h > y0 &&
                x < x0 + this.width &&
                y < y0 + this.height);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return null;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }


}
