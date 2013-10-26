package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import lufti.game.AbstractGame;
import lufti.sprites.CompactSpriteSheet;
import lufti.sprites.SpriteSheet;
import lufti.ui.Canvas;

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
        new SimpleGame(spr.scale(4));
    }

    static class SimpleGame extends AbstractGame {

        private SpriteSheet sprites;
        private int x, y;

        public SimpleGame(SpriteSheet spr) {
            x = y = 0;
            sprites = spr;
            setup(800, 600, 60, 60, Color.BLACK);
        }

        @Override
        public void update() {
        }

        @Override
        public void render(Canvas.CanvasPainter pntr) {
            pntr.drawImage(sprites.getSprite("InvaderA", 0), x-20, y-10);
        }

        @Override
        public void mouseMove(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseDown() {
        }
    }
}
