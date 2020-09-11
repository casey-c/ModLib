package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.Collection;

// TODO: either add layout specific functions (clearAll? recomputePositions?) or remove this code smell!
public abstract class Layout<T extends Layout<T>> extends ScreenWidget<T> {
    public abstract float totalChildrenHeight();
    public abstract float totalChildrenWidth();

    public abstract float minChildWidth();
    public abstract float maxChildWidth();

    public abstract float minChildHeight();
    public abstract float maxChildHeight();

    public abstract void recomputeLayout();
}
