package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.AnchorPosition;

public abstract class ScreenWidget {
    private float x, y, prefWidth, prefHeight;
    protected AnchorPosition anchorPosition;

    // --------------------------------------------------------------------------------
    // Getters
    // --------------------------------------------------------------------------------

    public float getPrefWidth() { return prefWidth; }
    public float getPrefHeight() { return prefHeight; }

    public float getLeft() { return x; }
    public float getRight() { return x + prefWidth; }
    public float getTop() { return y + prefHeight; }
    public float getBottom() { return y; }

//    public float getBottomLeftX() { return x; }
//    public float getBottomLeftY() { return y; }
//
//    public float getTopLeftX() { return x; }
//    public float getTopLeftY() { return y + prefHeight; }
//
//    public float getTopRightX() { return x + prefWidth; }
//    public float getTopRightY() { return y + prefHeight; }
//
//    public float getBottomRightX() { return x + prefWidth; }
//    public float getBottomRightY() { return y; }

    // --------------------------------------------------------------------------------
    // Setters
    // --------------------------------------------------------------------------------
    public void setPrefWidth(float w) { this.prefWidth = w; }
    public void setPrefHeight(float h) { this.prefHeight = h; }
    public void setPrefWidthHeight(float w, float h) {
        setPrefWidth(w);
        setPrefHeight(h);
    }

    public void setBottomLeftX(float x) { this.x = x; }
    public void setBottomLeftY(float y) { this.y = y; }
    public void setBottomLeft(float x, float y) {
        setBottomLeftX(x);
        setBottomLeftY(y);
    }

    public void setBottomRightX(float x) { this.x = x - prefWidth; }
    public void setBottomRightY(float y) { this.y = y; }
    public void setBottomRight(float x, float y) {
        setBottomRightX(x);
        setBottomRightY(y);
    }

    public void setTopLeftX(float x) { this.x = x; }
    public void setTopLeftY(float y) { this.y = y - prefHeight; }
    public void setTopLeft(float x, float y) {
        setTopLeftX(x);
        setTopLeftY(y);
    }

    public void setTopRightX(float x) { this.x = x - prefWidth; }
    public void setTopRightY(float y) { this.y = y - prefHeight; }
    public void setTopRight(float x, float y) {
        setTopRightX(x);
        setTopRightY(y);
    }

    // --------------------------------------------------------------------------------

    // Debug
    public void print() {
        System.out.println("SCREEN WIDGET: (" + getLeft() + ", " + getBottom() + ")  --> (" + getRight() + ", " + getTop() + ")");
    }

    // --------------------------------------------------------------------------------

    public abstract void render(SpriteBatch sb);
}
