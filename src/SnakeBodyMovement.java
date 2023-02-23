import controller.Direction;
import controller.GameApp;
import controller.Mouvement;
import model.Entity;
import model.ScreenPosition;

//FIXME: l'utilisateur est censé pouvoir accéder a ca?
public class SnakeBodyMovement extends Mouvement {

    private Entity predecessor;
    /**
     * Crée un mouvement avec une direction donnée.
     * Par défaut, le mouvement sera activé, vitesse constante et sans frottements.
     *
     * @param direction
     */
    public SnakeBodyMovement(Direction direction, Entity predecessor) {
        super(direction, GameApp.FPS);
        this.predecessor = predecessor;
    }

    /**Détecte le changement de direction à venir. Contrairement au mouvement rectiligne, dans le mouvement du corps
     * de la serpent, ce changement n'est pas immédiat. On doit attendre que le composant soit aligné avec son prédécesseur
     * avant de le faire change de direction.
     * */
    public Direction detectDirectionChange(ScreenPosition currentPosition){
        //S'il est aligné avec le prédecesseur, il doit suivre la même direction
        if (currentPosition.getxAxis() == predecessor.getX() || currentPosition.getyAxis() == predecessor.getY()){
            return predecessor.getDirection();
        }
        //S'il n'est pas aligné parce que le prédecesseur a changé de direction et a bougé, il doit continuer dans sa direction courante jusqu'au moment de s'aligner à nouveau
        return predecessor.getPreviousDirection();
    }

    @Override
    public ScreenPosition getNextPosition(ScreenPosition currentPosition){

        int x = currentPosition.getxAxis();
        int y = currentPosition.getyAxis();
        int width = currentPosition.getObjetctWidth();
        int length = currentPosition.getObjectLengh();

        Direction newDirection = detectDirectionChange(currentPosition);

        switch (newDirection) {
            case RIGHT -> {
                assert x < GameApp.getBoardLength();
                return new ScreenPosition(x + FPS, y, width, length);
            }
            case LEFT -> {
                assert x > 0;
                return new ScreenPosition(x - FPS, y, width, length);
            }
            case UP -> {
                assert y > 0;
                return new ScreenPosition(x, y - FPS, width, length);
            }
            case DOWN -> {
                assert y < GameApp.getBoardWidth();
                return new ScreenPosition(x, y + FPS, width, length);
            }
        }
        return null;
    }
}
