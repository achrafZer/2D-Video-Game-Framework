package view;

import java.awt.event.KeyEvent;

/**
 * Gestionnaire d'entrées clavier
 */
public abstract class InputsManager {
    /**
     * Gère les entrées clavier en fonction de la touche sur laquelle le joueur a appuyé
     */
    public abstract void manageInputs(KeyEvent e);

}
