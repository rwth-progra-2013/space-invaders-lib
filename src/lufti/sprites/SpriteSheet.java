package lufti.sprites;

import java.awt.image.BufferedImage;

/**
 * @author ubik
 */
public interface SpriteSheet {
    /**
     * Returns the image associated with the given sprite name
     * and frame number.
     * 
     * @param name the name of a sprite
     * @param frame a frame number
     * @return the requested frame of the sprite
     */
    public BufferedImage getSprite(String name, int frame);
    
    /**
     * @param name the name of a sprite
     * @return the number of frames associated with the sprite
     */
    public int getAnimationLength(String name);
}
