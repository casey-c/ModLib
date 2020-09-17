package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.widgets.Widget;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class OneDimensionalLayout3<T extends OneDimensionalLayout3<T>> extends Layout2<T> {
    protected LinkedList<Widget> children = new LinkedList<>();

    protected AnchorPosition globalChildAnchor = AnchorPosition.TOP_LEFT;

    public void setGlobalChildAnchor(AnchorPosition globalChildAnchor) { this.globalChildAnchor = globalChildAnchor; }
    public T withGlobalChildAnchor(AnchorPosition globalChildAnchor) {
        setGlobalChildAnchor(globalChildAnchor);
        return (T)this;
    }

    // --------------------------------------------------------------------------------

    public float max(LinkedList<Widget> list, Function<Widget, Float> func) {
        if (list.isEmpty())
            return 0;

        float max = -100000.0f;
        for (Widget w : list) {
            float val = func.apply(w);
            if (val > max)
                max = val;
        }

        return max;
    }

    public float min(LinkedList<Widget> list, Function<Widget, Float> func) {
        if (list.isEmpty())
            return 0;

        float min = 100000.0f;
        for (Widget w : list) {
            float val = func.apply(w);
            if (val < min)
                min = val;
        }

        return min;
    }

    public float sum(LinkedList<Widget> list, Function<Widget, Float> func) {
        float sum = 0;
        for (Widget w : list)
            sum += func.apply(w);

        return sum;
    }

    // --------------------------------------------------------------------------------

    public float sumChildPrefWidth() { return sum(children, Widget::getPrefWidth); }
    public float sumChildPrefHeight() { return sum(children, Widget::getPrefHeight); }

    public float minChildPrefWidth() { return min(children, Widget::getPrefWidth); }
    public float minChildPrefHeight() { return min(children, Widget::getPrefHeight); }

    public float maxChildPrefWidth() { return max(children, Widget::getPrefWidth); }
    public float maxChildPrefHeight() { return max(children, Widget::getPrefHeight); }

    // --------------------------------------------------------------------------------


//    public float sumChildPrefHeight() {
//        float sum = 0.0f;
//        for (Widget child : children)
//            sum += child.getPrefHeight();
//
//        return sum;
//    }
//
//    public float sumChildPrefWidth() {
//        float sum = 0.0f;
//        for (Widget child : children)
//            sum += child.getPrefWidth();
//
//        return sum;
//    }
//
//
//    public float minChildPrefWidth() {
//        if (children.size() == 0)
//            return 0;
//
//        float min = 100000.0f;
//        for (Widget child : children) {
//            float width = child.getPrefWidth();
//
//            if (width < min)
//                min = width;
//        }
//
//        return min;
//    }
//
//
//    public float maxChildPrefWidth() {
//        if (children.size() == 0)
//            return 0;
//
//        float max = -10000.0f;
//        for (Widget child : children) {
//            float width = child.getPrefWidth();
//
//            if (width > max)
//                max = width;
//        }
//
//        return max;
//    }



//    public float maxChildPrefHeight() {
//        if (children.size() == 0)
//            return 0;
//
//        float max = -10000.0f;
//        for (Widget child : children) {
//            float height = child.getPrefHeight();
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
