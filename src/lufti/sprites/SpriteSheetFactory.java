package lufti.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sanchez
 */
public class SpriteSheetFactory {

	public static SpriteSheet getClassic() throws IOException {
		BufferedImage sheet = ImageIO.read(SpriteSheetFactory.class.getResourceAsStream("/lufti/sprites/classic.png"));
		CompactSpriteSheet spr = new CompactSpriteSheet(sheet);
		
		// Invaders 
		spr.addSprite("InvaderA", 24, 10, 11, 8, 0);
		spr.addSprite("InvaderA", 37, 10, 11, 8, 1);

		spr.addSprite("InvaderB", 49, 10, 12, 9, 0);
		spr.addSprite("InvaderB", 62, 10, 12, 9, 1);
		
		spr.addSprite("InvaderC", 39, 0, 8, 8, 0);
		spr.addSprite("InvaderC", 49, 0, 8, 8, 1);
		
		// Ufo
		spr.addSprite("Ufo", 0, 25, 32, 14, 0);
		
		// Bunker
		spr.addSprite("Bunker", 0, 0, 22, 16, 0);
		
		// Player projectile
		spr.addSprite("ProjectileA", 0, 18, 1, 4, 0);

		// Various invader projectiles
		spr.addSprite("ProjectileB", 2, 17, 3, 6, 0);
		spr.addSprite("ProjectileB", 6, 17, 3, 6, 1);
		spr.addSprite("ProjectileB", 10, 17, 3, 6, 2);

		spr.addSprite("Ship", 24, 0, 13, 8, 0);

		return spr.scale(4);
	}
}
