package ui.layouts;

import ui.widgets.Widget;

public abstract class Layout<T extends Layout<T>> extends Widget<T> {
    protected float verticalSpacing, horizontalSpacing;

    // --------------------------------------------------------------------------------

//    protected abstract float totalChildrenHeight();
//    protected abstract float totalChildrenWidth();
//
//    protected abstract float minChildWidth();
//    protected abstract float maxChildWidth();
//
//    protected abstract float minChildHeight();
//    protected abstract float maxChildHeight();

    public abstract void computeLayout();

    // --------------------------------------------------------------------------------

    public void setVerticalSpacing(float verticalSpacing) { this.verticalSpacing = verticalSpacing; }
    public T withVerticalSpacing(float spacing) {
        setVerticalSpacing(spacing);
        return (T)this;
    }

    public void setHorizontalSpacing(float horizontalSpacing) { this.horizontalSpacing = horizontalSpacing; }
    public T withHorizontalSpacing(float spacing) {
        setHorizontalSpacing(spacing);
        return (T)this;
    }

    public void setSpacing(float spacing) {
        setVerticalSpacing(spacing);
        setHorizontalSpacing(spacing);
    }

    public T withSpacing(float spacing) {
        setSpacing(spacing);
        return (T)this;
    }

    // --------------------------------------------------------------------------------

}

