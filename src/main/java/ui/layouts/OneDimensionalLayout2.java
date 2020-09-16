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

    @Override
    public float totalChildrenHeight() {
        float sum = 0.0f;
        for (LayoutObject child : children)
            sum += (GrowthPolicy.isPreferredHeight(child.policy)) ? child.widget.getPrefHeight() : fixedHejghtSize;

        return sum;
    }

    @Override
    public float totalChildrenWidth() {
        float sum = 0.0f;
        for (LayoutObject child : children)
            sum += (GrowthPolicy.isPreferredWidth(child.policy)) ? child.widget.getPrefWidth() : fixedWidthSize;

        return sum;
    }

    @Override
    public float minChildWidth() {
        if (children.size() == 0)
            return 0;

        float min = 100000.0f;
        for (LayoutObject child : children) {
            float width = (GrowthPolicy.isPreferredWidth(child.policy)) ? child.widget.getPrefWidth() : fixedWidthSize;

            if (width < min)
                min = width;
        }

        return min;
    }

    @Override
    public float maxChildWidth() {
        if (children.size() == 0)
            return 0;

        float max = -10000.0f;
        for (LayoutObject child : children) {
            float width = (GrowthPolicy.isPreferredWidth(child.policy)) ? child.widget.getPrefWidth() : fixedWidthSize;

            if (width > max)
                max = width;
        }

        return max;
    }

    @Override
    public float minChildHeight() {
        if (children.size() == 0)
            return 0;

        float min = 100000.0f;
        for (LayoutObject child : children) {
            float height = (GrowthPolicy.isPreferredHeight(child.policy)) ? child.widget.getPrefHeight() : fixedHejghtSize;

            if (height < min)
                min = height;
        }

        return min;
    }

    @Override
    public float maxChildHeight() {
        if (children.size() == 0)
            return 0;

        float max = -10000.0f;
        for (LayoutObject child : children) {
            float height = (GrowthPolicy.isPreferredHeight(child.policy)) ? child.widget.getPrefHeight() : fixedHejghtSize;

            if (height > max)
                max = height;
        }

        return max;
    }

    protected float totalHorizontalSpacing() {
        return (children.size() > 0) ? (children.size() - 1) * horizontalSpacing : 0;
    }

    protected float totalVerticalSpacing() {
        return (children.size() > 0) ? (children.size() - 1) * verticalSpacing : 0;
    }

    @Override public float getPrefWidth() { return minChildWidth(); }
    @Override public float getPrefHeight() { return minChildHeight(); }

}

 */
