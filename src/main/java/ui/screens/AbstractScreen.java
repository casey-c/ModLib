package ui.screens;

import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import ui.layouts.Layout;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public abstract class AbstractScreen<T extends Layout<T>> implements RenderSubscriber {
    protected boolean visible;

    protected Texture SCREEN_BG;
    protected float SCREEN_W, SCREEN_H;

    protected float CONTENT_PADDING_LEFT = 60.0f;
    protected float CONTENT_PADDING_RIGHT = 60.0f;
    protected float CONTENT_PADDING_TOP = 60.0f;
    protected float CONTENT_PADDING_BOTTOM = 60.0f;

    protected float getScreenLeft() { return ((float) Settings.WIDTH - SCREEN_W) * 0.5f; }
    protected float getScreenRight() { return getScreenLeft() + SCREEN_W; }
    protected float getScreenBottom() { return ((float) Settings.HEIGHT - SCREEN_H) * 0.5f; }
    protected float getScreenTop() { return getScreenBottom() + SCREEN_H; }

    protected float getContentLeft() { return getScreenLeft() + CONTENT_PADDING_LEFT; }
    protected float getContentRight() { return getScreenRight() - CONTENT_PADDING_RIGHT; }

    protected float getContentBottom() { return getScreenBottom() + CONTENT_PADDING_BOTTOM; }
    protected float getContentTop() { return getScreenTop() - CONTENT_PADDING_TOP; }

    protected float getContentCenterX() { return getContentLeft() + (getContentWidth() * 0.5f); }
    protected float getContentCenterY() { return getContentBottom() + (getContentHeight() * 0.5f); }

    protected float getScreenWidth() { return SCREEN_W; }
    protected float getContentWidth() { return SCREEN_W - CONTENT_PADDING_LEFT - CONTENT_PADDING_RIGHT; }

    protected float getScreenHeight() { return SCREEN_H; }
    protected float getContentHeight() { return SCREEN_H - CONTENT_PADDING_TOP - CONTENT_PADDING_BOTTOM; }

    // --------------------------------------------------------------------------------

    protected T mainLayout;
    protected ArrayList<ScreenWidget> activeChildWidgets = new ArrayList<>();

    // --------------------------------------------------------------------------------

    public boolean isVisible() {
        return visible;
    }

    protected void setAllChildrenActive(boolean val) {
        for (ScreenWidget child : activeChildWidgets)
            child.setActive(val);
    }

    public void show() {
        if (visible)
            return;

        visible = true;
        setAllChildrenActive(true);

        ScreenHelper.showCustomScreen("DECK_OPEN");
    }

    public void hide() {
        if (!visible)
            return;

        visible = false;
        setAllChildrenActive(false);

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

    public void setContentPadding(float left, float right, float top, float bottom)  {
        this.CONTENT_PADDING_LEFT = left;
        this.CONTENT_PADDING_TOP = top;
        this.CONTENT_PADDING_RIGHT = right;
        this.CONTENT_PADDING_BOTTOM = bottom;
    }

    public void setContentPadding(float horizontal, float vertical)  {
        this.CONTENT_PADDING_LEFT = horizontal;
        this.CONTENT_PADDING_TOP = vertical;
        this.CONTENT_PADDING_RIGHT = horizontal;
        this.CONTENT_PADDING_BOTTOM = vertical;
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
        System.out.println();
        System.out.println("SCREEN CONTENT (width, height): " + getContentWidth() + ", " + getContentHeight());
        System.out.println("SCREEN CONTENT bottom left (x, y): " + getContentLeft() + ", " + getContentBottom());
        System.out.println("SCREEN CONTENT upper right (x, y): " + getContentRight() + ", " + getContentTop());
        System.out.println();
    }
}
