package ui.widgets.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.interactivity.IHasInteractivity;
import ui.interactivity.InteractiveWidgetManager;
import ui.layouts.AnchorPosition;
import ui.widgets.Widget;
import utils.SoundHelper;

import java.util.function.Consumer;

public abstract class AbstractButton<T extends AbstractButton<T>> extends Widget<T> implements IHasInteractivity {
    protected Consumer<T> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb;

    protected boolean hovering;

    protected InteractiveWidgetManager interactiveWidgetManager;

    public AbstractButton(InteractiveWidgetManager interactiveWidgetManager) {
        this.interactiveWidgetManager = interactiveWidgetManager;
        interactiveWidgetManager.track(this);
    }

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
        if (!interactive)
            return;

        if (onClick != null)
            onClick.accept((T)this);
    }

    public void rightClick() {
        if (!interactive)
            return;

        if (onRightClick != null)
            onRightClick.accept((T)this);
    }

    public void hoverEnter() {
        if (!interactive)
            return;

        if (onHoverEnter != null)
            onHoverEnter.accept((T)this);
    }

    public void hoverLeave() {
        if (!interactive)
            return;

        if (onHoverLeave != null)
            onHoverLeave.accept((T)this);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void update() {
        if (!interactive)
            return;

        if (hb == null)
            return;

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

    protected boolean interactive = true;
    @Override public boolean isCurrentlyInteractive() { return interactive; }

    @Override
    public void enableInteractivity() {
        hitboxNeedsFixing = true;
        interactive = true;
    }

    @Override
    public void disableInteractivity() {
        if (hb != null)
            hb.move(-10000.0f, -10000.0f);
        interactive = false;
    }

    private boolean hitboxNeedsFixing = true;
    public void setHitboxNeedsFixing() {
        hitboxNeedsFixing = true;
    }

//    @Override public void show() {}
//    @Override public void hide() {}

//    @Override
//    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
//        super.setActualFromAnchor(x, y, width, height, anchor);
//        //fixHitbox(getLeft(), getBottom(), width, height);
//    }

    public void fixHitbox(float bottomLeftX, float bottomLeftY, float width, float height) {
        if (hb == null) hb = new Hitbox(width, height);

        if (hitboxNeedsFixing)  {
            hb.width = width;
            hb.height = height;
            hb.move(bottomLeftX + (width * 0.5f), bottomLeftY + (height * 0.5f));

            //System.out.println(getClass().toString() + " just fixed its hitbox, moved to " + bottomLeftX + ", " + bottomLeftY);

            hitboxNeedsFixing = false;
            //print();
        }
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        fixHitbox(bottomLeftX, bottomLeftY, width, height);
    }
}
