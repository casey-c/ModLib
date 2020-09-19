package ui.widgets.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.widgets.Widget;
import utils.SoundHelper;

import java.util.function.Consumer;

public abstract class AbstractButton<T extends AbstractButton<T>> extends Widget<T> {
    protected Consumer<AbstractButton> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb;

    protected boolean hovering;

    public void setOnClick(Consumer<AbstractButton> onClick) { this.onClick = onClick; }
    public void setOnRightClick(Consumer<AbstractButton> onRightClick) { this.onRightClick = onRightClick; }
    public void setOnHoverEnter(Consumer<AbstractButton> onHoverEnter) { this.onHoverEnter = onHoverEnter; }
    public void setOnHoverLeave(Consumer<AbstractButton> onHoverLeave) { this.onHoverLeave = onHoverLeave; }

    public T withOnClick(Consumer<AbstractButton> onClick) { setOnClick(onClick); return (T)this;}
    public T withOnRightClick(Consumer<AbstractButton> onRightClick) { setOnRightClick(onRightClick);return (T)this; }
    public T withOnHoverEnter(Consumer<AbstractButton> onHoverEnter) { setOnHoverEnter(onHoverEnter); return (T) this; }
    public T withOnHoverLeave(Consumer<AbstractButton> onHoverLeave) { setOnHoverLeave(onHoverLeave); return (T)this; }

    // --------------------------------------------------------------------------------

    @Override
    public void update() {
        hb.update();

        // Hover-leave
        if (hovering && !hb.hovered) {
            if (onHoverLeave != null)
                onHoverLeave.accept(this);

            hovering = false;
        }

        // Hover-start
        if (!hovering && hb.justHovered) {
            SoundHelper.playUIHover();

            hovering = true;

            if (onHoverEnter != null)
                onHoverEnter.accept(this);
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
                onClick.accept(this);
        }


        // TODO: click checking? right click checking? hover enter/leave, etc. etc.
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

            hitboxNeedsFixing = false;
            print();
        }

    }
}
