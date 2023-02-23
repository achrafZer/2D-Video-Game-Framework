package model;

/**
 * Classe représentant les paramètres d'une entité.
 */
public class EntityParameters {
    private ScreenPosition screenPosition; //La position de l'entité en question
    /**Booléan qui indique si l'entité elle peut être traversée ou pas*/
    private boolean isCrossable; //Lst true si l'entité peut être traversée, par défaut = true
    /**Forme de l'entité pour l'affichage graphique*/
    private EShape shape; //La forme de l'entité
    private boolean mvtInTheBeggining; //Est true quand la variable est en mouvement dès le début du jeu

    /**
     * Constructeur qui défini les champs en fonction d'une forme donnée.
     * Par défaut, l'entité est traversable,
     */
    public EntityParameters(EShape shape) {
        this.screenPosition = new ScreenPosition(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height);
        this.isCrossable = true;
        this.shape = shape;
        this.mvtInTheBeggining = false;
    }


    /**
     * Retourne la position de l'entité
     */
    public ScreenPosition getScreenPosition() {
        return screenPosition;
    }

    /**
     * Retourne la forme de l'entité
     */
    public EShape getShape() {
        return shape;
    }

    /**
     * Retourne True si l'entité est traversable, False sinon.
     */
    public boolean isCrossable() {
        return isCrossable;
    }

    /**
     * Définit la nouvelle valeur du champ isCrossable
     */
    public void setCrossable(boolean crossable) {
        isCrossable = crossable;
    }

    //FIXME: Eliminer screenPosition?
    public void setScreenPosition(ScreenPosition screenPosition) {
        this.screenPosition = screenPosition;
    }

    /**
     * Définit la nouvelle forme de l'entité
     */
    public void setShape(EShape shape) {
        this.shape = shape;
    }
}
