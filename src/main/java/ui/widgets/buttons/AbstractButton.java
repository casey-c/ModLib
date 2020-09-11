package ui.widgets.buttons;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import ui.widgets.ScreenWidget;

import java.util.function.Consumer;

// TODO: clarify the differences between ACTIVE, VISIBLE, and ENABLED
//   * active means this widget is subscribed to updates and should process them / ignore them
//   * visible means this widget should be rendered
//   * enabled means a widget has certain display properies / behavior (e.g. a button can be set inactive and not be clickable -- grayed out or something)

public abstract class AbstractButton extends ScreenWidget<AbstractButton> implements PostUpdateSubscriber {
    protected Consumer<AbstractButton> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb;

    public AbstractButton(float x, float y, float width, float height) {
        hb = new Hitbox(width, height);
        BaseMod.subscribe(this);
    }

    void onClick() { onClick.accept(this); }
    void onHoverEnter() { onHoverEnter.accept(this); }
    void onHoverLeave() { onHoverLeave.accept(this); }
    void onRightClick() { onRightClick.accept(this); }

    public void setOnClick(Consumer<AbstractButton> onClick) { this.onClick = onClick; }
    public void setOnHoverEnter(Consumer<AbstractButton> onHoverEnter) { this.onHoverEnter = onHoverEnter; }
    public void setOnHoverLeave(Consumer<AbstractButton> onHoverLeave) { this.onHoverLeave = onHoverLeave; }
    public void setOnRightClick(Consumer<AbstractButton> onRightClick) { this.onRightClick = onRightClick; }


    @Override
    public void receivePostUpdate() {
        if (!active)
            return;

        hb.update();

        // TODO: click checking? right click checking? hover enter/leave, etc. etc.
    }
}
