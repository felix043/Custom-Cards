package customCards.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.GdxRuntimeException;
import customCards.DefaultMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.HashMap;

// Thank you Blank The Evil!

// Welcome to the utilities package. This package is for small utilities that make our life easier.
// You honestly don't need to bother with this unless you want to know how we're loading the textures.


public class TextureLoader {
    private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
    public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());

    /**
     * @param textureString - String path to the texture you want to load relative to resources,
     *                      Example: "theDefaultResources/images/ui/missing_texture.png"
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided
     */
    public static Texture getTexture(final String textureString) {
        if (textures.get(textureString) == null) {
            try {
                loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                logger.error("Could not find texture: " + textureString);
                return getTexture("customCardsResources/images/ui/missing_texture.png");
            }
        }
        return textures.get(textureString);
    }

    /**
     * Creates an instance of the texture, applies a linear filter to it, and places it in the HashMap
     *
     * @param textureString - String path to the texture you want to load relative to resources,
     *                      Example: "img/ui/missingtexture.png"
     * @throws GdxRuntimeException
     */
    private static void loadTexture(final String textureString) throws GdxRuntimeException {
        logger.info("DefaultMod | Loading Texture: " + textureString);
        Texture texture = new Texture(textureString);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        textures.put(textureString, texture);
    }

    public static String getAnimatedCardTextures(final String cardName) throws FileNotFoundException {
        String fileName = DefaultMod.makeCardPath(cardName + ".png");

        if (!testTexture(fileName)) {
            throw new FileNotFoundException(fileName + " was not found.");
        }
        return fileName;
    }

    public static boolean testTexture(String filePath) {
        return Gdx.files.internal(filePath).exists();
    }

    public static String getCardTextureString(final String cardName) {
        String textureString = DefaultMod.makeCardPath(cardName + ".png");

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = DefaultMod.makeCardPath("NothingToSeeHere.png");
        }

        return textureString;
    }
}
