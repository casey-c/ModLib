package ui.widgets.buttons;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.layouts.AnchorPosition;
import ui.widgets.ScreenWidget;
import utils.SoundHelper;

import java.util.function.Consumer;

// TODO: clarify the differences between ACTIVE, VISIBLE, and ENABLED
//   * active means this widget is subscribed to updates and should process them / ignore them
//   * visible means this widget should be rendered
//   * enabled means a widget has certain display properies / behavior (e.g. a button can be set inactive and not be clickable -- grayed out or something)

public abstract class AbstractButton<T extends AbstractButton<T>> extends ScreenWidget<T> {
    protected Consumer<AbstractButton> onHoverEnter, onHoverLeave, onClick, onRightClick;
    protected Hitbox hb = new Hitbox(0, 0);

    protected AbstractButton() { }

    public AbstractButton(float width, float height) {
        hb.width = width;
        hb.height = height;

        setPrefWidthHeight(width, height);
    }

    @Override
    public T anchoredAt(float x, float y, AnchorPosition pos) {
        super.anchoredAt(x, y, pos);

        System.out.println("OJB: abstract button called anchored at. ");
        print();

        hb.move(getLeft(), getBottom());
        return (T)this;
    }

    public T withOnClick(Consumer<AbstractButton> onClick) {
        setOnClick(onClick);
        return (T)this;
    }

    public void setOnClick(Consumer<AbstractButton> onClick) { this.onClick = onClick; }
    public void setOnHoverEnter(Consumer<AbstractButton> onHoverEnter) { this.onHoverEnter = onHoverEnter; }
    public void setOnHoverLeave(Consumer<AbstractButton> onHoverLeave) { this.onHoverLeave = onHoverLeave; }
    public void setOnRightClick(Consumer<AbstractButton> onRightClick) { this.onRightClick = onRightClick; }

    @Override
    public void update() {
        hb.update();

        if (hb.justHovered) {
            System.out.println("OJB: hb.justHovered");
            SoundHelper.playUIHover();
        }

        if (InputHelper.justClickedLeft && hb.hovered) {
            SoundHelper.playUIClick();
            hb.clickStarted = true;
        }

        if (hb.clicked) {
            hb.clicked = false;

            // Handle
            onClick.accept(this);
        }


        // TODO: click checking? right click checking? hover enter/leave, etc. etc.
    }

    @Override
    public void show() {
        hb.move(getLeft() + (0.5f * getPrefWidth()), getBottom() + (0.5f * getPrefHeight()));
    }

    @Override
    public void hide() {
        hb.move(-10000.0f, -10000.0f);
    }
}
