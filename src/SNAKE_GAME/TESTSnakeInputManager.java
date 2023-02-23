package SNAKE_GAME;

import controller.Direction;
import view.InputsManager;

import java.awt.event.KeyEvent;

public class TESTSnakeInputManager extends InputsManager {

    @Override
    public void manageInputs(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //Ne peut pas revenir en arrière
                if(TESTGame.activeEntity.getDirection() != Direction.RIGHT) {
                    TESTGame.setActiveEntityDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(TESTGame.activeEntity.getDirection() != Direction.LEFT) {
                    TESTGame.setActiveEntityDirection(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_UP:
                if(TESTGame.activeEntity.getDirection() != Direction.DOWN) {
                    TESTGame.setActiveEntityDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(TESTGame.activeEntity.getDirection() != Direction.UP) {
                    TESTGame.setActiveEntityDirection(Direction.DOWN);
                }
                break;
        }
    }
}