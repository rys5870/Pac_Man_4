import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(isOpaque);
    }

    static final int originalTileSize = 4;
    static final int scale = 4;
    static public final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 35;
    public static final int maxScreenRow = 38;

    public static final int ScreenWidth = tileSize * maxScreenCol;
    public static final int ScreenHeight = tileSize * maxScreenRow;
    Thread gameThread;
    public TileManager tileM = new TileManager(this);
    public Graphics2D g2;
    KeyHandler keyH = new KeyHandler();
    public static int life = 3;
    public static int timer = 0;
    GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth + 100, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                timer += 100;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            update();
            repaint();
        }

    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        tileM.draw(g2);
       // player.draw(g2);
      //  blinky.draw(g2);
      //  pinky.draw(g2);
      //  inky.draw(g2);

        g2.dispose();
    }
    private void update() {

    }
}
