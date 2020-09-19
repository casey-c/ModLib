package utils;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

public class TextureHelper {
    public static final String MOD_ID = "ModLib";

    public enum TextureItem {
        SCREEN_CORNER_BASE("ModLib/images/screens/screen_corner_base.png"),
        SCREEN_CORNER_TRIM("ModLib/images/screens/screen_corner_trim.png"),
        SCREEN_EDGE_TRIM("ModLib/images/screens/screen_edge_trim.png"),

        BUTTON_CORNER_BASE("ModLib/images/widgets/button/button_corner_base.png"),
        BUTTON_CORNER_TRIM("ModLib/images/widgets/button/button_corner_trim.png"),
        BUTTON_EDGE_TRIM("ModLib/images/widgets/button/button_edge_trim.png"),

        BUTTON_HIGHLIGHT_CORNER("ModLib/images/widgets/button/button_highlight_corner.png"),
        BUTTON_HIGHLIGHT_TOP_EDGE("ModLib/images/widgets/button/button_highlight_top_edge.png"),
        BUTTON_HIGHLIGHT_CENTER("ModLib/images/widgets/button/button_highlight_center.png"),

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
