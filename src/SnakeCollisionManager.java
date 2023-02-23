import model.CollisionManager;
import model.Entity;
import model.ScreenPosition;

import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class SnakeCollisionManager extends CollisionManager {

    SnakeAIEngine aiEngine;

    public SnakeCollisionManager(SnakeAIEngine aiEngine) {
        this.aiEngine = aiEngine;
    }

    @Override
    public void manageEntityBoardCollision() {
        Game.endTheGame();
    }

    @Override
    public void manageEntityCollision(Entity entity1, Entity entity2) {
        if (Objects.equals(entity1.getName(), "SNAKE_HEAD") && Objects.equals(entity2.getName(), "APPLE") ||
                Objects.equals(entity2.getName(), "SNAKE_HEAD") && Objects.equals(entity1.getName(), "APPLE")) {
            System.out.println("collision entre la pomme et le serpent");
            if (entity1.getName().equals("APPLE"))
                aiEngine.eatApple(entity1);
            else
                aiEngine.eatApple(entity2);
        }
        else if((Objects.equals(entity1.getName(), "SNAKE_HEAD") && Objects.equals(entity2.getName(), "BODY")) ||
                (Objects.equals(entity2.getName(), "SNAKE_HEAD") && Objects.equals(entity1.getName(), "BODY"))) {
            System.out.println("collision entre la tete et le corps");
            Game.endTheGame();
        }
        else if ((Objects.equals(entity1.getName(), "SNAKE_TAIL") && Objects.equals(entity2.getName(), "APPLE")) ||
                (Objects.equals(entity2.getName(), "SNAKE_TAIL") && Objects.equals(entity1.getName(), "APPLE"))){
            System.out.println("collision entre la queue et le corps");
            //todo
        }
    }
    @Override
    public boolean intersectionDetected(Entity entity, ScreenPosition newPosition){
        return switch (entity.getDirection()) {
            case RIGHT -> (newPosition.getxAxis() + Game.unitSize == entity.getX() + 1) && (newPosition.getyAxis() == entity.getY());
            case LEFT -> (newPosition.getxAxis() == entity.getX() + Game.unitSize - 1) && (newPosition.getyAxis() == entity.getY());
            case UP -> (newPosition.getyAxis() == entity.getY() + Game.unitSize - 1) && (newPosition.getxAxis() == entity.getX());
            case DOWN -> (newPosition.getyAxis() + Game.unitSize == entity.getY() + 1) && (newPosition.getxAxis() == entity.getX());
            default -> false;
        };
    }
}
