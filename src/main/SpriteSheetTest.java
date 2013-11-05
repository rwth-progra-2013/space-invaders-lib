package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import lufti.sprites.SpriteSheet;
import lufti.sprites.SpriteSheetFactory;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public class SpriteSheetTest {

	private static ArrayList<RenderTask> tasks = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		int width = 800;
		int height = 600;
		SpriteSheet sheet = SpriteSheetFactory.getClassic();
		createRenderTasks(width, height, sheet);
	
		SimpleWindow win = SimpleWindow.create(width, height, 30, Color.BLACK);
		win.getCanvas().addRenderCallback(new Canvas.RenderCallback() {

			@Override
			public void render(Canvas.CanvasPainter pntr) {
				for (RenderTask task : tasks) {
					pntr.drawImage(task.img, task.x, task.y);
					pntr.drawImageBoundary(task.img, task.x, task.y);
				}
			}
		});

	}

	private static void createRenderTasks(int width, int height, SpriteSheet sheet) {
		int margin = 20;
		int x = 2*margin;
		int y = 2*margin;
		for (String spriteName : sheet.getNames()) {
			int frames = sheet.getAnimationLength(spriteName);
			int maxHeight = 0;
			for (int f = 0; f < frames; f++) {
				BufferedImage spr = sheet.getSprite(spriteName, f);
				int h = spr.getHeight();
				int w = spr.getWidth();
				maxHeight = Math.max(h, maxHeight);
				
				tasks.add(new RenderTask(x, y, spr));
			
				x += w+margin;
			}
			
			x = 2*margin;
			y += maxHeight+margin;
		}
	}

	static class RenderTask {
		int x, y;
		BufferedImage img;

		public RenderTask(int x, int y, BufferedImage img) {
			this.x = x;
			this.y = y;
			this.img = img;
		}
	}
}
