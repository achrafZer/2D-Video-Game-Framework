package controller;

import model.ScreenPosition;


/**
 * Classe abstraite représentant les types de mouvement
 * Toute classe implémentée par l'utilisateur qui souhaite définir son propre type de mouvement doit étendre cette classe.
 */
public abstract class Mouvement {
    /**Direction du déplacement*/
    private Direction direction;
    /**Indique si le mouvement admet des frottements*/
    private boolean hasFriction;
    /**Indique si la vitesse du mouvement est constante*/
    private boolean hasConstantSpeed;
    /**Indique si le mouvement est activé*/
    private boolean isActive;

    protected int unitSize;
    protected int FPS;

    /**
     * Crée un mouvement avec une direction donnée.
     * Par défaut, le mouvement sera activé, vitesse constante et sans frottements.
     */
    public Mouvement(Direction direction, int FPS) {
        this.isActive = true;
        this.direction = direction;
        this.hasConstantSpeed = true;
        this.hasFriction = false;
        unitSize = GameApp.unitSize;
        this.FPS = FPS;
    }

    /**Méthode abstraite qui calcule la prochaine position en fonction de la position courante
     * Elle devra être implémentée par l'utilisateur en fonction du type de mouvement.*/
    public abstract ScreenPosition getNextPosition(ScreenPosition position);

    /**Calcule la prochaine position en fonction de la direction de déplacement courante*/
    //public abstract ScreenPosition getNextPosition(ScreenPosition position,  Direction newDirection);

    public abstract Direction detectDirectionChange(ScreenPosition currentPosition);



    /**
     * Retourne la direction du mouvement
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Retourne True si le mouvement est uniforme, False sinon
     */
    public boolean getHasConstantSpeed() {
        return hasConstantSpeed;
    }

    /**
     * Retourne True si le mouvement admet des frottements, False sinon
     */
    public boolean getHasFriction() {
        return hasFriction;
    }

    /**
     * Définit la direction du mouvement
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    /**
     * Définit si le mouvement est uniforme ou pas
     */
    public void setHasConstantSpeed(boolean hasConstantSpeed) {
        this.hasConstantSpeed = hasConstantSpeed;
    }

    /**
     * Définit si le mouvement admet des frottements ou pas
     */
    public void setHasFriction(boolean hasFriction) {
        this.hasFriction = hasFriction;
    }

    /**
     * Retourne True si le mouvement est active, False sinon
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Définit si le mouvement est active ou pas.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
