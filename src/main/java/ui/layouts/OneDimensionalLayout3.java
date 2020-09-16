package ui.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.GrowthPolicy;
import ui.widgets.Widget;
import utils.ColorHelper;

import java.util.ArrayList;

/*
public abstract class OneDimensionalLayout3<T extends OneDimensionalLayout3<T>> extends Layout2<T> {
    protected ArrayList<Widget> children = new ArrayList<>();
    protected AnchorPosition childAnchor = AnchorPosition.CENTER;

    // --------------------------------------------------------------------------------

    @Override
    protected float totalChildrenHeight() {
        float sum = 0.0f;
        for (Widget w : children)
            sum += w.getPrefHeight();
        return sum;
    }

    @Override
    protected float totalChildrenWidth() {
        float sum = 0.0f;
        for (Widget w : children)
            sum += w.getPrefWidth();
        return sum;
    }

    @Override
    protected float minChildWidth() {
        if (children.isEmpty())
            return 0.0f;

        float min = 100000.0f;
        for (Widget child : children) {
            float w = child.getPrefWidth();
            if (w < min)
                min = w;
        }
        return min;
    }

    @Override
    protected float maxChildWidth() {
        if (children.isEmpty())
            return 0.0f;

        float max = -100000.0f;
        for (Widget child : children) {
            float w = child.getPrefWidth();
            if (w > max)
                max = w;
        }
        return max;
    }

    @Override
    protected float minChildHeight() {
        if (children.isEmpty())
            return 0.0f;

        float min = 100000.0f;
        for (Widget child : children) {
            float h = child.getPrefWidth();
            if (h < min)
                min = h;
        }
        return min;
    }

    @Override
    protected float maxChildHeight() {
        if (children.isEmpty())
            return 0.0f;

        float max = -100000.0f;
        for (Widget child : children) {
            float w = child.getPrefHeight();
            if (w > max)
                max = w;
        }
        return max;
    }

    // --------------------------------------------------------------------------------

    protected abstract float totalVerticalSpacing();
    protected abstract float totalHorizontalSpacing();

    // --------------------------------------------------------------------------------

    @Override public float getPrefWidth() { return minChildWidth() + totalHorizontalSpacing(); }
    @Override public float getPrefHeight() { return minChildHeight() + totalVerticalSpacing(); }

    // --------------------------------------------------------------------------------

    public Widget addChild(Widget w) { return addChild(w, AnchorPosition.CENTER, GrowthPolicy.BOTH_PREFERRED); }
    public Widget addChild(Widget w, AnchorPosition anchor) { return addChild(w, anchor, GrowthPolicy.BOTH_PREFERRED); }

    public abstract Widget addChild(Widget w, AnchorPosition anchor, GrowthPolicy growthPolicy);

    // --------------------------------------------------------------------------------
    // Convenience functions call computeLayout() after the add; should probably be avoided (can be expensive)
//
//    public Widget addChildAndCompute(Widget w) {
//        addChild(w);
//        computeLayout();
//        return w;
//    }
//
//    public Widget addChildAndCompute(Widget w, AnchorPosition anchor) {
//        addChild(w, anchor);
//        computeLayout();
//        return w;
//    }
//
//    public Widget addChildAndCompute(Widget w, AnchorPosition anchor, GrowthPolicy growthPolicy) {
//        addChild(w, anchor, growthPolicy);
//        computeLayout();
//        return w;
//    }

    // --------------------------------------------------------------------------------

    @Override
    public void render(SpriteBatch sb) {
        // todo: scaling, debug only etc.
        sb.setColor(ColorHelper.VERY_DIM_GREEN);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight());

        for (Widget w : children)
            w.render(sb);
    }
}

 */
