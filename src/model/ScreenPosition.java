package model;


//FIXME: Eliminer la classe??
/**
 * Classe représentant une position d'une entité, tout en indiquant l'espace que cette entité là occupe
 */
public class ScreenPosition {
    private int xAxis; // La position sur l'axe X
    private int yAxis; // La position sur l'axe Y
    private int objetctWidth; // La largeur
    private int objectLengh; // La longueur

    /**
     * Un constructeur qui génère une position sur l'écran
     * @param x La position sur l'axe X
     * @param y La position sur l'axe Y
     * @param width La largeur
     * @param lenght La longueur
     */
    public ScreenPosition(int x, int y, int width, int lenght) {
        this.xAxis = x;
        this.yAxis = y;
        this.objetctWidth = width;
        this.objectLengh = lenght;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setObjetctWidth(int objetctWidth) {
        this.objetctWidth = objetctWidth;
    }

    public void setObjectLengh(int objectLengh) {
        this.objectLengh = objectLengh;
    }

    public int getObjetctWidth() {
        return objetctWidth;
    }

    public int getObjectLengh() {
        return objectLengh;
    }

    public int getyAxis() {
        return yAxis;
    }

    public int getxAxis() {
        return xAxis;
    }
}
