package model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Classe représentant la forme d'une entité pour l'affichage graphique.
 * Il s'agit d'une version plus adaptée de la classe <code>Shape</code>
 * @see Shape
 */

//FIXME: Ca sert à quoi ca?
public abstract class EShape implements Shape {

    public abstract Rectangle getBounds();

    public abstract Rectangle2D getBounds2D();

    public abstract boolean contains(double x, double y);

    public abstract boolean contains(Point2D p);

    public abstract boolean intersects(double x, double y, double w, double h);

    public abstract boolean intersects(Rectangle2D r);

    public abstract boolean contains(double x, double y, double w, double h);

    public abstract boolean contains(Rectangle2D r);

    public abstract PathIterator getPathIterator(AffineTransform at);

    public abstract PathIterator getPathIterator(AffineTransform at, double flatness);

    /**
     * @return Le type de la forme
     */
    public abstract String getType();
}
