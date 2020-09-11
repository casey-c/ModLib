package ui.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import config.Config;
import org.apache.commons.lang3.tuple.Pair;
import ui.widgets.ScreenWidget;
import utils.ColorHelper;
import utils.RenderingHelper;

import java.util.ArrayList;

public abstract class OneDimensionalLayout<T extends OneDimensionalLayout<T>> extends Layout<T> {
    protected float spacing = 0.0f;

    protected float fixedWidth, fixedHeight;
    protected boolean dynamicWidth = true;
    protected boolean dynamicHeight = true;

    //protected ArrayList<Pair<ScreenWidget, AnchorPosition>> children = new ArrayList<>();
    protected ArrayList<ScreenWidget> children = new ArrayList<>();

    // Convenience
    public T addChild(ScreenWidget child) {
        return addChild(child, AnchorPosition.BOTTOM_LEFT);
    }

    public T addChild(ScreenWidget child, AnchorPosition anchor) {
        children.add(child);
        child.setAnchor(anchor);

        recomputeLayout();
        return (T)this;
    }

    public T withSpacing(float s) {
        this.spacing = s;
        return (T)this;
    }

    public T withFixedChildWidth(float w) {
        this.dynamicWidth = false;
        this.fixedWidth = w;
        return (T)this;
    }

    public T withFixedChildHeight(float h) {
        this.dynamicHeight = false;
        this.fixedHeight = h;
        return (T)this;
    }

    public float totalChildrenHeight() {
        float sum = 0.0f;
        for (ScreenWidget child : children) {
            sum += (dynamicHeight) ? child.getPrefHeight() : fixedHeight;
            sum += spacing;
        }

        if (children.size() > 0)
            sum -= spacing;

        return sum;
    }

    public float totalChildrenWidth() {
        float sum = 0.0f;
        for (ScreenWidget child : children) {
            sum += (dynamicWidth) ? child.getPrefWidth() : fixedWidth;
            sum += spacing;
        }

        if (children.size() > 0)
            sum -= spacing;

        return sum;
    }

    public float minChildHeight() {
        float min = 100000.0f;
        for (ScreenWidget child : children) {
            if (child.getPrefHeight() < min)
                min = child.getPrefHeight();
        }
        return min;
    }

    public float minChildWidth() {
        float min = 100000.0f;
        for (ScreenWidget child : children) {
            if (child.getPrefWidth() < min)
                min = child.getPrefWidth();
        }
        return min;
    }

    public float maxChildHeight() {
        float max = 0.0f;
        for (ScreenWidget child : children) {
            if (child.getPrefHeight() > max)
                max = child.getPrefHeight();
        }
        return max;
    }

    public float maxChildWidth() {
        float max = 0.0f;
        for (ScreenWidget child : children) {
            if (child.getPrefWidth() > max)
                max = child.getPrefWidth();
        }
        return max;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (Config.MOD_LIB_DEBUG_MODE) {
            //sb.setColor(ColorHelper.VERY_DIM_BLUE);
            RenderingHelper.renderBox(sb, getLeft(), getBottom(), getPrefWidth(), getPrefHeight(), ColorHelper.VERY_DIM_BLUE);
            //sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());
        }

        for (ScreenWidget child : children)
            child.render(sb);
    }
}
