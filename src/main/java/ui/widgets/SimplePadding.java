package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimplePadding extends ScreenWidget {
    public SimplePadding(float w, float h) {
        setPrefWidthHeight(w, h);
    }

    @Override
    public void render(SpriteBatch sb) { }

    public static SimplePadding vertical(float h) {
        return new SimplePadding(1.0f, h);
    }

    public static SimplePadding horizontal(float w) {
        return new SimplePadding(w, 1.0f);
    }
}
