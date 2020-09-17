package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.widgets.Widget;

public class VerticalLayout3 extends OneDimensionalLayout3<VerticalLayout3> {

    private VerticalLayoutPolicy verticalLayoutPolicy = VerticalLayoutPolicy.CHILD_PREFERRED_WIDTH;

    private boolean dynamicChildHeight = true;
    private float fixedHeight;

    // --------------------------------------------------------------------------------

    public VerticalLayout3 withChildExpansionPolicy(VerticalLayoutPolicy policy) {
        this.verticalLayoutPolicy = policy;
        return this;
    }

    public VerticalLayout3 withFixedRowHeight(float height) {
        this.dynamicChildHeight = false;
        this.fixedHeight = height;
        return this;
    }

    private float totalVerticalSpacing() { return (children.isEmpty()) ? 0.0f : (children.size() - 1) * verticalSpacing; }

    // --------------------------------------------------------------------------------

    @Override
    public void computeLayout() {
        float x = getContentLeft();
        float y = getContentTop();

        float maxChildWidth = maxChildPrefWidth();
        float sumChildHeight = (dynamicChildHeight) ? sumChildPrefHeight() : (children.size() * fixedHeight);
        sumChildHeight += totalVerticalSpacing();

        // Obey the global anchor position
        if (globalChildAnchor.isCentralX())
            x = getContentCenterX();
        else if (globalChildAnchor.isRight())
            x = getContentRight();

        if (globalChildAnchor.isCentralY())
            y = getContentCenterY() + (sumChildHeight * 0.5f);
        else if (globalChildAnchor.isBottom())
            y = getContentBottom() + sumChildHeight;

        // Update each child's position
        for (Widget child : children) {
            float width = getContentWidth();
            float height = (dynamicChildHeight) ? child.getPrefHeight() : fixedHeight;

            if (globalChildAnchor.isCentralY())
                y -= (height * 0.5f);
            else if (globalChildAnchor.isBottom())
                y -= height;

            switch (verticalLayoutPolicy) {
                case CHILD_PREFERRED_WIDTH:
                    child.setGrowthPolicy(GrowthPolicy.PREFERRED_BOTH);
                    break;
                case CHILD_EXPAND_WIDTH_TO_FULL:
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_X);
                    break;
                case CHILD_EXPAND_WIDTH_TO_MAX:
                    width = maxChildWidth;
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_X);
                    break;
            }

            // Move into position
            child.setActualFromAnchor(x, y, width, height, globalChildAnchor);
            child.setContentAnchorPosition(globalChildAnchor);

            // Update the y value for the next child (beneath the current one)
            if (globalChildAnchor.isTop())
                y -= height;
            else if (globalChildAnchor.isCentralY())
                y -= (height * 0.5f);

            y -= verticalSpacing;
        }
    }

    @Override public float getPrefWidth() { return maxChildPrefWidth(); }
    @Override public float getPrefHeight() { return sumChildPrefHeight() + totalVerticalSpacing(); }
}

