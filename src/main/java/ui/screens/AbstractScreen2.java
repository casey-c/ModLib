package ui.screens;

import basemod.BaseMod;
import basemod.interfaces.PostRenderSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.layouts.AnchorPosition;
import ui.widgets.Widget;
import utils.ColorHelper;

public class AbstractScreen2<T extends AbstractScreen2<T>> extends Widget<T> implements RenderSubscriber {
    private boolean visible;
    protected float SCREEN_WIDTH, SCREEN_HEIGHT;

    public AbstractScreen2(float width, float height) {
        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;

        setActualFromAnchor(
                ScreenHelper.getGlobalCenterX(SCREEN_WIDTH),
                ScreenHelper.getGlobalCenterY(SCREEN_HEIGHT),
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                AnchorPosition.BOTTOM_LEFT
        );

        BaseMod.subscribe(this);
    }

    // Convenience

    @Override public float getPrefWidth() { return SCREEN_WIDTH; }
    @Override public float getPrefHeight() { return SCREEN_HEIGHT; }

    // --------------------------------------------------------------------------------

    public void setVisible(boolean val) { this.visible = val; }
    public boolean isVisible() { return visible; }

    @Override
    public void show() {
        if (visible)
            return;

        this.visible = true;
        ScreenHelper.showCustomScreen("DECK_OPEN");
    }

    @Override
    public void hide() {
        if (!visible)
            return;

        this.visible = false;
        ScreenHelper.closeCustomScreen("DECK_CLOSE");
    }

    // --------------------------------------------------------------------------------

    protected void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // TODO: lock behind debug only
        sb.setColor(ColorHelper.VERY_DIM_BLUE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);
    }

    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // TODO: any other widgets on this screen
    }

    @Override public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        renderBackground(sb, bottomLeftX, bottomLeftY, width, height);
        renderForeground(sb, bottomLeftX, bottomLeftY, width, height);
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (!visible)
            return;

        render(sb);
    }
}
