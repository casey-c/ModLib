package ui.screens;

/*

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import ui.layouts.HorizontalLayout;
import ui.layouts.Layout;
import ui.layouts.VerticalLayout;
import utils.ColorHelper;
import utils.TextureHelper;
import utils.TextureManager;

public class LargeScreen<T extends Layout<T>> extends AbstractScreen<T> {
    //private static final Texture SCREEN_TRIM = TextureManager.getTexture("ModLib", "SCREEN_LARGE_TRIM");
    private static final Texture SCREEN_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_LARGE_TRIM);

    protected float screenOpacity = 0.8f;
    protected Color trimColor;

    public LargeScreen() {
        //this.SCREEN_BG = TextureManager.getTexture("ModLib", "SCREEN_LARGE_BASE");
        this.SCREEN_BG = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_LARGE_BASE);
        this.trimColor = Settings.CREAM_COLOR;

        this.SCREEN_W = SCREEN_BG.getWidth();
        this.SCREEN_H = SCREEN_BG.getHeight();
        //computeCenteredContentLocations();

        //mainLayout = new VerticalLayout(
//        mainLayout = new VerticalLayout(
//                CONTENT_MIN_X, CONTENT_MAX_Y,
//                SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
//                SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y,
//                40.0f
//        );

        BaseMod.subscribe(this);
    }

    @Override
    protected void renderScreenBackground(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, screenOpacity));
        sb.draw(SCREEN_BG,
                getScreenLeft() * Settings.scale,
                getScreenBottom() * Settings.scale,
                SCREEN_W,
                SCREEN_H
        );

        //sb.setColor(trimColor);
        sb.setColor(ColorHelper.rainbowColor());
        sb.draw(SCREEN_TRIM,
                getScreenLeft() * Settings.scale,
                getScreenBottom() * Settings.scale,
                SCREEN_W,
                SCREEN_H
        );
    }
}

 */
