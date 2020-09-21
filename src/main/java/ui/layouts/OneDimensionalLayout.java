package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.Widget;

import java.util.LinkedList;
import java.util.function.Function;

public abstract class OneDimensionalLayout<T extends OneDimensionalLayout<T>> extends Layout<T> {
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

    public <W extends Widget> W addChild(W widget) {
        children.add(widget);
        widget.setContentAnchorPosition(AnchorPosition.TOP_LEFT);
        return widget;
    }

    public T withChild(Widget widget) {
        addChild(widget);
        return (T)this;
    }

    @Override
    public void show() {
        for (Widget child : children)
            child.show();
    }

    @Override
    public void hide() {
        for (Widget child : children)
            child.hide();
    }

    @Override
    public void update() {
        for (Widget child : children)
            child.update();
    }

    public LinkedList<Widget> getChildren() { return children; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // todo: add a debug draw

        // render all children of this layout
        boolean anyPriority = false;
        for (Widget w : children) {
            if (w.mustBeRenderedLast()) {
                anyPriority = true;
                continue;
            }
            else
                w.render(sb);
        }

        if (anyPriority) {
            for (Widget w : children) {
                if (w.mustBeRenderedLast())
                    w.render(sb);
            }
        }

    }
}
