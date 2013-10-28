package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import lufti.game.AbstractGame;
import lufti.sprites.CompactSpriteSheet;
import lufti.sprites.SpriteSheet;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public class GameTest {

    public static void main(String args[]) throws IOException {
        BufferedImage sheet = ImageIO.read(SpriteTest.class.getResourceAsStream("/lufti/sprites/classic.png"));
        CompactSpriteSheet spr = new CompactSpriteSheet(sheet);
        spr.addSprite("InvaderA", 24, 10, 11, 8, 0);
        spr.addSprite("InvaderA", 37, 10, 11, 8, 1);

        spr.addSprite("Projectile", 2, 16, 3, 7, 0);
        spr.addSprite("Projectile", 6, 16, 3, 7, 1);
        spr.addSprite("Projectile", 10, 16, 3, 7, 2);
        new SimpleGame(spr.scale(4));
    }

    static class SimpleGame extends AbstractGame {

        private ArrayList<Point> bullets = new ArrayList<>();
        private ArrayList<Point> bulletQueue = new ArrayList<>();

        private SpriteSheet sprites;
        private int x, y;
        private int ticker;

        boolean rendering = false;
        
        public SimpleGame(SpriteSheet spr) {
            x = y = 0;
            ticker = 0;
            sprites = spr;
	    SimpleWindow window = SimpleWindow.create(800, 600, 60, Color.BLACK);
            setup(window, 60);
        }

        @Override
        public void update() {
            assert( !rendering );
            bullets.addAll(bulletQueue);
            bulletQueue.clear();

            ListIterator<Point> listIterator = bullets.listIterator();
            while (listIterator.hasNext()) {
                Point bullet = listIterator.next();
                bullet.y += 5;
                if (bullet.y > 600) {
                    listIterator.remove();
                }
            }

            ticker++;
        }

        @Override
        public void render(Canvas.CanvasPainter pntr) {
            rendering = true;
            pntr.drawImage(sprites.getSprite("InvaderA", 0), x - 20, y - 10);

            for (Point bullet : bullets) {
                pntr.drawImage(sprites.getSprite("Projectile", (ticker / 2) % 3), bullet.x - 1, bullet.y);
            }
            rendering = false;
        }

        @Override
        public void mouseMove(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseDown() {
            bulletQueue.add(new Point(x - 2, y + 8));
        }
    }
}
