package ui.layouts;

import ui.widgets.ScreenWidget;

public class HorizontalLayout extends OneDimensionalLayout<HorizontalLayout> {
    public static HorizontalLayout build(float w, float h) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.withDimensions(w, h);
        return layout;
    }

    @Override
    public void recomputeLayout() {
        updateSync();

        float childPosX = getLeft();

        if (AnchorPosition.isRight(anchorPosition))
            childPosX = getRight() - totalChildrenWidth();
        else if (AnchorPosition.isCentralX(anchorPosition))
            childPosX = getCenterX() - (totalChildrenWidth() * 0.5f);

        float childPosY = getBottom();
        if (AnchorPosition.isTop(anchorPosition))
            childPosY = getTop() - maxChildHeight();
        else if (AnchorPosition.isCentralY(anchorPosition))
            childPosY = getCenterY() - (maxChildHeight() * 0.5f);

        for (ScreenWidget child : children) {
            if (!dynamicHeight) child.setPrefHeight(fixedHeight);
            if (!dynamicWidth) child.setPrefWidth(fixedWidth);

            child.setBottomLeft(childPosX, childPosY);

            childPosX += (dynamicWidth) ? child.getPrefWidth() : fixedWidth;
            childPosX += spacing;
        }
    }
}
