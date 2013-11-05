package lufti.sprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A single-image sprite sheet.
 *
 * @author ubik
 */
public class CompactSpriteSheet implements SpriteSheet {

	private final BufferedImage source;
	private final HashMap<String, ArrayList<SpriteData>> sprites = new HashMap<>();
	private final HashMap<String, Integer> numFrames = new HashMap<>();

	public CompactSpriteSheet(BufferedImage src) {
		source = src;
	}

	public void addSprite(String name, int x, int y, int w, int h, int frame) {
		if (!sprites.containsKey(name)) {
			sprites.put(name, new ArrayList<SpriteData>());
		}

		ArrayList<SpriteData> dataList = sprites.get(name);
		while (frame >= dataList.size()) {
			dataList.add(null);
		}
		dataList.set(frame, SpriteData.create(x, y, w, h));

		if (!numFrames.containsKey(name)) {
			numFrames.put(name, frame + 1);
		}
		numFrames.put(name, Math.max(numFrames.get(name), frame + 1));
	}

	@Override
	public BufferedImage getSprite(String name, int frame) {
		assert source != null;
		assert sprites.containsKey(name);
		assert frame >= 0 && frame < getAnimationLength(name);

		SpriteData spr = sprites.get(name).get(frame);
		return source.getSubimage(spr.x, spr.y, spr.width, spr.height);
	}

	@Override
	public int getAnimationLength(String name) {
		assert numFrames.containsKey(name) : name + " not contained in sheet";
		return numFrames.get(name);
	}

	public CompactSpriteSheet scale(int factor) {
		assert (factor > 0 && factor < 20);

		BufferedImage resized = new BufferedImage(source.getWidth() * factor, source.getHeight() * factor, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.drawImage(source, 0, 0, resized.getWidth(), resized.getHeight(), 0, 0, source.getWidth(), source.getHeight(), null);
		g.dispose();

		CompactSpriteSheet res = new CompactSpriteSheet(resized);

		for (Map.Entry<String, Integer> entry : numFrames.entrySet()) {
			res.numFrames.put(entry.getKey() + "", entry.getValue());
		}

		for (Map.Entry<String, ArrayList<SpriteData>> entry : sprites.entrySet()) {
			res.sprites.put(entry.getKey() + "", scaleData(factor, entry.getValue()));
		}

		return res;
	}

	@Override
	public List<String> getNames() {
		List<String> res = new ArrayList<>();
		res.addAll(sprites.keySet());
		return res;
	}

	private ArrayList<SpriteData> scaleData(int f, ArrayList<SpriteData> list) {
		ArrayList<SpriteData> res = new ArrayList<>();

		int len = list.size();
		for (int i = 0; i < len; i++) {
			res.add(list.get(i).scale(f));
		}

		return res;
	}

	@Override
	public Dimension getSpriteDimension(String name, int frame) {
		assert sprites.containsKey(name);
		assert frame >= 0 && frame < getAnimationLength(name);
		
		return sprites.get(name).get(frame).getDimension();
	}

	private static class SpriteData {

		private int x, y, width, height;

		private SpriteData() {
		}

		public Dimension getDimension() {
			return new Dimension(width, height);
		}
		
		public SpriteData scale(int factor) {
			return create(x * factor, y * factor, width * factor, height * factor);
		}

		public static SpriteData create(int x, int y, int width, int height) {
			SpriteData res = new SpriteData();
			res.x = x;
			res.y = y;
			res.width = width;
			res.height = height;
			return res;
		}
	}

}
