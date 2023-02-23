import controller.Direction;
import controller.Mouvement;
import controller.ProjectileMouvement;
import model.EShape;
import model.Entity;
import model.EntityParameters;
import view.Rectangle;

public class Snake {

    private Entity head;
    //FIXME: Change Entity[] par Eshape[]
    public static Entity[] snakeBody;
    private int unitSize;
    public static int bodyLenght;
    private int xInit;
    private int yInit;

    public Snake(int unitSize, int bodyLenght, int maxSize, int xInit, int yInit){
        snakeBody =  new Entity[maxSize];
        this.unitSize = unitSize;
        this.bodyLenght = bodyLenght;
        createHead();
        createBody();
        this.xInit = xInit * unitSize;
        this.yInit = yInit * unitSize;
    }

    public Entity[] getSnakeBody() {
        return snakeBody;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public Entity getBodyPart(int index){
        return snakeBody[index];
    }

    public int getBodyLenght() {
        return bodyLenght;
    }

    public void createHead(){
        EShape headShape = new Rectangle(xInit, yInit, unitSize, unitSize);
        EntityParameters headParameters = new EntityParameters(headShape);
        Mouvement headMvt = new ProjectileMouvement(Direction.RIGHT);
        head = new Entity("SNAKE_HEAD", headParameters, headMvt);
        snakeBody[0] = head;
    }

    public void createBody(){
        Mouvement bodyMvt;
        EntityParameters bodyParameters;
        EShape bodySquare;
        for (int i=1; i<=bodyLenght; i++){
            String name;
            if(i == 1)
                name = "NECK";
            else
                name = "BODY";
            bodyMvt = new SnakeBodyMovement(snakeBody[i-1].getDirection(), snakeBody[i-1]);
            bodyMvt.setActive(true);
            bodySquare = new Rectangle(xInit-i*unitSize, yInit, unitSize, unitSize);
            bodyParameters =  new EntityParameters(bodySquare);
            snakeBody[i] = new Entity(name, bodyParameters, bodyMvt);
        }
        snakeBody[bodyLenght].setName("SNAKE_TAIL");
    }

    public void increaseSize(){

        Entity previous = snakeBody[bodyLenght-1];
        previous.setName("BODY");
        Mouvement bodyMvt = new SnakeBodyMovement(previous.getDirection(), previous);
        int x=0, y=0;

        System.out.println(previous.getDirection());

        switch (previous.getDirection()){
            case DOWN: x = previous.getX(); y = previous.getY() + unitSize;  break;
            case UP: x = previous.getX(); y = previous.getY() - unitSize; break;
            case LEFT: x = previous.getX() + unitSize; y = previous.getY(); break;
            case RIGHT:x = previous.getX() - unitSize; y = previous.getY(); break;
            default: ;
        }

        //x = previous.getX()-unitSize+1;
        //y = previous.getY();

        EShape bodySquare = new Rectangle(x, y, unitSize, unitSize);
        EntityParameters bodyParameters = new EntityParameters(bodySquare);
        bodyMvt.setActive(true);
        snakeBody[bodyLenght] = new Entity("SNAKE_TAIL", bodyParameters, bodyMvt);
        System.out.println(snakeBody[bodyLenght].getDirection());
        Game.components.set(bodyLenght+2,snakeBody[bodyLenght]);
        //Game.addEntity(, false);
        bodyLenght++;
    }

}
