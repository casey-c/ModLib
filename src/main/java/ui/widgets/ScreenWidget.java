package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScreenWidget {
    protected float x, y, prefWidth, prefHeight;

    public float getX() { return x; }
    public float getY() { return y; }

    public float getPrefWidth() { return prefWidth; }
    public float getPrefHeight() { return prefHeight; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setXY(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setPrefWidth(float w) { this.prefWidth = w; }
    public void setPrefHeight(float h) { this.prefHeight = h; }
    public void setPrefWidthHeight(float w, float h) {
        setPrefWidth(w);
        setPrefHeight(h);
    }

    public abstract void render(SpriteBatch sb);
}
