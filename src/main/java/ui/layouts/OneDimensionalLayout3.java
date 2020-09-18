package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.Widget;

import java.util.LinkedList;
import java.util.function.Function;

public abstract class OneDimensionalLayout3<T extends OneDimensionalLayout3<T>> extends Layout2<T> {
    protected LinkedList<Widget> children = new LinkedList<>();

    protected AnchorPosition globalChildAnchor = AnchorPosition.TOP_LEFT;

    public void setGlobalChildAnchor(AnchorPosition globalChildAnchor) { this.globalChildAnchor = globalChildAnchor; }
    public T withGlobalChildAnchor(AnchorPosition globalChildAnchor) {
        setGlobalChildAnchor(globalChildAnchor);
        return (T)this;
    }

    // --------------------------------------------------------------------------------

    public final float max(LinkedList<Widget> list, Function<Widget, Float> func) {
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

    public final float min(LinkedList<Widget> list, Function<Widget, Float> func) {
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

    public final float sum(LinkedList<Widget> list, Function<Widget, Float> func) {
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

    public void addChild(Widget widget) {
        children.add(widget);
        widget.setContentAnchorPosition(AnchorPosition.TOP_LEFT);
    }

    public T withChild(Widget widget) {
        addChild(widget);
        return (T)this;
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // todo: add a debug draw

        // render all children of this layout
        for (Widget w : children)
            w.render(sb);
    }
}
