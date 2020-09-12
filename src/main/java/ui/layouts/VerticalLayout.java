package ui.layouts;

import ui.widgets.ScreenWidget;

public class VerticalLayout extends OneDimensionalLayout<VerticalLayout> {
    public static VerticalLayout build(float w, float h) {
        VerticalLayout layout = new VerticalLayout();
        layout.withDimensions(w, h);
        return layout;
    }

    private float getFirstChildHeight() {
        return (children.size() > 0) ? children.get(0).getPrefHeight() : 0.0f;
    }

    @Override
    public void recomputeLayout() {
        updateSync();

        float childPosX = getLeft();

        if (AnchorPosition.isRight(anchorPosition))
            childPosX = getRight() - maxChildWidth();
        else if (AnchorPosition.isCentralX(anchorPosition))
            childPosX = getCenterX() - (maxChildWidth() * 0.5f);

        float childPosY = getBottom() + totalChildrenHeight();
        if (AnchorPosition.isTop(anchorPosition))
            childPosY = getTop();
        else if (AnchorPosition.isCentralY(anchorPosition))
            childPosY = getCenterY() + (totalChildrenHeight() * 0.5f);

        for (ScreenWidget child : children) {
            if (!dynamicHeight) child.setPrefHeight(fixedHeight);
            if (!dynamicWidth) child.setPrefWidth(fixedWidth);

            child.setTopLeft(childPosX, childPosY);

            childPosY -= (dynamicHeight) ? child.getPrefHeight() : fixedHeight;
            childPosY -= spacing;

//            childPosX += (dynamicWidth) ? child.getPrefWidth() : fixedWidth;
//            childPosX += spacing;
        }
    }
}
