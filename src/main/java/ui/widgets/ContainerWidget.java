package ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
public abstract class ContainerWidget<T extends ContainerWidget<T>> extends Widget {
    protected float MARGIN_LEFT, MARGIN_TOP, MARGIN_RIGHT, MARGIN_BOTTOM;

    // --------------------------------------------------------------------------------

    public T withLeftRightMargins(float left, float right) {
        this.MARGIN_LEFT = left;
        this.MARGIN_RIGHT = right;
        return (T)this;
    }

    public T withBottomTopMargins(float bottom, float top) {
        this.MARGIN_BOTTOM = bottom;
        this.MARGIN_TOP = top;
        return (T)this;
    }


    public T withMargins(float left, float right, float bottom, float top) {
        this.MARGIN_LEFT = left;
        this.MARGIN_RIGHT = right;
        this.MARGIN_BOTTOM = bottom;
        this.MARGIN_TOP = top;

        return (T)this;
    }

    // --------------------------------------------------------------------------------

    // TODO: think about using pref vs real

    public float getContentWidth() { return getRealWidth() - MARGIN_LEFT - MARGIN_RIGHT; }
    public float getContentHeight() { return getRealHeight() - MARGIN_BOTTOM - MARGIN_TOP; }

    public float getContentLeft() { return getLeft() + MARGIN_LEFT; }
    public float getContentCenterX() { return getContentLeft() + (getContentWidth() * 0.5f); }
    public float getContentRight() { return getRight() - MARGIN_RIGHT; }

    public float getContentBottom() { return getBottom() + MARGIN_BOTTOM; }
    public float getContentCenterY() { return getContentBottom() + (getContentHeight() * 0.5f); }
    public float getContentTop() { return getTop() - MARGIN_TOP; }

    // --------------------------------------------------------------------------------

    public abstract void render(SpriteBatch sb);

    // --------------------------------------------------------------------------------


    @Override
    public void print() {
        System.out.println("ContainerWidget: ");
        System.out.println("\tContentWidth: " + getContentWidth());
        System.out.println("\tContentHeight: " + getContentHeight());
        System.out.println();
        System.out.println("\tContentLeft: " + getContentLeft());
        System.out.println("\tContentRight: " + getContentRight());
        System.out.println();
        System.out.println("\tContentTop: " + getContentTop());
        System.out.println("\tContentBottom: " + getContentBottom());
        super.print();
    }
}

 */
