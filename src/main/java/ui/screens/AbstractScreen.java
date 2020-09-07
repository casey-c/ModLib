package ui.screens;

import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import ui.layouts.Layout;

public abstract class AbstractScreen implements RenderSubscriber {
    protected boolean visible;

    protected Texture SCREEN_BG;
    protected float SCREEN_W, SCREEN_H;

    protected float SCREEN_X, SCREEN_Y;

    protected float CONTENT_OUTER_PADDING_X = 10.0f;
    protected float CONTENT_OUTER_PADDING_Y = 10.0f;

    protected float CONTENT_MIN_X;
    protected float CONTENT_MAX_X;
    protected float CONTENT_MIN_Y;
    protected float CONTENT_MAX_Y;

    protected Layout mainLayout;

    protected void computeCenteredContentLocations() {
        this.SCREEN_X = ((float)Settings.WIDTH - SCREEN_W) / 2.0f;
        this.SCREEN_Y = ((float)Settings.HEIGHT - SCREEN_H) / 2.0f;

        this.CONTENT_MIN_X = SCREEN_X + CONTENT_OUTER_PADDING_X;
        this.CONTENT_MAX_X = SCREEN_X + SCREEN_W - CONTENT_OUTER_PADDING_X;

        this.CONTENT_MIN_Y = SCREEN_Y + CONTENT_OUTER_PADDING_Y;
        this.CONTENT_MAX_Y = SCREEN_Y + SCREEN_H - CONTENT_OUTER_PADDING_Y;
    }

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
}
