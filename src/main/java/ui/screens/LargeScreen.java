package ui.screens;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import ui.layouts.VerticalLayout;
import utils.TextureManager;

public class LargeScreen extends AbstractScreen {
    private static final Texture SCREEN_TRIM = TextureManager.getTexture("ModLib", "SCREEN_LARGE_TRIM");

    protected float vertSpacing = 20.0f;
    protected float screenOpacity = 0.8f;
    protected Color trimColor;

    public LargeScreen() {
        this.SCREEN_BG = TextureManager.getTexture("ModLib", "SCREEN_LARGE_BASE");
        this.trimColor = Settings.CREAM_COLOR;

        this.SCREEN_W = SCREEN_BG.getWidth();
        this.SCREEN_H = SCREEN_BG.getHeight();
        computeCenteredContentLocations();

        mainLayout = new VerticalLayout(
                CONTENT_MIN_X, CONTENT_MAX_Y,
                SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
                SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y,
                vertSpacing
        );

        BaseMod.subscribe(this);
    }
}
