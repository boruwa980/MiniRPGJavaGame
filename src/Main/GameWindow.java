package Main;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setTitle("VERY EPIC GAME");


        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        this.setVisible(true);
        this.pack();
        gamePanel.startGameThread();
    }

}
