package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import lufti.game.AbstractGame;
import lufti.sprites.SpriteSheetFactory;
import lufti.game.PlayerInput;
import lufti.sprites.SpriteSheet;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public class GameTest {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String args[]) throws IOException {
	SpriteSheet spr = SpriteSheetFactory.getClassic();
	

	SimpleGame.create(800, 600, spr);
    }

    static class SimpleGame extends AbstractGame {

	private ArrayList<Point> bullets = new ArrayList<>();
	private ArrayList<Point> bulletQueue = new ArrayList<>();
	private SpriteSheet sprites;
	private int x, y, width, height;
	private int ticker;
	boolean rendering = false;

	public SimpleGame(int width, int height, SpriteSheet spr) {
	    x = width / 2;
	    y = height - 75;
	    this.width = width;
	    this.height = height;
	    ticker = 0;
	    sprites = spr;
	}

	public static SimpleGame create(int width, int height, SpriteSheet spr) {
	    SimpleGame res = new SimpleGame(width, height, spr);

	    SimpleWindow window = SimpleWindow.create(width, height, 60, Color.BLACK);
	    AbstractGame.attach(res, window, 60);

	    return res;
	}

	@Override
	public void update() {
	    assert (!rendering);
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
