package ui.layouts;

import ui.widgets.Widget;

public abstract class Layout2 extends Widget {
    // These define the actual space the layout is given -- should be set by the constructor
    private float x, y;
    private float width, height;

    public Layout2(float x, float y, float width, float height, AnchorPosition pos) {
        this.x = getLeft(x, width, pos);
        this.y = getBottom(y, height, pos);
        this.width = width;
        this.height = height;
    }

    public abstract float totalChildrenHeight();
    public abstract float totalChildrenWidth();

    public abstract float minChildWidth();
    public abstract float maxChildWidth();

    public abstract float minChildHeight();
    public abstract float maxChildHeight();

    public abstract void computeLayout();

    // --------------------------------------------------------------------------------

    public float getLayoutLeft() { return x; }
    public float getLayoutCenterX() { return x + (width / 2.0f); }
    public float getLayoutRight() { return x + width; }

    public float getLayoutBottom() { return y; }
    public float getLayoutCenterY() { return y + (height / 2.0f); }
    public float getLayoutTop() { return y + height; }
}
