package model;

/**
 * Le gestionnaire de collisions est une classe abstraite qui rassemble toutes les informations nécessaires pour gérer
 * toutes les collisions du jeu
 */
public abstract  class CollisionManager {
    /**
     * Définit le comportement que le programme doit avoir en cas de collision entre l'entité principale et l'un des
     * quatre bords du plateau
     */
    public abstract void manageEntityBoardCollision();

    /**
     * Définit le comportement que le programme doit avoir en cas de collision entre entity1 et entity2
     * @param entity1 La première entité
     * @param entity2 La deuxième entité
     */
    public abstract void manageEntityCollision(Entity entity1, Entity entity2);

    public abstract boolean intersectionDetected(Entity entity, ScreenPosition newPosition);

}
