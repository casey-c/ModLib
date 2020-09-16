package ui.layouts;

import ui.GrowthPolicy;
import ui.widgets.Widget;

/*
public class VerticalLayout3 extends OneDimensionalLayout3<VerticalLayout3> {

    public VerticalLayout3() {
        this.childAnchor = AnchorPosition.TOP_LEFT;
    }

    @Override
    protected float totalVerticalSpacing() {
        return (children.isEmpty()) ? 0.0f : (children.size() - 1) * verticalSpacing;
    }

    @Override protected float totalHorizontalSpacing() { return 0; }

    @Override
    public Widget addChild(Widget w, AnchorPosition anchor, GrowthPolicy growthPolicy) {
        children.add(w);
        w.setGrowthPolicy(growthPolicy);
        return w;
    }

    @Override
    public void computeLayout() {
        float x = getContentLeft();
        float y = getContentTop();

        for (Widget child : children) {
            child.setPosition(x, y, childAnchor);

            // TODO: respect growth policies
            y -= child.getPrefHeight();
            y -= verticalSpacing;
            //child.setPosition(x, y, width, height, anchor, policy);
        }
    }
}

 */
