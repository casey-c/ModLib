package ui.screens;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import ui.layouts.AnchorPosition;
import ui.layouts.Layout;
import utils.ColorHelper;
import utils.TextureHelper;
import utils.TextureManager;

public class DynamicScreen<T extends Layout<T>> extends AbstractScreen<T> {
    private int rowChunks, colChunks;
    private static final int CHUNK_SIZE = 100;

    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_BASE);
    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_TRIM);
    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_EDGE_TRIM);
    private static final Texture TEX_CENTER = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CENTER);

    public DynamicScreen(int width, int height) {
        this.SCREEN_W = toNearestHundred(width);
        this.SCREEN_H = toNearestHundred(height);
        this.rowChunks = (int)SCREEN_W / 100;
        this.colChunks = (int)SCREEN_H / 100;

        BaseMod.subscribe(this);
    }

//    public static <T extends Layout<T>> DynamicScreen<T> build(int width, int height) {
//        DynamicScreen<T> screen = new DynamicScreen<>();
//        return screen.with_dimensions(width, height);
//    }

//    public <T extends Layout<T>> DynamicScreen<T> with_dimensions(int width, int height) {
//
//        return (DynamicScreen<T>) this;
//    }

    // TODO: screen is always centered at the moment; should improve later to allow anchoring
//    public <T extends Layout<T>> DynamicScreen<T> centered() {
//        return (DynamicScreen<T>) this;
//    }

//    public <T extends Layout<T>> DynamicScreen<T> anchoredAt(int x, int y, AnchorPosition pos) {
//        return (DynamicScreen<T>) this;
//    }

    protected int toNearestHundred(float input) {
        int x = (int)Math.floor((input + 50.0f) / 100.0f);
        return x * 100;
    }

    public Color getTrimColor() {
        return Settings.CREAM_COLOR;
    }

    /*
    Draws a rectangle with the bottom left corner at x,y having the given width and height in pixels. The rectangle is
    offset by originX, originY relative to the origin. Scale specifies the scaling factor by which the rectangle should
    be scaled around originX, originY. Rotation specifies the angle of counter clockwise rotation of the rectangle
    around originX, originY. The portion of the Texture given by srcX, srcY and srcWidth, srcHeight is used. These
    coordinates and sizes are given in texels. FlipX and flipY specify whether the texture portion should be flipped
    horizontally or vertically.

Specified by:
    draw in interface Batch
    x - the x-coordinate in screen space
    y - the y-coordinate in screen space
    originX - the x-coordinate of the scaling and rotation origin relative to the screen space coordinates
    originY - the y-coordinate of the scaling and rotation origin relative to the screen space coordinates
    width - the width in pixels
    height - the height in pixels
    scaleX - the scale of the rectangle around originX/originY in x
    scaleY - the scale of the rectangle around originX/originY in y
    rotation - the angle of counter clockwise rotation of the rectangle around originX/originY
    srcX - the x-coordinate in texel space
    srcY - the y-coordinate in texel space
    srcWidth - the source with in texels
    srcHeight - the source height in texels
    flipX - whether to flip the sprite horizontally
    flipY - whether to flip the sprite vertically
     */

    private void easyDrawCenterPiece(SpriteBatch sb, float x, float y) {
        sb.setColor(Color.WHITE);
        sb.draw(TEX_CENTER, x, y,
                50.0f, 50.0f,
                (float)CHUNK_SIZE, (float)CHUNK_SIZE,
                Settings.scale, Settings.scale,
                0.0f,
                0, 0,
                CHUNK_SIZE, CHUNK_SIZE,
                false, false );

    }


    private void easyDraw(SpriteBatch sb, Texture baseTex, Texture trimTex, float x, float y, float rot, boolean flipX, boolean flipY) {
        // Base
        sb.setColor(Color.WHITE);
        sb.draw(baseTex,
                x, y,
                50.0f, 50.0f, // CHUNK_SIZE / 2.0 (it's a square)
                (float)CHUNK_SIZE, (float)CHUNK_SIZE,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                CHUNK_SIZE, CHUNK_SIZE,
                flipX, flipY );

        // Trim
        sb.setColor(getTrimColor());
        sb.draw(trimTex,
                x, y,
                50.0f, 50.0f, // CHUNK_SIZE / 2.0 (it's a square)
                (float)CHUNK_SIZE, (float)CHUNK_SIZE,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                CHUNK_SIZE, CHUNK_SIZE,
                flipX, flipY );
    }

    @Override
    protected void renderScreenBackground(SpriteBatch sb) {
        float left = getScreenLeft();
        float bottom = getScreenBottom();

        sb.setColor(Color.WHITE);
        for (int i = 0; i < rowChunks; ++i) {
            float x = left + i * CHUNK_SIZE;

            for (int j = 0; j < colChunks; ++j) {
                float y = bottom + j * CHUNK_SIZE;

                // CORNERS (default texture looks like top left corner)
                if (i == 0 && j == 0)
                    easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, x, y, 90.0f, false, false);
                else if (i == 0 && j == colChunks - 1)
                    easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, x, y, 0.0f, false, false);
                else if (i == rowChunks - 1 && j == 0)
                    easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, x, y, 90.0f, false, true);
                else if (i == rowChunks - 1 && j == colChunks - 1)
                    easyDraw(sb, TEX_CORNER_BASE, TEX_CORNER_TRIM, x, y, 0.0f, true, false);

                // EDGES
                else if (i == 0)
                    easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, x, y, 90.0f, false, false);
                else if (i == rowChunks - 1)
                    easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, x, y, 90.0f, false, true);
                else if (j == 0)
                    easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, x, y, 0.0f, false, true);
                else if (j == colChunks - 1)
                    easyDraw(sb, TEX_CENTER, TEX_EDGE_TRIM, x, y, 0.0f, false, false);

                // CENTER
                else
                    easyDrawCenterPiece(sb, x, y);
            }
        }
    }

}
