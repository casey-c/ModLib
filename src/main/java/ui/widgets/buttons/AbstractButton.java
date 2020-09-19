package ui.widgets.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.widgets.Widget;
import utils.SoundHelper;

import java.util.function.Consumer;

public abstract class AbstractButton<T extends AbstractButton<T>> extends Widget<T> {
    protected Consumer<T> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb;

    protected boolean hovering;

    public void setOnClick(Consumer<T> onClick) { this.onClick = onClick; }
    public void setOnRightClick(Consumer<T> onRightClick) { this.onRightClick = onRightClick; }
    public void setOnHoverEnter(Consumer<T> onHoverEnter) { this.onHoverEnter = onHoverEnter; }
    public void setOnHoverLeave(Consumer<T> onHoverLeave) { this.onHoverLeave = onHoverLeave; }

    public T withOnClick(Consumer<T> onClick) { setOnClick(onClick); return (T)this;}
    public T withOnRightClick(Consumer<T> onRightClick) { setOnRightClick(onRightClick);return (T)this; }
    public T withOnHoverEnter(Consumer<T> onHoverEnter) { setOnHoverEnter(onHoverEnter); return (T) this; }
    public T withOnHoverLeave(Consumer<T> onHoverLeave) { setOnHoverLeave(onHoverLeave); return (T)this; }

    // --------------------------------------------------------------------------------

    public void click() {
        if (onClick != null)
            onClick.accept((T)this);
    }

    public void rightClick() {
        if (onRightClick != null)
            onRightClick.accept((T)this);
    }

    public void hoverEnter() {
        if (onHoverEnter != null)
            onHoverEnter.accept((T)this);
    }

    public void hoverLeave() {
        if (onHoverLeave != null)
            onHoverLeave.accept((T)this);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void update() {
        hb.update();

        // Hover-leave
        if (hovering && !hb.hovered) {
            hoverLeave();
            hovering = false;
        }

        // Hover-start
        if (!hovering && hb.justHovered) {
            SoundHelper.playUIHover();
            hovering = true;
            hoverEnter();
        }

        // Click started
        if (InputHelper.justClickedLeft && hb.hovered) {
            SoundHelper.playUIClick();
            hb.clickStarted = true;
        }

        // Click finished
        if (hb.clicked) {
            hb.clicked = false;
            click();
        }


        // TODO: right click checking
    }

    private boolean hitboxNeedsFixing = true;

    @Override public void show() { hitboxNeedsFixing = true; }
    @Override public void hide() { hb.move(-10000.0f, -10000.0f); }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        if (hitboxNeedsFixing)  {
            hb.width = width;
            hb.height = height;
            hb.move(bottomLeftX + (width * 0.5f), bottomLeftY + (height * 0.5f));

            //System.out.println(getClass().toString() + " just fixed its hitbox, moved to " + bottomLeftX + ", " + bottomLeftY);

            hitboxNeedsFixing = false;
            //print();
        }

    }
}
