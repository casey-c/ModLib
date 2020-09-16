package ui.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;

/*
public class VerticalLayout2 extends OneDimensionalLayout2 {
    private VerticalLayoutPolicy layoutPolicy;

    public VerticalLayout2(float x, float y, float width, float height, AnchorPosition layoutAnchorPosition, AnchorPosition childrenAnchorPosition) {
        super(x, y, width, height, layoutAnchorPosition, childrenAnchorPosition);
    }

    public enum VerticalLayoutPolicy {
        PREFERRED_CHILD_WIDTH,
        FIXED_CHILD_WIDTH,
        SYNC_CHILD_WIDTH_TO_MAX
    }

    // TODO: margins
    private float getChildrenStartX() {
        if (AnchorPosition.isCentralX(childrenAnchorPosition))
            return getLayoutCenterX() - (totalChildrenWidth() / 2.0f);
        else if (AnchorPosition.isRight(childrenAnchorPosition))
            return getLayoutRight() - totalChildrenWidth();
        else
            return getLayoutLeft();
    }

    private float getChildrenStartY() {
        if (AnchorPosition.isTop(childrenAnchorPosition))
            return getLayoutTop();

        float h = totalChildrenHeight() + totalVerticalSpacing();

        if (AnchorPosition.isCentralY(childrenAnchorPosition))
            return getLayoutCenterY() + (h / 2.0f);
        else
            return getLayoutBottom() + h;
    }

    @Override
    public void computeLayout() {
        // Nothing to do
        if (children.size() == 0)
            return;

        // TODO: margins?
//        float totalHeight = totalChildrenHeight() + totalVerticalSpacing();
//        float totalWidth = totalChildrenWidth();

        float startX = getChildrenStartX();
        float startY = getChildrenStartY();

        for (LayoutObject object : children) {
            object.x = startX;
            object.y = startY;

            startY += object.widget.getPrefHeight();
            startY += verticalSpacing;
        }
    }

    @Override
    public void renderFixed(SpriteBatch sb, float x, float y, AnchorPosition pos, float width, float height) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, x, y, width, height);

//        for (LayoutObject child : children) {
//            child.widget.renderFixedWidth(sb, child.x, child.y, child.anchorPosition, width);
//        }

    }
}

 */
