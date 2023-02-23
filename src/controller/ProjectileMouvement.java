package controller;

import model.ScreenPosition;

/**
 *Classe représentant un mouvement rectiligne.
 * Elle étend la classe Mouvement
 * @see Mouvement
 */
public class ProjectileMouvement extends Mouvement {

    /**
     * Constructeur d'un mouvement rectiligne vers une direction donnée
     */
    public ProjectileMouvement(Direction direction) {
        super(direction, GameApp.FPS);
    }


    /**Détecte le changement de direction à venir. Pour un mouvement rectiligne, cette fonction est redondante
     * puisque le changement de direction est immédiat*/
    @Override
    public Direction detectDirectionChange(ScreenPosition currentPosition) {
        return this.getDirection();
    }

    /**Calcule la prochaine position du mouvement rectiligne.*/
    @Override
    public ScreenPosition getNextPosition(ScreenPosition currentPosition) {
        int x = currentPosition.getxAxis();
        int y = currentPosition.getyAxis();
        int width = currentPosition.getObjetctWidth();
        int length = currentPosition.getObjectLengh();

        switch (this.getDirection()) {
            case RIGHT -> {
                //FIXME: length?? c'est pas width?
                assert x < GameApp.getBoardLength();
                return new ScreenPosition(x + FPS, y, width, length);
            }
            case LEFT -> {
                assert x > 0;
                return new ScreenPosition(x - FPS, y, width, length);
            }
            case UP -> {
                assert y > 0;
                return new ScreenPosition(x, y - FPS, width, length);
            }
            case DOWN -> {
                assert y < GameApp.getBoardWidth();
                return new ScreenPosition(x, y + FPS, width, length);
            }
        }
        return null;    }
    //@Override

    //public ScreenPosition getNextPosition(ScreenPosition position, Direction newDirection) {return getNextPosition(position);}

}
