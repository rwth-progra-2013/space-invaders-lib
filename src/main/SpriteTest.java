package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import lufti.sprites.CompactSpriteSheet;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public class SpriteTest {

    public static void main(String args[]) throws IOException {
        SimpleWindow.setLookAndFeel("Nimubs");

        SimpleWindow win = SimpleWindow.create(800, 600, 40, Color.BLACK);
        BufferedImage sheet = ImageIO.read(SpriteTest.class.getResourceAsStream("/lufti/sprites/classic.png"));

        CompactSpriteSheet sprites = new CompactSpriteSheet(sheet);
        sprites.addSprite("InvaderA", 24, 10, 11, 8, 0);
        sprites.addSprite("InvaderA", 37, 10, 11, 8, 1);

        final CompactSpriteSheet scaledSprites = sprites.scale(4);

        final Canvas canvas = win.getCanvas();
        final int[] pos = new int[4];
        pos[0] = 400;
        pos[1] = 0;
        pos[2] = 0;
        pos[3] = 0;

        win.getCanvas().addRenderCallback(new Canvas.RenderCallback() {
            @Override
            public void render(Canvas.CanvasPainter pntr) {
                pntr.drawImage(scaledSprites.getSprite("InvaderA", pos[2]), pos[0], pos[1]);
                pos[1]++;

                if (pos[3]++ > 10) {
                    pos[3] = 0;
                    pos[2] = (pos[2] + 1) % 2;
                }
            }
        });
    }
}
