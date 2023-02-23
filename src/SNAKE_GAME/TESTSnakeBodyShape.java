package SNAKE_GAME;

import model.EShape;
import view.IDrawable;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TESTSnakeBodyShape extends EShape implements IDrawable {
    int headX, headY;
    int[] x, y;


    public TESTSnakeBodyShape(int headX, int headY, int[]x, int[]y) {
        this.headX = headX;
        this.headY = headY;
        this.x = x;
        this.y = y;
    }



    @Override
    public Rectangle getBounds() {
        return new java.awt.Rectangle(headX, headY, TESTGame.unitSize, TESTGame.unitSize);
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
        return false;
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
        return "SNAKEBODY";
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(43, 181, 80));
        graphics.fillOval( this.headX, this.headY, TESTGame.unitSize, TESTGame.unitSize);
        for (int i = 1; i < TESTGame.snake.currentSize; i++) {
            graphics.fillOval( TESTGame.snake.getX(i),  TESTGame.snake.getY(i), TESTGame.unitSize, TESTGame.unitSize);
        }
    }
}
