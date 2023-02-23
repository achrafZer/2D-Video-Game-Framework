package model;

import controller.Direction;
import controller.GameApp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe principale du moteur physique du framework
 */
public class PhysicalEngine {

    //FIXME : LIGNES DE CODE DUPLIQUÉES! REFACTOR

    public Direction detectDirectionChange(Entity entity){
        return entity.detectDirectionChange();
    }

    /**
     * Calcule la nouvelle position de l'entité donnée en paramètre en fonction de son mouvement
     * @param entity L'entité qui est en mouvement
     * @return La nouvelle position de l'entité <code>entity</code>
     */
    public ScreenPosition getNextPosition(Entity entity) {
        ScreenPosition newPosition = entity.getNextPosition();
        return newPosition;
    }

    /**Fonction qui détectera toutes les possibles collisions lorsqu'une entité donnée va être déplacée.
     * Elle vérifie, en fonction du sens de déplacement de l'entité, s'il y aura des collisions avec les bords du plateau ou avec d'autres entités.
     * @param collidingEntity L'entité qui pourrait rentrer en collision.
     * @param newPosition La nouvelle position prévue de l'entité.
     * */
    public void detectCollisions(Entity collidingEntity, ScreenPosition newPosition) {
        if (collidingEntity.getDirection() == Direction.RIGHT) {
            detectRightEntityCollision(collidingEntity, newPosition);
            detectRightBoardCollision(collidingEntity, newPosition);
        } else if (collidingEntity.getDirection() == Direction.LEFT) {
            detectLeftEntityCollision(collidingEntity, newPosition);
            detectLeftBoardCollision(newPosition);
        } else if (collidingEntity.getDirection() == Direction.UP) {
            detectTopEntityCollision(collidingEntity, newPosition);
            detectTopBoardCollision(newPosition);
        } else if (collidingEntity.getDirection() == Direction.DOWN) {
            detectBottomEntityCollision(collidingEntity, newPosition);
            detectBottomBoardCollision(collidingEntity, newPosition);
        }
    }

