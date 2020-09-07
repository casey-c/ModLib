package utils;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SpireInitializer
public class TextureManager {
    // Singleton pattern (initialized on startup by ModTheSpire)
    private static class TextureManagerHolder { private static final TextureManager INSTANCE = new TextureManager(); }
    private static TextureManager getInstance() { return TextureManagerHolder.INSTANCE; }
    private TextureManager() {
        textureDatabase = new HashMap<>();
    }
    public static void initialize() { getInstance(); }

    // --------------------------------------------------------------------------------

    private HashMap<String, HashMap<String, Texture>> textureDatabase;
    private static Texture TEX_ERROR;

    public static void registerTexture(String modid, String texName, String texInternalPath) {
        TextureManager instance = getInstance();

        if (!instance.textureDatabase.containsKey(modid)) {
            instance.textureDatabase.put(modid, new HashMap<>());
        }

        HashMap<String, Texture> modTextureMap = instance.textureDatabase.get(modid);
        modTextureMap.put(texName, new Texture(texInternalPath));
    }

    public static Texture getTexture(String modid, String texName) {
        TextureManager instance = getInstance();

        if (instance.textureDatabase.containsKey(modid)) {
            HashMap<String, Texture> modTextureMap = instance.textureDatabase.get(modid);
            if (modTextureMap.containsKey(texName))
                return modTextureMap.get(texName);
        }

        if (TEX_ERROR == null)
            TEX_ERROR = new Texture("ModLib/images/error.png");

        return TEX_ERROR;
    }
}
