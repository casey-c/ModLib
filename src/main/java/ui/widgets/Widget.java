package ui.widgets;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.AnchorPosition;

public abstract class Widget {
    public abstract float getPrefWidth();
    public abstract float getPrefHeight();

    // --------------------------------------------------------------------------------
    // Convenience functions
    // --------------------------------------------------------------------------------

    protected float getLeft(float x, float width, AnchorPosition pos) {
        if (AnchorPosition.isRight(pos))
            return x - width;
        else if (AnchorPosition.isCentralX(pos))
            return x - (width / 2.0f);
        else
            return x;
    }

    protected float getRight(float x, float width, AnchorPosition pos) {
        if (AnchorPosition.isLeft(pos))
            return x + width;
        else if (AnchorPosition.isCentralX(pos))
            return x + (width / 2.0f);
        else
            return x;
    }

    protected float getBottom(float y, float height, AnchorPosition pos) {
        if (AnchorPosition.isTop(pos))
            return y - height;
        else if (AnchorPosition.isCentralY(pos))
            return y - (height / 2.0f);
        else
            return y;
    }

    protected float getTop(float y, float height, AnchorPosition pos) {
        if (AnchorPosition.isBottom(pos))
            return y + height;
        else if (AnchorPosition.isCentralY(pos))
            return y + (height / 2.0f);
        else
            return y;
    }

    protected float getCenterX(float x, float width, AnchorPosition pos) {
        if (AnchorPosition.isLeft(pos))
            return x + (width / 2.0f);
        else if (AnchorPosition.isRight(pos))
            return x - (width / 2.0f);
        else
            return x;
    }

    protected float getCenterY(float y, float height, AnchorPosition pos) {
        if (AnchorPosition.isBottom(pos))
            return y + (height / 2.0f);
        else if (AnchorPosition.isTop(pos))
            return y - (height / 2.0f);
        else
            return y;
    }

    // --------------------------------------------------------------------------------

    public void renderPreferred(SpriteBatch sb, float x, float y, AnchorPosition pos) { renderFixed(sb, x, y, pos, getPrefWidth(), getPrefHeight()); }
    public void renderFixedWidth(SpriteBatch sb, float x, float y, AnchorPosition pos, float width) { renderFixed(sb, x, y, pos, width, getPrefHeight()); }
    public void renderFixedHeight(SpriteBatch sb, float x, float y, AnchorPosition pos, float height) { renderFixed(sb, x, y, pos, getPrefWidth(), height); }

    public abstract void renderFixed(SpriteBatch sb, float x, float y, AnchorPosition pos, float width, float height);

    // --------------------------------------------------------------------------------

    public void showPreferred(float x, float y, AnchorPosition pos) { showFixed(x, y, pos, getPrefWidth(), getPrefHeight()); }
    public void showFixedWidth(float x, float y, AnchorPosition pos, float width) { showFixed(x, y, pos, width, getPrefHeight()); }
    public void showFixedHeight(float x, float y, AnchorPosition pos, float height) { showFixed(x, y, pos, getPrefWidth(), height); }
    public void showFixed(float x, float y, AnchorPosition pos, float width, float height) { }

    public void hide() {}

    // --------------------------------------------------------------------------------

    public void update() {}
}
