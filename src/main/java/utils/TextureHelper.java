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

        CHECKBOX_BASE("ModLib/images/widgets/checkbox/checkbox_base.png"),
        CHECKBOX_TRIM("ModLib/images/widgets/checkbox/checkbox_trim.png"),
        CHECKBOX_CHECK("ModLib/images/widgets/checkbox/checkbox_check.png"),

        DROPDOWN_CORNER_BASE("ModLib/images/widgets/dropdown/dropdown_corner_base_v4.png"),
        DROPDOWN_CORNER_TRIM("ModLib/images/widgets/dropdown/dropdown_corner_trim_v4.png"),
        DROPDOWN_EDGE_TRIM("ModLib/images/widgets/dropdown/dropdown_edge_trim_v4.png"),
        DROPDOWN_ICON("ModLib/images/widgets/dropdown/dropdown_icon.png"),

        ICON("ModLib/images/icon.png"),
        X_ICON("ModLib/images/widgets/icons/icon_x.png"),
        RESET_ICON("ModLib/images/widgets/icons/icon_reset.png")
        ;

        private final String val;

        private TextureItem(String val) { this.val = val; }
        @Override public String toString() { return val; }

        public Texture get() { return TextureHelper.getTexture(this); }
    }

    public static void registerModTextures() {
        for (TextureItem t : TextureItem.values())
            TextureManager.registerTexture(MOD_ID, t.name(), t.toString());
    }

    public static Texture getTexture(TextureItem id) {
        return TextureManager.getTexture(MOD_ID, id.name());
    }

}
