package SNAKE_GAME;

import controller.Direction;
import controller.Mouvement;
import model.Entity;
import model.EntityParameters;

public class TESTSnake extends Entity {
    public int[] x;
    public int[] y;


    @Override
    public int[] getXs() {
        return this.x;
    }
    @Override
    public int[] getYs() {
        return this.y;
    }

    public int currentSize;


    public int getCurrentSize() {
        return currentSize;
    }

    public void increaseSize(){
        currentSize = currentSize + TESTGame.unitSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public TESTSnake(int initSize, int[]x, int[]y, String name, EntityParameters entityParameter, Mouvement mouvement) {
        super(name, entityParameter, mouvement);
        currentSize = initSize;
        this.x = x;
        this.y = y;
        this.x[0] = getX();
        this.y[0] = getY();
        initBody(this.getDirection());
    }

    public TESTSnake(Entity entity) {
        super(entity);
    }

    public void followHead(){
        if (this.getX()!=x[0] || this.getY()!=y[0]){
            for(int i = currentSize-1;i>0;i--) {
                x[i] = x[i-1] ;
                y[i] = y[i-1] ;
            }
            y[0]=getY();
            x[0]=getX();
        }
    }

    public void initBody(Direction direction){
        for (int i=1; i<currentSize; i++){
            int _x=0, _y=0;
            switch (direction){
                case DOWN: _x = this.getX(); _y = this.getY() + i* TESTGame.unitSize;  break;
                case UP: _x = this.getX(); _y = this.getY() - i* TESTGame.unitSize; break;
                case LEFT: _x = this.getX() + i* TESTGame.unitSize; _y = this.getY(); break;
                case RIGHT:_x = this.getX() - i* TESTGame.unitSize; _y = this.getY(); break;
                default: ;
            }
            this.x[i] = _x;
            this.y[i] = _y;
        }
    }

    public void fill(Direction direction){

    }


    public int getX(int position){
        return x[position];
    }

    public int getY(int position){
        return y[position];
    }


}
