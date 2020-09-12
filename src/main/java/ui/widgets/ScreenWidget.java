package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.AnchorPosition;

public abstract class ScreenWidget<T extends ScreenWidget> {
    private float x, y;
    private float prefWidth, prefHeight;
    protected AnchorPosition anchorPosition = AnchorPosition.BOTTOM_LEFT;

    // --------------------------------------------------------------------------------
    // Getters
    // --------------------------------------------------------------------------------

    public float getPrefWidth() { return prefWidth; }
    public float getPrefHeight() { return prefHeight; }

    public float getLeft() { return x; }
    public float getRight() { return x + prefWidth; }
    public float getTop() { return y + prefHeight; }
    public float getBottom() { return y; }

    public float getCenterX() { return x + 0.5f * prefWidth; }
    public float getCenterY() { return y + 0.5f * prefHeight; }

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

    public void setCenterLeftX(float x) { this.x = x; }
    public void setCenterLeftY(float y) { this.y = y - (prefHeight * 0.5f); }
    public void setCenterLeft(float x, float y) {
        setCenterLeftX(x);
        setCenterLeftY(y);
    }

    public void setBottomCenterX(float x) { this.x = x - (prefWidth * 0.5f); }
    public void setBottomCenterY(float y) { this.y = y; }
    public void setBottomCenter(float x, float y) {
        setBottomCenterX(x);
        setBottomCenterY(y);
    }

    public void setCenterRightX(float x) { this.x = x - prefWidth; }
    public void setCenterRightY(float y) { this.y = y - (prefHeight * 0.5f); }
    public void setCenterRight(float x, float y) {
        setCenterRightX(x);
        setCenterRightY(y);
    }

    public void setTopCenterX(float x) { this.x = x - (prefWidth * 0.5f); }
    public void setTopCenterY(float y) { this.y = y - prefHeight; }
    public void setTopCenter(float x, float y) {
        setTopCenterX(x);
        setTopCenterY(y);
    }

    public void setCenter(float x, float y) {
        this.x = x - (prefWidth * 0.5f);
        this.y = y - (prefHeight * 0.5f);
    }

    // Convienience
    public T anchoredAt(float x, float y) {
        return anchoredAt(x, y, AnchorPosition.BOTTOM_LEFT);
    }

    public T anchoredAt(float x,  float y, AnchorPosition pos) {
            this.anchorPosition = pos;

            // Move this layout to the proper spot
            if (pos == AnchorPosition.BOTTOM_LEFT)
                this.setBottomLeft(x, y);
            else if (pos == AnchorPosition.TOP_LEFT)
                this.setTopLeft(x, y);
            else if (pos == AnchorPosition.TOP_RIGHT)
                this.setTopRight(x, y);
            else if (pos == AnchorPosition.BOTTOM_RIGHT)
                this.setBottomRight(x, y);
            else if (pos == AnchorPosition.CENTER_LEFT)
                this.setCenterLeft(x, y);
            else if (pos == AnchorPosition.CENTER_RIGHT)
                this.setCenterRight(x, y);
            else if (pos == AnchorPosition.TOP_CENTER)
                this.setTopCenter(x, y);
            else if (pos == AnchorPosition.BOTTOM_CENTER)
                this.setBottomCenter(x, y);
            else if (pos == AnchorPosition.CENTER)
                this.setCenter(x, y);

            return (T)this;
    }

    public void setAnchor(AnchorPosition pos) {
        this.anchorPosition = pos;
    }


    public T withDimensions(float prefWidth, float prefHeight) {
        this.setPrefWidthHeight(prefWidth, prefHeight);
        return (T)this;
    }

    // --------------------------------------------------------------------------------

    // Debug
    public void print() {
        System.out.println( "SCREEN WIDGET: bl(" + getLeft() + ", " + getBottom() +  ")  --> c("  + getCenterX() + ", " + getCenterY() + ")  --> tr("  + getRight() + ", " + getTop() + ")  | (w, h): ("  + getPrefWidth() + ", " + getPrefHeight() + ")");
    }

    // --------------------------------------------------------------------------------

    public abstract void render(SpriteBatch sb);
    public void update() {}

    public void show() {};
    public void hide() {};
}
