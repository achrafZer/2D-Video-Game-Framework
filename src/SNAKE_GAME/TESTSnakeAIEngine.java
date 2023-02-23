package SNAKE_GAME;

import controller.AIEngine;
import model.Entity;
import model.GameStatus;
import model.ScreenPosition;

import java.util.Random;

public class TESTSnakeAIEngine extends AIEngine {
    private Entity apple;
    private GameStatus gameStatus =  TESTGame.getGameStatus();
    private TESTGame game;


    public void eatApple(Entity apple) {
        this.apple = apple;
        System.out.println("Pomme mangé!");
        gameStatus.increaseScore(1);
        System.out.println("Score : " + gameStatus.getGameScore());
        changeAppleEmplacement();
        increaseSize();
        increaseSpeed();
    }

    public void increaseSpeed(){
        double increase = 0.8;
        TESTGame.setDelay((int)Math.round(TESTGame.getDelay()*increase));
    }

    public void setGame(TESTGame game){
        this.game = game;
    }

    private void increaseSize(){
        TESTGame.snake.increaseSize();
    }

    private boolean isValablePosition(int x, int y){
        if(x < 0 || y < 0 || x > TESTGame.nbCells || y > TESTGame.nbCells)
            return false;
        if(x * TESTGame.unitSize == apple.getX() && y * TESTGame.unitSize == apple.getY())
            return false;

        for(int i = 0; i < TESTGame.snake.getCurrentSize(); i++) {
            if (TESTGame.snake.getX(i) == x * TESTGame.unitSize && TESTGame.snake.getY(i) == y * TESTGame.unitSize)
                return false;
        }

        return true;
    }

    public void changeAppleEmplacement(){
        int x,y;
        do {
            x = new Random().nextInt(TESTGame.nbCells -1);
            y = new Random().nextInt(TESTGame.nbCells -1 );
        }
        while (!isValablePosition(x,y));
        apple.setPosition(new ScreenPosition(x * TESTGame.unitSize, y * TESTGame.unitSize, TESTGame.unitSize, TESTGame.unitSize));
        //apple.getEntityParameters().setShape(new Circle(x * GameTest.unitSize, y * GameTest.unitSize, GameTest.unitSize, GameTest.unitSize, Color.RED));
        game.updateGraphics(apple);

    }

    @Override
    public void update() {

    }
}
