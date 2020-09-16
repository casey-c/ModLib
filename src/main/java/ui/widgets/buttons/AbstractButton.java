package ui.widgets.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.layouts.AnchorPosition;
import ui.widgets.Widget;
import utils.SoundHelper;

import java.util.function.Consumer;

/*
public abstract class AbstractButton<T extends AbstractButton<T>> extends Widget {
    protected Consumer<T> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb;
    protected boolean hovering;

//    public void setOnClick(Consumer<AbstractButton> onClick) { this.onClick = onClick; }
//    public void setOnHoverEnter(Consumer<AbstractButton> onHoverEnter) { this.onHoverEnter = onHoverEnter; }
//    public void setOnHoverLeave(Consumer<AbstractButton> onHoverLeave) { this.onHoverLeave = onHoverLeave; }
//    public void setOnRightClick(Consumer<AbstractButton> onRightClick) { this.onRightClick = onRightClick; }

    // --------------------------------------------------------------------------------
    // TODO: if I end up making a button factory class, these should probably go there instead?

    public T withOnClick(Consumer<T> onClick) {
        this.onClick = onClick;
        return (T)this;
    }

    public T withOnHoverEnter(Consumer<T> onHoverEnter) {
        this.onHoverEnter = onHoverEnter;
        return (T)this;
    }

    public T withOnHoverLeave(Consumer<T> onHoverLeave) {
        this.onHoverLeave = onHoverLeave;
        return (T)this;
    }

    public T withOnRightClick(Consumer<T> onRightClick) {
        this.onRightClick = onRightClick;
        return (T)this;
    }

    // --------------------------------------------------------------------------------

    @Override public void showFixed(float x, float y, AnchorPosition pos, float width, float height) { hb.move(getCenterX(x, width, pos), getCenterY(y, height, pos)); }
    @Override public void hide() { hb.move(-10000.0f, -10000.0f); }

    // --------------------------------------------------------------------------------

    @Override
    public void update() {
        hb.update();

        // Hover-leave
        if (hovering && !hb.hovered) {
            if (onHoverLeave != null)
                onHoverLeave.accept((T)this);

            hovering = false;
        }

        // Hover-start
        if (!hovering && hb.justHovered) {
            SoundHelper.playUIHover();

            hovering = true;

            if (onHoverEnter != null)
                onHoverEnter.accept((T)this);
        }

        // Click started
        if (InputHelper.justClickedLeft && hb.hovered) {
            SoundHelper.playUIClick();
            hb.clickStarted = true;
        }

        // Click finished
        if (hb.clicked) {
            hb.clicked = false;

            // Handle
            if (onClick != null)
                onClick.accept((T)this);
        }


        // TODO: click checking? right click checking? hover enter/leave, etc. etc.
    }
}

 */
