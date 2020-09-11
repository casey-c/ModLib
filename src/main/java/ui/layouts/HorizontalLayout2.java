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
        float childPos = getLeft();

        if (AnchorPosition.isRight(anchorPosition))
            childPos = getRight() - totalChildrenWidth();
        else if (AnchorPosition.isCentralX(anchorPosition))
            childPos = getCenterX() - (totalChildrenWidth() * 0.5f);

        System.out.println("HORIZONTAL LAYOUT anchor: " + anchorPosition.name());
        System.out.println("HORIZONTAL LAYOUT top: " + getTop());
        System.out.println("HORIZONTAL LAYOUT bottom: " + getBottom());
        System.out.println("HORIZONTAL LAYOUT maxChildHeight: " + maxChildHeight());
        System.out.println("HORIZONTAL LAYOUT maxChildWidth: " + maxChildWidth());
        System.out.println("HORIZONTAL LAYOUT total childWidth: " + totalChildrenWidth());
        System.out.println("HORIZONTAL LAYOUT total childHeight: " + totalChildrenHeight());

        float childHeight = getBottom();
        if (AnchorPosition.isTop(anchorPosition))
            childHeight = getTop() - maxChildHeight();
        else if (AnchorPosition.isCentralY(anchorPosition))
            childHeight = getCenterY() - (maxChildHeight() * 0.5f);

        print();
        System.out.println("HORIZONTAL LAYOUT: childPos" + childPos);
        System.out.println("HORIZONTAL LAYOUT: childHeight" + childHeight);

        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            ScreenWidget child = p.getLeft();
            AnchorPosition childAnchor = p.getRight();

            System.out.println("[child in layout loop]");
            child.print();

            if (dynamicSize)
                child.setBottomLeft(childPos, childHeight);
            else
                child.anchoredGivenBottomLeft(childPos, childHeight, childAnchor);

            System.out.println("[child post layout loop]");
            child.print();
            System.out.println();

            childPos += (dynamicSize) ? child.getPrefWidth() : fixedSize;
            childPos += spacing;
            System.out.println("HORIZONTAL LAYOUT (loop): childPos" + childPos);
        }
        System.out.println("-----------");
    }
}
