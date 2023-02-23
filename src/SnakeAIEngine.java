import controller.*;
import model.Entity;
import model.GameStatus;
import model.ScreenPosition;

import java.util.Random;

public class SnakeAIEngine extends AIEngine {

    private Entity apple;
    private GameStatus gameStatus =  Game.getGameStatus();
    private Game game;

    public void eatApple(Entity apple) {
        this.apple = apple;
        System.out.println("Pomme mangé!");
        gameStatus.increaseScore(1);
        System.out.println("Score : " + gameStatus.getGameScore());
        changeAppleEmplacement();
        increaseSize();
    }

    public void setGame(Game game){
        this.game = game;
    }

    private void increaseSize(){
        Game.snake.increaseSize();
    }

    private boolean isValablePosition(int i, int j){
        if(i < 0 || j < 0 || i > Game.nbCells || j > Game.nbCells)
            return false;
        if(i * Game.unitSize == apple.getX() && j * Game.unitSize == apple.getY())
            return false;

        Entity entity;
        for(int indice = 0; indice < Snake.bodyLenght; indice++) {
            entity = Snake.snakeBody[indice];
            if (entity.getX() == i * Game.unitSize && entity.getY() == j * Game.unitSize)
                return false;
        }

        return true;
    }

    public void changeAppleEmplacement(){
        int i,j;
        do {
            i = new Random().nextInt(Game.nbCells);
            j = new Random().nextInt(Game.nbCells);
        }
        while (!isValablePosition(i,j));
        apple.setPosition(new ScreenPosition(i * Game.unitSize, j * Game.unitSize, Game.unitSize, Game.unitSize));
        game.updateGraphics(apple);

    }

    @Override
    public void update() {
        Game.setGameStatus(gameStatus);
    }
}
