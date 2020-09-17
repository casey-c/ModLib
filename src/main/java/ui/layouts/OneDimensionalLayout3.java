package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.widgets.Widget;

import java.util.LinkedList;

public abstract class OneDimensionalLayout3<T extends OneDimensionalLayout3<T>> extends Layout2<T> {
    protected LinkedList<Widget> children = new LinkedList<>();

//    @Override
//    public float totalChildrenHeight() {
//        float sum = 0.0f;
//        for (Widget child : children)
//            sum += (child.getGrowthPolicy().isExpandingHeight()) ? child.getActualHeight() : child.getPrefHeight();
//
//        return sum;
//    }
//
//    @Override
//    public float totalChildrenWidth() {
//        float sum = 0.0f;
//        for (LayoutObject child : children)
//            sum += (GrowthPolicy.isPreferredWidth(child.policy)) ? child.widget.getPrefWidth() : fixedWidthSize;
//
//        return sum;
//    }
//
//    @Override
//    public float minChildWidth() {
//        if (children.size() == 0)
//            return 0;
//
//        float min = 100000.0f;
//        for (LayoutObject child : children) {
//            float width = (GrowthPolicy.isPreferredWidth(child.policy)) ? child.widget.getPrefWidth() : fixedWidthSize;
//
//            if (width < min)
//                min = width;
//        }
//
//        return min;
//    }
//

    public float getMaxChildWidth() {
        if (children.size() == 0)
            return 0;

        float max = -10000.0f;
        for (Widget child : children) {
            float width = child.getPrefWidth();

            if (width > max)
                max = width;
        }

        return max;
    }
//
//    @Override
//    public float minChildHeight() {
//        if (children.size() == 0)
//            return 0;
//
//        float min = 100000.0f;
//        for (LayoutObject child : children) {
//            float height = (GrowthPolicy.isPreferredHeight(child.policy)) ? child.widget.getPrefHeight() : fixedHejghtSize;
//
//            if (height < min)
//                min = height;
//        }
//
//        return min;
//    }
//
//    @Override
//    public float maxChildHeight() {
//        if (children.size() == 0)
//            return 0;
//
//        float max = -10000.0f;
//        for (LayoutObject child : children) {
//            float height = (GrowthPolicy.isPreferredHeight(child.policy)) ? child.widget.getPrefHeight() : fixedHejghtSize;
//
//            if (height > max)
//                max = height;
//        }
//
//        return max;
//    }

//    protected float totalHorizontalSpacing() { return (children.size() > 0) ? (children.size() - 1) * horizontalSpacing : 0; }
//    protected float totalVerticalSpacing() { return (children.size() > 0) ? (children.size() - 1) * verticalSpacing : 0; }

}
