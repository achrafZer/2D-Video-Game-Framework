import controller.Direction;
import view.InputsManager;

import java.awt.event.KeyEvent;

public class SnakeInputsManager extends InputsManager {

    @Override
    public void manageInputs(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //Ne peut pas revenir en arrière
                if(Game.activeEntity.getDirection() != Direction.RIGHT) {
                    Game.setActiveEntityDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(Game.activeEntity.getDirection() != Direction.LEFT) {
                    Game.setActiveEntityDirection(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_UP:
                if(Game.activeEntity.getDirection() != Direction.DOWN) {
                    Game.setActiveEntityDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(Game.activeEntity.getDirection() != Direction.UP) {
                    Game.setActiveEntityDirection(Direction.DOWN);
                }
                break;

        }
    }
}