    /**
     * Une fonction qui détecte les collisions entre une entité et le bord droit du plateau.
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param entity L'entité qui rentre en collision avec le plateau
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public void detectRightBoardCollision(Entity entity, ScreenPosition newPosition) {
        if (newPosition.getxAxis() + (entity.getEntityParameters().getScreenPosition().getObjetctWidth()) >= GameApp.getBoardWidth() + 1) {
            GameApp.getCollisionManager().manageEntityBoardCollision();
        }
    }

    /**
     * Une fonction qui détecte les collisions entre une entité et le bord gauche du plateau
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public void detectLeftBoardCollision(ScreenPosition newPosition) {
        if (newPosition.getxAxis() <= -1) {
            GameApp.getCollisionManager().manageEntityBoardCollision();
        }
    }

    /**
     * Une fonction qui détecte les collisions entre une entité et le bord haut du plateau
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public void detectTopBoardCollision(ScreenPosition newPosition) {
        if (newPosition.getyAxis() <= -1) {
            GameApp.getCollisionManager().manageEntityBoardCollision();
        }
    }

    /**
     * Une fonction qui détecte les collisions entre une entité et le bord bas du plateau
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param collidingEntity L'entité qui rentre en collision avec le plateau
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public void detectBottomBoardCollision(Entity collidingEntity, ScreenPosition newPosition) {
        if (newPosition.getyAxis() + (collidingEntity.getEntityParameters().getScreenPosition().getObjectLengh()) >= GameApp.getBoardLength() + 1) {
            GameApp.getCollisionManager().manageEntityBoardCollision();
        }
    }

    /**
     * Une fonction qui détecte une collision à droite entre une entité donnée et toutes les autres entités du plateau
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param collidingEntity L'entité qui rentre en collision avec d'autres entités
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public synchronized void detectRightEntityCollision(Entity collidingEntity, ScreenPosition newPosition) {
        ArrayList<Entity> copyComponents = new ArrayList<>(GameApp.components);
        for (Entity entity: GameApp.components) {
            if (GameApp.getCollisionManager().intersectionDetected(entity, newPosition)){
                GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
            }
            /*
            if (newPosition.getxAxis() + (collidingEntity.getEntityParameters().getScreenPosition().getObjetctWidth()) == entity.getX() + 1) {
                if (newPosition.getyAxis() == entity.getY()){
                    GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
                }
            }*/
        }

        Set<Entity> union = new HashSet<>();
        union.addAll(copyComponents);
        union.addAll(GameApp.components);
        GameApp.components.clear();
        GameApp.components.addAll(union);
    }

    /**
     * Une fonction qui détecte une collision à gauche entre une entité donnée et toutes les autres entités du plateau.
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param collidingEntity L'entité qui rentre en collision avec d'autres entités
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public synchronized void detectLeftEntityCollision(Entity collidingEntity, ScreenPosition newPosition) {
        ArrayList<Entity> copyComponents = new ArrayList<>(GameApp.components);
        for (Entity entity: GameApp.components) {

            if (GameApp.getCollisionManager().intersectionDetected(entity, newPosition)){
                GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
            }
            /*
            if (newPosition.getxAxis() == entity.getX() + entity.getEntityParameters().getScreenPosition().getObjetctWidth() - 1) {
                if (newPosition.getyAxis() == entity.getY()){
                    GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
                }
            }*/
        }

        Set<Entity> union = new HashSet<>();
        union.addAll(copyComponents);
        union.addAll(GameApp.components);
        GameApp.components.clear();
        GameApp.components.addAll(union);
    }

    /**
     * Une fonction qui détecte une collision en haut de l'entité donnée avec toutes les autres entités du plateau.
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param collidingEntity L'entité qui rentre en collision avec d'autres entités
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public synchronized void detectTopEntityCollision(Entity collidingEntity, ScreenPosition newPosition) {
        ArrayList<Entity> copyComponents = new ArrayList<>(GameApp.components);
        for (Entity entity: GameApp.components) {
            if (GameApp.getCollisionManager().intersectionDetected(entity, newPosition)){
                GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
            }
            /*
            if (newPosition.getyAxis() == entity.getY() + entity.getEntityParameters().getScreenPosition().getObjectLengh() - 1) {
                if (newPosition.getxAxis() == entity.getX()){
                    GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
                }
            }*/
        }

        Set<Entity> union = new HashSet<>();
        union.addAll(copyComponents);
        union.addAll(GameApp.components);
        GameApp.components.clear();
        GameApp.components.addAll(union);
    }

    /**
     * Une fonction qui détecte une collision en bas de l'entité donnée avec toutes les autres entités du plateau.
     * Elle notifie le gestionnaire de collisions pour traiter la collision en question.
     * @param collidingEntity L'entité qui rentre en collision avec d'autres entités
     * @param newPosition La nouvelle position prévue de l'entité
     */
    public synchronized void detectBottomEntityCollision(Entity collidingEntity, ScreenPosition newPosition) {
        ArrayList<Entity> copyComponents = new ArrayList<>(GameApp.components);
        for (Entity entity: GameApp.components) {
            if (GameApp.getCollisionManager().intersectionDetected(entity, newPosition)){
                GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
            }
            /*
            if (newPosition.getyAxis() + collidingEntity.getEntityParameters().getScreenPosition().getObjectLengh() == entity.getY() + 1) {
                if (newPosition.getxAxis() == entity.getX()){
                    GameApp.getCollisionManager().manageEntityCollision(collidingEntity, entity);
                }
            }*/
        }

        Set<Entity> union = new HashSet<>();
        union.addAll(copyComponents);
        union.addAll(GameApp.components);
        GameApp.components.clear();
        GameApp.components.addAll(union);
    }




    /**
     * Fait bouger une entité selon sa direction
     * @param entity L'entité en mouvement
     */
    public void move(Entity entity) {
        //Changement de position
        Direction newDirection = this.detectDirectionChange(entity);
        ScreenPosition newPosition = this.getNextPosition(entity);
        detectCollisions(entity, newPosition);
        GameApp.notifyNewPosition_Physical(entity, newPosition, newDirection);
    }


}
