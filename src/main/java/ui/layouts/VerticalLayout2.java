package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

        if (layoutPolicy == VerticalLayoutPolicy.PREFERRED_CHILD_WIDTH) {
            for (LayoutObject object : children) {
                //todo
//                if ()
//                object.x = sta
            }
        }
    }
}
