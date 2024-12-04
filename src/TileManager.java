import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public static Tile[][] mapTileNum;
    public static TileImage[] tileImages = new TileImage[6];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new Tile[GamePanel.maxScreenCol][GamePanel.maxScreenRow];
        getTileImage();
        loadMap();

    }

    public void getTileImage() {
        try {
            TileImage t = new TileImage();
            TileImage t1 = new TileImage();
            TileImage t2 = new TileImage();
            TileImage t3 = new TileImage();
            TileImage t4 = new TileImage();
            t.image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/tiles/black.png"))));
            tileImages[0] = t;
            t1.image =ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            tileImages[1] = t1; //
            tileImages[1].collision = true;
            t2.image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/tiles/smallPoint.png"))));
            tileImages[2] = t2;//
            t3.image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/redM.png"))));
            tileImages[3] =t3;
            t4.image =ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigPoint.png"))));
            tileImages[4] =  t4;
            tileImages[5] = t;// ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png"))));
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public static void loadMap() {
        try {

            InputStream is = TileManager.class.getResourceAsStream("/maps/map02.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i <GamePanel.maxScreenRow ; i++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j <GamePanel.maxScreenCol ; j++) {

                    int num = Integer.parseInt(numbers[j]);
                    Tile t = new Tile();
                    t.image = num;
                    t.x = j*GamePanel.tileSize;
                    t.y = i*GamePanel.tileSize;
                    t.w = GamePanel.tileSize*(j+1)-1;
                    t.h = GamePanel.tileSize*(i+1)-1;
                    t.keyX =j;
                    t.keyY = i;
                    t.isBlocked = tileImages[num].collision;
                    mapTileNum[j][i] = t;


                }
            }


            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void draw(Graphics2D g2) {

        for (Tile[] tiles : mapTileNum) {
            for (int j = 0; j < mapTileNum[0].length; j++) {
                Tile tile = tiles[j];

                g2.drawImage(tileImages[tile.image].image, tile.x, tile.y, GamePanel.tileSize, GamePanel.tileSize, null);
            }
        }

    }
}
