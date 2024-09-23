package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNumber[][];


    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel; //this.gamePanel is the gamePanel in the constructor
        tile = new Tile[10]; //10 is the number of tiles
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldMap01.txt");
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col =0;
            int row=0;

            while(col<gamePanel.maxWorldCol && row<gamePanel.maxWorldRow){
                String line = br.readLine();

                while(col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //all the tiles are loaded here
    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;


            tile[3] = new Tile();
            tile[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;


            tile[5] = new Tile();
            tile[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY -gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                g2d.drawImage(tile[tileNum].tileImage,(int) screenX, (int) screenY ,gamePanel.tileSize, gamePanel.tileSize, null);
            }


            worldCol++;


            if(worldCol == gamePanel.maxWorldCol){
                worldCol =0;
                worldRow++;
            }
        }

    }

}
