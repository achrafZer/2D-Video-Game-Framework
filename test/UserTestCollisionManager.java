import model.CollisionManager;
import model.Entity;
import model.ScreenPosition;

import java.util.Objects;

public class UserTestCollisionManager extends CollisionManager {
    @Override
    public void manageEntityBoardCollision() {
        System.out.println("board collision");
        //Game.endTheGame();
    }

    @Override
    public void manageEntityCollision(Entity entity1, Entity entity2) {
        if (Objects.equals(entity1, "SNAKE_HEAD") && Objects.equals(entity2, "APPLE")) {
            System.out.println("collision entre la pomme et le serpent");
            Game.removeEntity(entity2);

        }
    }

    @Override
    public boolean intersectionDetected(Entity entity, ScreenPosition newPosition) {
        return false;
    }
}
