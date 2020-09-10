package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class ColorHelper {
    public static final Color TRANSPARENT_WHITE = new Color(1.0f, 1.0f, 1.0f, 0.75f);
    public static final Color SCREEN_BASE_COLOR = new Color(0.133f, 0.165f, 0.169f, 0.8f);
    public static final Color ORANGE_COLOR = new Color(0.847f, 0.361f, 0.11f, 1.0f);

    public static Color rainbowColor() {
        float r = (MathUtils.cosDeg((float) (System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        float g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        float b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        return new Color(r, g, b, 1.0f);
    }
}
