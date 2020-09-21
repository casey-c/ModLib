package ui.widgets;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;

public abstract class Widget<T extends Widget<T>> {
    protected AnchorPosition contentAnchorPosition = AnchorPosition.CENTER;
    protected GrowthPolicy growthPolicy = GrowthPolicy.PREFERRED_BOTH;

    private float MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM;

    private float actualLeft, actualBottom;
    private float actualWidth, actualHeight;

    // --------------------------------------------------------------------------------
    //  Positioning
    // --------------------------------------------------------------------------------

    public T anchoredAt(Widget parent) {
        setActualFromAnchor(parent.getContentLeft(), parent.getContentBottom(), parent.getContentWidth(), parent.getContentHeight(), AnchorPosition.BOTTOM_LEFT);
        return (T)this;
    }

    public T anchoredAt(float x, float y, float width, float height) {
        setActualFromAnchor(x, y, width, height, AnchorPosition.BOTTOM_LEFT);
        return (T)this;
    }

    public T anchoredAt(float x, float y, float width, float height, AnchorPosition anchor) {
        setActualFromAnchor(x, y, width, height, anchor);
        return (T)this;
    }

    public T anchoredAt(float x, float y, AnchorPosition anchor) {
        setActualFromAnchor(x, y, getPrefWidth(), getPrefHeight(), anchor);
        return (T)this;
    }


    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
        // Dimensions
        this.actualWidth = width;
        this.actualHeight = height;

        // Positions
        if (anchor.isCentralX())
            this.actualLeft = x - (width * 0.5f);
        else if (anchor.isRight())
            this.actualLeft = x - width;
        else
            this.actualLeft = x;

        if (anchor.isCentralY())
            this.actualBottom = y - (height * 0.5f);
        else if (anchor.isTop())
            this.actualBottom = y - height;
        else
            this.actualBottom = y;
    }

    // --------------------------------------------------------------------------------

    public void setContentAnchorPosition(AnchorPosition position) { this.contentAnchorPosition = position; }
    public T withContentAnchorPosition(AnchorPosition position) {
        setContentAnchorPosition(position);
        return (T)this;
    }

    public void setGrowthPolicy(GrowthPolicy policy) { this.growthPolicy = policy; }
    public T withGrowthPolicy(GrowthPolicy policy) {
        setGrowthPolicy(policy);
        return (T)this;
    }

    public GrowthPolicy getGrowthPolicy() { return growthPolicy; }

    // --------------------------------------------------------------------------------
    //  Margins
    // --------------------------------------------------------------------------------

    public T withVerticalMargins(float top, float bottom) {
        setVerticalMargins(top, bottom);
        return (T)this;
    }

    public T withHorizontalMargins(float left, float right) {
        setHorizontalMargins(left, right);
        return (T)this;
    }

    public T withMargins(float left, float right, float top, float bottom) {
        setMargins(left, right, top, bottom);
        return (T)this;
    }

    public T withMargins(float all) {
        setMargins(all);
        return (T)this;
    }

    public void setMargins(float all) { setMargins(all, all, all, all); }
    public void setHorizontalMargins(float left, float right) { setMargins(left, right, MARGIN_TOP, MARGIN_BOTTOM); }
    public void setVerticalMargins(float top, float bottom) { setMargins(MARGIN_LEFT, MARGIN_RIGHT, top, bottom); }

    public void setMargins(float left, float right, float top, float bottom) {
        this.MARGIN_LEFT = left;
        this.MARGIN_RIGHT = right;
        this.MARGIN_TOP = top;
        this.MARGIN_BOTTOM = bottom;
    }

    public void setMarginLeft(float left) { this.MARGIN_LEFT = left; }
    public void setMarginRight(float right) { this.MARGIN_RIGHT = right; }
    public void setMarginTop(float top) { this.MARGIN_TOP = top; }
    public void setMarginBottom(float bottom) { this.MARGIN_BOTTOM = bottom; }

    public float getMarginLeft() { return MARGIN_LEFT; }
    public float getMarginRight() { return MARGIN_RIGHT; }
    public float getMarginTop() { return MARGIN_TOP; }
    public float getMarginBottom() { return MARGIN_BOTTOM; }

    // --------------------------------------------------------------------------------

    public abstract float getPrefWidth();
    public abstract float getPrefHeight();
    public float getActualWidth() { return actualWidth; }
    public float getActualHeight() { return actualHeight; }

    // --------------------------------------------------------------------------------

    public float getLeft() { return actualLeft; }
    public float getBottom() { return actualBottom; }
    public float getRight() { return actualLeft + actualWidth; }
    public float getTop() { return actualBottom + actualHeight; }

    public float getCenterX() { return actualLeft + (actualWidth * 0.5f); }
    public float getCenterY() { return actualBottom + (actualHeight * 0.5f); }

    // --------------------------------------------------------------------------------

    public float getContentLeft() { return getLeft() + MARGIN_LEFT; }
    public float getContentRight() { return getRight() - MARGIN_RIGHT; }
    public float getContentTop() { return getTop() - MARGIN_TOP; }
    public float getContentBottom() { return getBottom() + MARGIN_BOTTOM; }

    // TODO: clamp this to [0, actualWidth] ? -- assuming well formed for now
    public float getContentWidth() { return getContentRight() - getContentLeft(); }
    public float getContentHeight() { return getContentTop() - getContentBottom(); }

    public float getContentCenterX() { return getContentLeft() + (getContentWidth() * 0.5f); }
    public float getContentCenterY() { return getContentBottom() + (getContentHeight() * 0.5f); }

    // --------------------------------------------------------------------------------

    public void show() {}
    public void hide() {}
    public void update() {}
    // --------------------------------------------------------------------------------

    // Debug
    public void print() {
        System.out.println(this.getClass().toString());

        System.out.println("\tACTUAL LEFT: " + getLeft());
        System.out.println("\tACTUAL RIGHT: " + getRight());
        System.out.println("\tACTUAL BOTTOM: " + getBottom());
        System.out.println("\tACTUAL TOP: " + getTop());
        System.out.println();
        System.out.println("\tCONTENT LEFT: " + getContentLeft());
        System.out.println("\tCONTENT RIGHT: " + getContentRight());
        System.out.println("\tCONTENT BOTTOM: " + getContentBottom());
        System.out.println("\tCONTENT TOP: " + getContentTop());
        System.out.println();
        System.out.println("\tPREF WIDTH: " + getPrefWidth());
        System.out.println("\tPREF HEIGHT: " + getPrefHeight());
        System.out.println();
        System.out.println("\tANCHOR: " + contentAnchorPosition.name());
        System.out.println("-----------------------------");
    }

    // --------------------------------------------------------------------------------

    public boolean mustBeRenderedLast() { return false; }

    public void render(SpriteBatch sb) {
        // Dimensions
        float width = (growthPolicy.isExpandingWidth()) ? getContentWidth() : getPrefWidth();
        float height = (growthPolicy.isExpandingHeight()) ? getContentHeight() : getPrefHeight();

        // Position
        float left = getContentLeft();
        float bottom = getContentBottom();

        if (contentAnchorPosition.isCentralX())
            left = getContentCenterX() - (width * 0.5f);
        else if (contentAnchorPosition.isRight())
            left = getContentRight() - width;

        if (contentAnchorPosition.isCentralY())
            bottom = getContentCenterY() - (height * 0.5f);
        else if (contentAnchorPosition.isTop())
            bottom = getContentTop() - height;

        renderAt(sb, left, bottom, width, height);
    }

    public abstract void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height);
}
