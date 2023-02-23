package view;

import controller.GameApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.*;

public class GameUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 42L;
    /**
     * Constructs a new visible frame.
     */

    public GameUI() {
        setTitle("USER INTERFACE");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void mainMenu(String gameName){
        add(new menuPanel(gameName));
    }

    public void popUpMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    private class menuPanel extends JPanel{

        public menuPanel(String message){
            this.add(new JLabel("BIENVENU DANS LE JEU DE " + message));
            Button start =new Button("START GAME");
            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("HERE");
                    GameApp.startGame = true;
                    SwingUtilities.windowForComponent(start).dispose();
                }
            });
            this.add(start, Component.CENTER_ALIGNMENT);
            setVisible(true);
        }
    }

}


