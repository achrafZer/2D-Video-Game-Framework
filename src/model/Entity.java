package model;
import controller.Direction;
import controller.Mouvement;

/**
 * Classse représentant les entités du jeu.
 */
public class Entity {
    /**Nom de l'entité*/
    private String name;

    /**Paramètres de l'entité
     * @see EntityParameters*/
    private EntityParameters entiyParameters;
    /**Mouvement de l'entité*/
    private Mouvement mouvement;
    private EntityParameters previousParameters;

    private Direction previousDirection;
    /**
     * Constructeur qui crée une entité avec un nom donné, des paramètres et un mouvement
     */
    public Entity(String name, EntityParameters entityParameter, Mouvement mouvement) {
        this.name = name;
        this.mouvement = mouvement;
        this.entiyParameters = entityParameter;
        this.previousDirection = mouvement.getDirection();
        //this.previousParameters =  null;
    }

    public Entity(Entity entity){
        this.name = entity.getName();
        this.entiyParameters = entity.getEntityParameters();
        this.mouvement = entity.getMouvement();
        this.previousParameters =  entity.getPreviousParameters();
        this.previousDirection = entity.getDirection();
    }

    /**
     * Retourne le mouvement de l'entité
     */
    public Mouvement getMouvement() {
        return mouvement;
    }

    public Direction getDirection(){return mouvement.getDirection();}

    public Direction getPreviousDirection(){return previousDirection;}
    public void setDirection(Direction direction){
        //on met à jour le previous direction que si la direction a effectivement été changée
        if (!direction.equals(mouvement.getDirection())){
            previousDirection = mouvement.getDirection();
        }
        mouvement.setDirection(direction);
    }
    public boolean isMoving(){return mouvement.isActive();}
    public Direction detectDirectionChange(){
        return mouvement.detectDirectionChange(this.entiyParameters.getScreenPosition());
    }

    public ScreenPosition getNextPosition(){
        return mouvement.getNextPosition(this.entiyParameters.getScreenPosition());
    }
    /**
     * Retourne les paramètre de l'entité
     */
    public EntityParameters getEntityParameters() {
        return entiyParameters;
    }

    public EntityParameters getPreviousParameters(){
        return previousParameters;
    }

    public void setPreviousParameters(EntityParameters p){
        previousParameters = p;
    }

    /**
     * Définit le mouvement de l'entité
     */
    public void setMovement(Mouvement mouvement) {
        this.mouvement = mouvement;
    }

    /**
     * Définit les paramètres de l'entité
     */
    public void setEntiyParameters(EntityParameters entiyParameters) {
        this.previousParameters = new EntityParameters(this.entiyParameters.getShape());
        this.entiyParameters = entiyParameters;
    }

    /**
     * Retourne une représentation en chaîne de caractères de l'entité
     * @return les coordonnées de l'entité
     */
    @Override
    public String toString() {
        return this.name + ": (" + this.entiyParameters.getScreenPosition().getxAxis() + ", " + this.entiyParameters.getScreenPosition().getyAxis() + ")";
    }

    /**
     *Définit la position de l'entité
     */
    public void setPosition(ScreenPosition screenPosition) {
        this.previousParameters = new EntityParameters(this.entiyParameters.getShape());
        this.getEntityParameters().setScreenPosition(screenPosition);
    }


    public ScreenPosition intersects(Entity collidingEntity){
        //FIXME----------------
        ScreenPosition collidingPoint;
        //Collision sur le coté droit de l'entité
        if (this.getX() + (collidingEntity.getEntityParameters().getScreenPosition().getObjetctWidth()) == this.getX()) {
            if (collidingEntity.getY() == this.getY()) {

            }
        }
        return null;
    }

    /**
     * Retourne la position de l'entité sur l'axe X
     */
    public int getX() {
        return this.entiyParameters.getScreenPosition().getxAxis();
    }

    /**
     * Retourne la position de l'entité sur l'axe Y
     */
    public int getY() {
        return this.entiyParameters.getScreenPosition().getyAxis();
    }


    public int[] getXs(){return null;};
    public int[] getYs(){return null;};

    /**
     * Retourne le nom de l'entité
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
