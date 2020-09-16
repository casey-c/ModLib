package ui.widgets;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;

public abstract class Widget {
    protected GrowthPolicy growthPolicy;

//    public abstract float getPrefWidth();
//    public abstract float getPrefHeight();
    public float getRealWidth() { return realWidth; }
    public float getRealHeight() { return realHeight; }

    public float getPrefWidth() { return realWidth; }
    public float getPrefHeight() { return realHeight; }

    private float bottomLeftX, bottomLeftY;
    private float realWidth, realHeight;

    // --------------------------------------------------------------------------------

    protected float getLeft() { return bottomLeftX; }
    protected float getRight() { return bottomLeftX + realWidth; }
    protected float getBottom() { return bottomLeftY; }
    protected float getTop() { return bottomLeftY + realHeight; }

    protected float getCenterX() { return bottomLeftX + (realWidth / 2.0f); }
    protected float getCenterY() { return bottomLeftY + (realHeight / 2.0f); }

    // --------------------------------------------------------------------------------

    public void setGrowthPolicy(GrowthPolicy policy) {
        this.growthPolicy = policy;
    }

    public void setRealHeight(float realHeight) {
        this.realHeight = realHeight;
    }

    public void setRealWidth(float realWidth) {
        this.realWidth = realWidth;
    }

    public void setRealDimensions(float realWidth, float realHeight) {
        setRealWidth(realWidth);
        setRealHeight(realHeight);
    }

    // Uses preferred width/height (only sets the anchor)
    public void setPosition(float x, float y, AnchorPosition anchor) {
        // TODO: figure out if i actually want prefwidth or realwidth etc. - need to think about it more
//        this.realWidth = getPrefWidth();
//        this.realHeight = getPrefHeight();

        if (AnchorPosition.isRight(anchor))
            this.bottomLeftX = x - getPrefWidth();
        else if (AnchorPosition.isCentralX(anchor))
            this.bottomLeftX = x - (getPrefWidth() / 2.0f);
        else
            this.bottomLeftX = x;

        if (AnchorPosition.isTop(anchor))
            this.bottomLeftY = y - getPrefHeight();
        else if (AnchorPosition.isCentralY(anchor))
            this.bottomLeftY = y - (getPrefHeight() / 2.0f);
        else
            this.bottomLeftY = y;
    }

    public void setPosition(float x, float y, float width, float height, AnchorPosition anchor) {
        this.realWidth = width;
        this.realHeight = height;

        if (AnchorPosition.isRight(anchor))
            this.bottomLeftX = x - width;
        else if (AnchorPosition.isCentralX(anchor))
            this.bottomLeftX = x - (width / 2.0f);
        else
            this.bottomLeftX = x;

        if (AnchorPosition.isTop(anchor))
            this.bottomLeftY = y - height;
        else if (AnchorPosition.isCentralY(anchor))
            this.bottomLeftY = y - (height / 2.0f);
        else
            this.bottomLeftY = y;
    }

    // --------------------------------------------------------------------------------

    public abstract void render(SpriteBatch sb);

    // --------------------------------------------------------------------------------
    // Convenience functions
    // --------------------------------------------------------------------------------

    /*
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
     */

    // --------------------------------------------------------------------------------

//    public void renderPreferred(SpriteBatch sb, float x, float y, AnchorPosition pos) { renderFixed(sb, x, y, pos, getPrefWidth(), getPrefHeight()); }
//    public void renderFixedWidth(SpriteBatch sb, float x, float y, AnchorPosition pos, float width) { renderFixed(sb, x, y, pos, width, getPrefHeight()); }
//    public void renderFixedHeight(SpriteBatch sb, float x, float y, AnchorPosition pos, float height) { renderFixed(sb, x, y, pos, getPrefWidth(), height); }
//
//    public abstract void renderFixed(SpriteBatch sb, float x, float y, AnchorPosition pos, float width, float height);

    // --------------------------------------------------------------------------------

//    public void showPreferred(float x, float y, AnchorPosition pos) { showFixed(x, y, pos, getPrefWidth(), getPrefHeight()); }
//    public void showFixedWidth(float x, float y, AnchorPosition pos, float width) { showFixed(x, y, pos, width, getPrefHeight()); }
//    public void showFixedHeight(float x, float y, AnchorPosition pos, float height) { showFixed(x, y, pos, getPrefWidth(), height); }
//    public void showFixed(float x, float y, AnchorPosition pos, float width, float height) { }

    public void show() {}
    public void hide() {}
    public void update() {}

    // --------------------------------------------------------------------------------

    // Debug
    public void print() {
        System.out.println("Widget: ");
        System.out.println("\tLEFT: " + getLeft());
        System.out.println("\tRIGHT: " + getRight());
        System.out.println("\tBOTTOM: " + getBottom());
        System.out.println("\tTOP: " + getTop());
        System.out.println();
        System.out.println("\tWIDTH: " + getPrefWidth());
        System.out.println("\tHEIGHT: " + getPrefHeight());
        System.out.println("-----------------------------");
    }
}
