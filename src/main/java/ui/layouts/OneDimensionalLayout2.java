package ui.layouts;

import ui.GrowthPolicy;
import ui.widgets.Widget;

import java.util.ArrayList;

/*
public abstract class OneDimensionalLayout2 extends Layout2 {
    protected ArrayList<LayoutObject> children = new ArrayList<>();

    //protected boolean fixedWidth, fixedHeight;
    protected float fixedWidthSize, fixedHejghtSize;
    protected float verticalSpacing, horizontalSpacing;

    protected AnchorPosition childrenAnchorPosition;

    public OneDimensionalLayout2(float x, float y, float width, float height, AnchorPosition layoutAnchorPosition, AnchorPosition childrenAnchorPosition) {
        super(x, y, width, height, layoutAnchorPosition);
        this.childrenAnchorPosition = childrenAnchorPosition;
    }

    // Convenience function
    public Widget addChildAndRecomputeLayout(Widget child, GrowthPolicy policy) {
        addChild(child, policy);
        computeLayout();
        return child;
    }

    public Widget addChild(Widget child, GrowthPolicy policy) {
        children.add(new LayoutObject(child, policy, AnchorPosition.CENTER));
        return child;
    }

    public Widget addChild(Widget child, GrowthPolicy policy, AnchorPosition childAnchor) {
        children.add(new LayoutObject(child, policy, childAnchor));
        return child;
    }


    @Override public float getPrefWidth() { return minChildWidth(); }
    @Override public float getPrefHeight() { return minChildHeight(); }

}

 */
