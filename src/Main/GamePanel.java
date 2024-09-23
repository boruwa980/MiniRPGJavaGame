package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    public final int originalTileSize = 32; //16x16 pixels
    public final int scale = 2; //3x scale of 16x16 pixels (48x48 pixels)

    public final int tileSize = originalTileSize * scale; // 48x48 pixels

    //4x3 ratio of screen
    public final int maxScreenCol = 30; //16 tiles;
    public final int maxScreenRow = 17; //12 tiles;

    public final int screenWidth = tileSize*maxScreenCol; //768 pixels
    public final int screenHeight = tileSize*maxScreenRow; //576 pixels

    //WORLD settings
    public final int maxWorldCol = 50; //50 tiles
    public final int maxWorldRow = 50; //50 tiles
    public final int worldWidth = tileSize*maxWorldCol; //2400 pixels
    public final int worldHeight = tileSize*maxWorldRow; //2400 pixels

    //FPS
    final int FPS = 140;

    TileManager tileManager = new TileManager(this);
    KeyHandling keyHandler = new KeyHandling();

    //INGAME TIMER
    Thread gameTherad;

    public CollisionDetection collisionDetection = new CollisionDetection(this); //create collision detection object

    //create player object
    public Player player = new Player(this, keyHandler);

    public GamePanel() {
        // <caret>
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of the panel
        this.setBackground(Color.BLACK); //set background color to black
        this.setDoubleBuffered(true); //enable double buffering (helps to reduce flickering)

        this.addKeyListener(keyHandler);
        this.setFocusable(true); // set focus to the panel
    }

    public void startGameThread(){
        gameTherad = new Thread(this); //create new thread
        gameTherad.start(); //start the game thread
    }

    @Override
    public void run() {

        double drawInterval = 1000_000_000/FPS; //time between each frame
        double delta = 0; //time since last frame
        long lastTime = System.nanoTime(); //time of the last frame
        long currentTime; //current time
        long timer = 0;
        long drawCount = 0;

        //game loop
        while (gameTherad!=null){
            //delta method to control the frame rate

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval; //time since last frame divided by time between each frame
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000_000_000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update(){
        player.update();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
