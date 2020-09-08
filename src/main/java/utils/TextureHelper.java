package utils;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

public class TextureHelper {
    public static final String MOD_ID = "ModLib";

    public enum TextureItem {
        SCREEN_LARGE_BASE("ModLib/images/screens/screen_1000_800_base.png"),
        SCREEN_LARGE_TRIM("ModLib/images/screens/screen_1000_800_trim.png"),

        SCREEN_CORNER("ModLib/images/screens/screen_corner.png"),
        SCREEN_EDGE("ModLib/images/screens/screen_edge.png"),
        SCREEN_CENTER("ModLib/images/screens/screen_center.png"),

        ICON("ModLib/images/icon.png")
        ;

        private final String val;

        private TextureItem(String val) { this.val = val; }
        @Override public String toString() { return val; }
    }

    public static void registerModTextures() {
        for (TextureItem t : TextureItem.values())
            TextureManager.registerTexture(MOD_ID, t.name(), t.toString());
    }

    public static Texture getTexture(TextureItem id) {
        return TextureManager.getTexture(MOD_ID, id.name());
    }

}
