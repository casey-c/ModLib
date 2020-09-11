package ui.layouts;

import org.apache.commons.lang3.tuple.Pair;
import ui.screens.AbstractScreen;
import ui.widgets.ScreenWidget;

public class HorizontalLayout2 extends OneDimensionalLayout<HorizontalLayout2> {
    public static HorizontalLayout2 build(float w, float h) {
        HorizontalLayout2 layout = new HorizontalLayout2();
        layout.withDimensions(w, h);
        return layout;
    }

    @Override
    public void recomputeLayout() {
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

        System.out.println("OJB: HORIZONTAL LAYOUT-------------------------");
        print();
        for (ScreenWidget child : children) {
            if (!dynamicHeight) child.setPrefHeight(fixedHeight);
            if (!dynamicWidth) child.setPrefWidth(fixedWidth);

            child.setBottomLeft(childPosX, childPosY);

            childPosX += (dynamicWidth) ? child.getPrefWidth() : fixedWidth;
            childPosX += spacing;

            child.print();
        }
        System.out.println("-------------------------");
    }
}
