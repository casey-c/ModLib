package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class ColorHelper {
    public static final Color TRANSPARENT_WHITE = new Color(1.0f, 1.0f, 1.0f, 0.75f);
    public static final Color SCREEN_BASE_COLOR = new Color(0.133f, 0.165f, 0.169f, 0.8f);
    public static final Color ORANGE_COLOR = new Color(0.847f, 0.361f, 0.11f, 1.0f);

    // --------------------------------------------------------------------------------

    public static final Color VERY_DIM_RED = new Color(1.0f, 0.0f, 0.0f, 0.3f);
    public static final Color VERY_DIM_GREEN = new Color(0.0f, 1.0f, 0.0f, 0.3f);
    public static final Color VERY_DIM_BLUE = new Color(0.0f, 0.0f, 1.0f, 0.3f);

    public static final Color VERY_DIM_YELLOW = new Color(1.0f, 1.0f, 0.0f, 0.3f);
    public static final Color VERY_DIM_MAGENTA = new Color(1.0f, 0.0f, 1.0f, 0.3f);
    public static final Color VERY_DIM_CYAN = new Color(0.0f, 1.0f, 1.0f, 0.3f);

    // --------------------------------------------------------------------------------

    public static final Color BUTTON_DEFAULT_BASE = new Color(0.173f, 0.192f, 0.212f, 1.0f);

    //public static final Color BUTTON_TRIM = new Color(0.129f, 0.133f, 0.137f, 1.0f); // lighter, original version
    public static final Color BUTTON_TRIM = new Color(0.094f, 0.094f, 0.094f, 1.0f);

    public static final Color BUTTON_HOVER_BASE = new Color( 0.329f, 0.365f, 0.408f, 1.0f );
    public static final Color BUTTON_CLICK_BASE = new Color(0.173f, 0.349f, 0.565f, 1.0f);

    public static final Color BUTTON_HIGHLIGHT = new Color(1.0f, 1.0f, 1.0f, 0.5f); // todo: adjust opacity

    // --------------------------------------------------------------------------------

    public static final Color ICON_COLOR = new Color(0.71f, 0.71f, 0.71f,  1.0f);

    public static final Color CANCEL_BUTTON_BASE = new Color(0.553f, 0.251f, 0.251f, 1.0f);
    public static final Color CANCEL_BUTTON_HOVER = new Color(0.694f, 0.345f, 0.345f, 1.0f);
    public static final Color CANCEL_BUTTON_CLICK = new Color(0.761f, 0.212f, 0.212f, 1.0f);

    // --------------------------------------------------------------------------------

    public static Color rainbowColor() {
        float r = (MathUtils.cosDeg((float) (System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        float g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        float b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        return new Color(r, g, b, 1.0f);
    }
}
