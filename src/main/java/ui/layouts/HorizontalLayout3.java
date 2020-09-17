package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.widgets.Widget;

public class HorizontalLayout3 extends OneDimensionalLayout3<HorizontalLayout3> {

    private HorizontalLayoutPolicy horizontalLayoutPolicy = HorizontalLayoutPolicy.CHILD_PREFERRED_HEIGHT;

    private boolean dynamicChildWidth = true;
    private float fixedWidth;

    // --------------------------------------------------------------------------------

    public HorizontalLayout3 withChildExpansionPolicy(HorizontalLayoutPolicy policy) {
        this.horizontalLayoutPolicy = policy;
        return this;
    }

    public HorizontalLayout3 withFixedColumnWidth(float width) {
        this.dynamicChildWidth = false;
        this.fixedWidth = width;
        return this;
    }

    private float totalHorizontalSpacing() { return (children.isEmpty()) ? 0.0f : (children.size() - 1) * horizontalSpacing; }

    // --------------------------------------------------------------------------------


    @Override
    public void computeLayout() {
        float x = getContentLeft();
        float y = getContentTop();

        float maxChildHeight = maxChildPrefHeight();
        float sumChildWidth = (dynamicChildWidth) ? sumChildPrefWidth() : (children.size() * fixedWidth);
        sumChildWidth += totalHorizontalSpacing();

        // Obey the global anchor position
        if (globalChildAnchor.isCentralX())
            x = getContentCenterX() - (sumChildWidth * 0.5f);
        else if (globalChildAnchor.isRight())
            x = getContentRight() - sumChildWidth;

        if (globalChildAnchor.isCentralY())
            y = getContentCenterY();
        else if (globalChildAnchor.isBottom())
            y = getContentBottom();

        // Update each child's position
        for (Widget child : children) {
            float width = (dynamicChildWidth) ? child.getPrefWidth() : fixedWidth;
            float height = getContentHeight();

            if (globalChildAnchor.isCentralX())
                x += (width * 0.5f);
            else if (globalChildAnchor.isRight())
                x += width;

            switch (horizontalLayoutPolicy) {
                case CHILD_PREFERRED_HEIGHT:
                    child.setGrowthPolicy(GrowthPolicy.PREFERRED_BOTH);
                    break;
                case CHILD_EXPAND_HEIGHT_TO_FULL:
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_Y);
                    break;
                case CHILD_EXPAND_HEIGHT_TO_MAX:
                    height = maxChildHeight;
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_Y);
                    break;
            }

            // Move into position
            child.setActualFromAnchor(x, y, width, height, globalChildAnchor);
            child.setContentAnchorPosition(globalChildAnchor);

            // Update the y value for the next child (beneath the current one)
            if (globalChildAnchor.isLeft())
                x += width;
            else if (globalChildAnchor.isCentralX())
                x += (width * 0.5f);

            x += horizontalSpacing;
        }

    }

    // --------------------------------------------------------------------------------

    @Override public float getPrefWidth() { return sumChildPrefWidth() + totalHorizontalSpacing(); }
    @Override public float getPrefHeight() { return maxChildPrefHeight(); }
}
