package lufti.sprites;

import java.awt.image.BufferedImage;

/**
 * @author ubik
 */
public interface SpriteSheet {
    public BufferedImage getSprite(String name, int frame);
    public int getAnimationLength(String name);
}
