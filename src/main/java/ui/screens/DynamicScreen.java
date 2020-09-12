package ui.screens;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.layouts.Layout;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class DynamicScreen<T extends Layout<T>> extends AbstractScreen<T> {
    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_BASE);
    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_TRIM);
    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_EDGE_TRIM);
    private static final Texture TEX_CENTER = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CENTER);

    private final int CORNER_SIZE = 100;

    private int centerWidth, centerHeight;

    // Minimum size (each corner piece is 100x100, so no edges/no center = 100+100 in each dimension)
    private float atLeastCornerSize(int input) {
        return (input < (CORNER_SIZE * 2)) ? CORNER_SIZE * 2 : (float)input;
    }

    public DynamicScreen(int width, int height) {
        this.SCREEN_W = atLeastCornerSize(width);
        this.SCREEN_H = atLeastCornerSize(height);

        this.centerWidth = (int)SCREEN_W - (CORNER_SIZE * 2);
        this.centerHeight = (int)SCREEN_H - (CORNER_SIZE * 2);

        BaseMod.subscribe(this);
    }

    public Color getBaseColor() {
        return ColorHelper.SCREEN_BASE_COLOR;
    }

    public Color getTrimColor() {
        return Settings.CREAM_COLOR;
    }


    // TODO: scale based on resolution
    private void easyDraw(SpriteBatch sb, Texture baseTex, Texture trimTex, float x, float y, int width, int height, float rot, boolean flipX, boolean flipY) {
        // Base
        sb.setColor(getBaseColor());
        sb.draw(baseTex,
                x, y,
                CORNER_SIZE / 2, CORNER_SIZE / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );

        // Trim
        sb.setColor(getTrimColor());
        sb.draw(trimTex,
                x, y,
                CORNER_SIZE / 2, CORNER_SIZE / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );
    }

    @Override
    protected void renderScreenBackground(SpriteBatch sb) {
        float left = getScreenLeft();
        float bottom = getScreenBottom();

        RenderingHelper.renderDynamicPieces(sb,
                TEX_CORNER_BASE,
                TEX_CORNER_TRIM,
                TEX_EDGE_TRIM,
                left,
                bottom,
                left + CORNER_SIZE,
                left + CORNER_SIZE + centerWidth,
                bottom + CORNER_SIZE,
                bottom + CORNER_SIZE + centerHeight,
                centerWidth,
                centerHeight,
                getBaseColor(),
                getTrimColor(),
                CORNER_SIZE);


        // CORNERS
//        easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, left, bottom, CORNER_SIZE, CORNER_SIZE, 90.0f, false, false); // bottom left
//        easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, left, centerEndY, CORNER_SIZE, CORNER_SIZE, 0.0f, false, false); // top left
//        easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, centerEndX, bottom, CORNER_SIZE, CORNER_SIZE, 90.0f, false, true); // bottom right
//        easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, centerEndX, centerEndY, CORNER_SIZE, CORNER_SIZE, 0.0f, true, false); // top right
//
//        // EDGES
//        easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, left, centerStartY, centerHeight, CORNER_SIZE, 90.0f, false, false); // left
//        easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, centerStartX, bottom, centerWidth, CORNER_SIZE, 0.0f, false, true); // bottom
//        easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, centerEndX, centerStartY, centerHeight, CORNER_SIZE, 90.0f, false, true); // right
//        easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, centerStartX, centerEndY, centerWidth, CORNER_SIZE, 0.0f, false, false); // top
//
//        // CENTER
//        // TODO: scale based on resolution
//        sb.setColor(getBaseColor());
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, centerStartX, centerStartY, centerWidth, centerHeight);
    }
}
