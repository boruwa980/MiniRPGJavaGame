package entity;

import Main.GamePanel;
import Main.KeyHandling;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gp;
    KeyHandling kh;

    double scaleFactor = 1;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandling kh){
        this.gp = gp;
        this.kh = kh;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);


        //collison area
        solidArea = new Rectangle(4,24,24,20);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX= gp.tileSize*23;
        worldY= gp.tileSize * 21;
        speed=1.5;
        direction = "down";

    }

    //player animation
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacGora1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacGora2.png"));
            up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacGora3.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacDol1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacDol2.png"));;
            down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacDol3.png"));;
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacLewo1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacLewo2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacLewo3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacPrawo1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacPrawo2.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/nowaPostacPrawo3.png"));



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(kh.downPressed == true || kh.upPressed == true || kh.leftPressed == true || kh.rightPressed == true){
            //sprite animation go up

            if (kh.upPressed == true) {
                direction = "up";

            } else if (kh.downPressed == true) { //sprite animation go down
                direction = "down";

            }else if (kh.leftPressed == true) {//sprite animation go left
                direction = "left";

            } else if (kh.rightPressed == true) { //sprite animation go right
                direction = "right";

            }


            //chceck tile collision
            collisionOn = false;
            gp.collisionDetection.checkTile(this);

            //if collision is false, player cant move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }


            //sprite animation
            spriteCounter++;
            if(spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 3;
                }else if (spriteNum == 3){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    //draw player
    public void draw(Graphics2D g2d){
        BufferedImage img = null; //image to draw

        //which sprite to draw
        switch (direction){
            case "up":
                if(spriteNum==1){
                    img = up1;
                }
                if(spriteNum ==2){
                    img = up2;
                }
                if(spriteNum ==3){
                    img = up3;
                }
                break;
            case "down":
                if(spriteNum==1){
                    img = down1;
                }
                if(spriteNum ==2){
                    img = down2;
                }
                if(spriteNum ==3){
                    img = down3;
                }

                break;
            case "left":
                if(spriteNum==1){
                    img = left1;
                }
                if(spriteNum ==2){
                    img = left2;
                }
                if(spriteNum ==3){
                    img = left3;
                }
                break;
            case "right":
                if(spriteNum==1){
                    img = right1;
                }
                if(spriteNum ==2){
                    img = right2;
                }
                if(spriteNum ==3){
                    img = right3;
                }

                break;
            default:
                img = down1;
                break;
        }
        //draw player
//        int newWidth = (int) (img.getWidth()*scaleFactor * gp.scale);
//        int newHeight = (int) (img.getHeight()*scaleFactor * gp.scale);

        g2d.drawImage(img,screenX,screenY,img.getWidth() * gp.scale,img.getHeight() * gp.scale, null);

        //drawing collision rectangle"
        g2d.setColor(Color.RED);
        int solidAreaScreenX = screenX + solidArea.x;
        int solidAreaScreenY = screenY + solidArea.y;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        g2d.drawRect(solidAreaScreenX, solidAreaScreenY, solidAreaWidth, solidAreaWidth);

    }
}
