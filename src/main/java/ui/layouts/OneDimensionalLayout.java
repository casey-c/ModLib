package ui.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.apache.commons.lang3.tuple.Pair;
import ui.widgets.ScreenWidget;
import utils.ColorHelper;

import java.util.ArrayList;

public abstract class OneDimensionalLayout<T extends OneDimensionalLayout<T>> extends Layout<T> {
    protected float spacing = 0.0f;

    protected boolean dynamicSize = true;
    protected float fixedSize;

    protected ArrayList<Pair<ScreenWidget, AnchorPosition>> children = new ArrayList<>();

    //public <T extends OneDimensionalLayout<T>> OneDimensionalLayout<T> addChild(ScreenWidget child, AnchorPosition anchor) {
    public T addChild(ScreenWidget child, AnchorPosition anchor) {
        children.add(Pair.of(child, anchor));
        recomputeLayout();
        return (T)this;
    }

    //public <T extends OneDimensionalLayout<T>> OneDimensionalLayout<T> withSpacing(float s) {
    public T withSpacing(float s) {
        this.spacing = s;
        return (T)this;
    }

    //public <T extends OneDimensionalLayout<T>> OneDimensionalLayout<T> withFixedChildWidth(float w) {
    public T withFixedChildWidth(float w) {
        this.dynamicSize = false;
        this.fixedSize = w;
        return (T)this;
    }

    protected float totalChildrenHeight() {
        float sum = 0.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            sum += (dynamicSize) ? p.getLeft().getPrefHeight() : fixedSize;
            sum += spacing;
        }

        if (children.size() > 0)
            sum -= spacing;

        return sum;
    }

    protected float totalChildrenWidth() {
        float sum = 0.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            sum += (dynamicSize) ? p.getLeft().getPrefWidth() : fixedSize;
            sum += spacing;
        }

        if (children.size() > 0)
            sum -= spacing;

        return sum;
    }

    protected float minChildHeight() {
        float min = 100000.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            if (p.getLeft().getPrefHeight() < min)
                min = p.getLeft().getPrefHeight();
        }
        return min;
    }

    protected float minChildWidth() {
        float min = 100000.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            if (p.getLeft().getPrefWidth() < min)
                min = p.getLeft().getPrefWidth();
        }
        return min;
    }

    protected float maxChildHeight() {
        float max = 0.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            if (p.getLeft().getPrefHeight() > max)
                max = p.getLeft().getPrefHeight();
        }
        return max;
    }

    protected float maxChildWidth() {
        float max = 0.0f;
        for (Pair<ScreenWidget, AnchorPosition> p : children) {
            if (p.getLeft().getPrefWidth() > max)
                max = p.getLeft().getPrefWidth();
        }
        return max;
    }

    public void recomputeLayout() {}

    @Override
    public void render(SpriteBatch sb) {
        // TODO: move to debug only
        sb.setColor(ColorHelper.VERY_DIM_BLUE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());

        for (Pair<ScreenWidget, AnchorPosition> p : children)
            p.getLeft().render(sb);
    }
}
