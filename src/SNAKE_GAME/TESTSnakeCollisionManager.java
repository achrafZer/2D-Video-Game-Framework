package SNAKE_GAME;

import model.CollisionManager;
import model.Entity;
import model.ScreenPosition;

public class TESTSnakeCollisionManager extends CollisionManager {

    TESTSnakeAIEngine aI;

    public TESTSnakeCollisionManager(TESTSnakeAIEngine aI){
        this.aI = aI;
    }

    @Override
    public void manageEntityBoardCollision() {
        TESTGame.endTheGame();
    }

    @Override
    public void manageEntityCollision(Entity entity1, Entity entity2) {
        if (entity1.getName().equals("SNAKE") && entity2.getName().equals("APPLE") ||
                entity2.getName().equals("SNAKE") && entity1.getName().equals("APPLE")) {
            System.out.println("collision entre la pomme et le serpent");
            if (entity1.getName().equals("APPLE"))
                aI.eatApple(entity1);
            else
                aI.eatApple(entity2);
        }
        else if(entity1.getName().equals(entity2.getName())) {
            System.out.println("collision entre la tete et le corps");
            TESTGame.endTheGame();
        }else
            System.out.println("????");
    }

    @Override
    public boolean intersectionDetected(Entity entity, ScreenPosition newPosition) {
        if (entity.getName().equals("APPLE")){
            return (entity.getX() == TESTGame.snake.getX(0) && entity.getY() == TESTGame.snake.getY(0));
        }
        else if (entity.getName().equals("SNAKE")){
            for(int i = TESTGame.snake.getCurrentSize()-1; i>0; i--) {
                if(TESTGame.snake.getX(0) == TESTGame.snake.getX(i)) {
                    if (TESTGame.snake.getY(0) == TESTGame.snake.getY(i)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
