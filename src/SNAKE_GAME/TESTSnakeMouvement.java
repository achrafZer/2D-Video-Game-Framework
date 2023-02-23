package SNAKE_GAME;

import controller.Direction;
import controller.GameApp;
import controller.Mouvement;
import model.ScreenPosition;

public class TESTSnakeMouvement extends Mouvement {
    /**
     * Crée un mouvement avec une direction donnée.
     * Par défaut, le mouvement sera activé, vitesse constante et sans frottements.
     *
     * @param direction
     * @param FPS
     */
    public TESTSnakeMouvement(Direction direction, int FPS) {
        super(direction, FPS);
    }

    @Override
    public ScreenPosition getNextPosition(ScreenPosition position) {
        TESTGame.snake.followHead();
        int x = position.getxAxis();
        int y = position.getyAxis();
        int width = position.getObjetctWidth();
        int length = position.getObjectLengh();

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
        return null;
    }

    @Override
    public Direction detectDirectionChange(ScreenPosition currentPosition) {
        return getDirection();
    }
}
