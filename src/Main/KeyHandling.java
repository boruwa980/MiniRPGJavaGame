package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandling implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //returns the number of key that was pressed;

        //if pressed W
        if(code == KeyEvent.VK_W){
            //move character up
            upPressed = true;

        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            //move character left
        }
        if(code == KeyEvent.VK_S){
            //move character down
            downPressed = true;

        }
        if(code == KeyEvent.VK_D){
            //move character right
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); //returns the number of key that was pressed;


        //if released W
        if(code == KeyEvent.VK_W){
            //move character up
            upPressed = false;
        }

        //if pressed A
        if(code == KeyEvent.VK_A){
            leftPressed = false;
            //move character left
        }

        //if pressed S
        if(code == KeyEvent.VK_S){
            //move character down
            downPressed = false;
        }

        //if pressed D
        if(code == KeyEvent.VK_D){
            //move character right
            rightPressed = false;
        }


    }
}
