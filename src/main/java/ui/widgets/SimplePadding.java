package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimplePadding extends Widget<SimplePadding> {
    private float width, height;

    public SimplePadding(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public static SimplePadding vertical(float height) { return new SimplePadding(1, height); }
    public static SimplePadding horizontal(float width) { return new SimplePadding(width, 1); }

    @Override public float getPrefWidth() { return width; }
    @Override public float getPrefHeight() { return height; }
    @Override public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) { }
}
