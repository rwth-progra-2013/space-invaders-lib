/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lufti.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.SpriteTest;

/**
 *
 * @author sanchez
 */
public class SpriteSheetFactory {

    public static SpriteSheet getClassic() throws IOException {
	BufferedImage sheet = ImageIO.read(SpriteTest.class.getResourceAsStream("/lufti/sprites/classic.png"));
	CompactSpriteSheet spr = new CompactSpriteSheet(sheet);
	spr.addSprite("InvaderA", 24, 10, 11, 8, 0);
	spr.addSprite("InvaderA", 37, 10, 11, 8, 1);

	// Player projectile
	spr.addSprite("ProjectileA", 0, 16, 1, 5, 0);
		
	// Various invader projectiles
	spr.addSprite("ProjectileB", 2, 16, 3, 7, 0);
	spr.addSprite("ProjectileB", 6, 16, 3, 7, 1);
	spr.addSprite("ProjectileB", 10, 16, 3, 7, 2);
	
	spr.addSprite("Ship", 24, 0, 13, 8, 0);

	return spr.scale(4);
    }
}
