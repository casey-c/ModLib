package ui.screens;

import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import ui.layouts.Layout;

public abstract class AbstractScreen<T extends Layout<T>> implements RenderSubscriber {
    protected boolean visible;

    protected Texture SCREEN_BG;
    protected float SCREEN_W, SCREEN_H;

    protected float CONTENT_PADDING_X = 60.0f;
    protected float CONTENT_PADDING_Y = 60.0f;

    protected float getScreenLeft() { return ((float) Settings.WIDTH - SCREEN_W) * 0.5f; }
    protected float getScreenRight() { return getScreenLeft() + SCREEN_W; }
    protected float getScreenBottom() { return ((float) Settings.HEIGHT - SCREEN_H) * 0.5f; }
    protected float getScreenTop() { return getScreenBottom() + SCREEN_H; }

    protected float getContentLeft() { return getScreenLeft() + CONTENT_PADDING_X; }
    protected float getContentRight() { return getScreenRight() - CONTENT_PADDING_X; }

    protected float getContentBottom() { return getScreenBottom() + CONTENT_PADDING_Y; }
    protected float getContentTop() { return getScreenTop() - CONTENT_PADDING_Y; }

    protected float getScreenWidth() { return SCREEN_W; }
    protected float getContentWidth() { return SCREEN_W - 2 * CONTENT_PADDING_X; }

    protected float getScreenHeight() { return SCREEN_H; }
    protected float getContentHeight() { return SCREEN_H - 2 * CONTENT_PADDING_Y; }

//    protected float SCREEN_X, SCREEN_Y;
//
//    protected float CONTENT_OUTER_PADDING_X = 60.0f;
//    protected float CONTENT_OUTER_PADDING_Y = 60.0f;
//
//    protected float CONTENT_MIN_X;
//    protected float CONTENT_MAX_X;
//    protected float CONTENT_MIN_Y;
//    protected float CONTENT_MAX_Y;

    protected T mainLayout;

//    protected void computeCenteredContentLocations() {
//        this.SCREEN_X = ((float)Settings.WIDTH - SCREEN_W) / 2.0f;
//        this.SCREEN_Y = ((float)Settings.HEIGHT - SCREEN_H) / 2.0f;
//
//        this.CONTENT_MIN_X = SCREEN_X + CONTENT_OUTER_PADDING_X;
//        this.CONTENT_MAX_X = SCREEN_X + SCREEN_W - CONTENT_OUTER_PADDING_X;
//
//        this.CONTENT_MIN_Y = SCREEN_Y + CONTENT_OUTER_PADDING_Y;
//        this.CONTENT_MAX_Y = SCREEN_Y + SCREEN_H - CONTENT_OUTER_PADDING_Y;
//    }

    public boolean isVisible() {
        return visible;
    }

    public void show() {
        if (visible)
            return;

        visible = true;
        ScreenHelper.showCustomScreen("DECK_OPEN");
    }

    public void hide() {
        if (!visible)
            return;

        visible = false;
        ScreenHelper.closeCustomScreen("DECK_CLOSE");
    }

    public void saveAndClose() { hide(); }
    public void revertAndClose() { hide(); }

    protected void renderScreenBackground(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(SCREEN_BG,
                ((float) Settings.WIDTH - SCREEN_W * Settings.scale) * 0.5f,
                ((float) Settings.HEIGHT - SCREEN_H * Settings.scale) * 0.5f
        );
    }

    protected void renderScreenForeground(SpriteBatch sb) {
        if (mainLayout != null)
            mainLayout.render(sb);
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (!visible)
            return;

        renderScreenBackground(sb);
        renderScreenForeground(sb);
    }

    public void print() {
        System.out.println("SCREEN (width, height): " + getScreenWidth() + ", " + getScreenHeight());
        System.out.println("SCREEN bottom left (x, y): " + getScreenLeft() + ", " + getScreenBottom());
        System.out.println("SCREEN upper right (x, y): " + getScreenRight() + ", " + getScreenTop());
    }
}
