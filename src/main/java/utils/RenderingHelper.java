package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class RenderingHelper {
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

    // No rotation allowed (todo: allow only 90/180/270?)
    public static void renderClipped(SpriteBatch sb,
                                     Texture tex,
                                     float x,
                                     float y,
                                     boolean flipX,
                                     boolean flipY,
                                     float clipLeft,
                                     float clipRight,
                                     float clipBottom,
                                     float clipTop
                                     ) {
        int width = tex.getWidth();
        int height = tex.getHeight();

        // Simple culling to quit early
        if (x + width < clipLeft || x > clipRight)
            return;
        if (y + height < clipBottom || y > clipTop)
            return;

        // Adjust these values based on the clip region
        int srcX = 0;
        int srcY = 0;
        int finalWidth = width;
        int finalHeight = height;

        // LEFT
        if (x < clipLeft) {
            int diff = (int)(clipLeft - x);
            srcX += diff;
            finalWidth -= diff;
        }

        // RIGHT
        if (x + width > clipRight) {
            int diff = (int)(x + width - clipRight);
            finalWidth -= diff;
        }

        // BOTTOM
        int additionalVerticalShift = 0;
        if (y < clipBottom) {
            int diff = (int)(clipBottom - y);
            finalHeight -= diff;
            additionalVerticalShift = diff;
        }

        // TOP
        if (y + height > clipTop) {
            int diff = (int)(y + height - clipTop);
            srcY += diff;
            finalHeight -= diff;
        }


        sb.draw(tex,
                x + srcX, y + additionalVerticalShift,
                (float)width / 2.0f, (float)height / 2.0f,
                finalWidth, finalHeight,
                Settings.scale, Settings.scale,
                0.0f,
                srcX, srcY,
                finalWidth, finalHeight,
                flipX, flipY
                );

    }

    public static void renderBox(SpriteBatch sb, float x, float y, float width, float height, Color color) {
        sb.setColor(color);
        sb.draw(ImageMaster.DEBUG_HITBOX_IMG, x, y, width, height);
    }

    public static void renderBoxFilled(SpriteBatch sb, float x, float y, float width, float height, Color color) {
        sb.setColor(color);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, x, y, width, height);
    }

    // TODO: scale based on resolution
    private static void easyDraw(SpriteBatch sb,
                                 Texture baseTex,
                                 Texture trimTex,
                                 float x,
                                 float y,
                                 int width,
                                 int height,
                                 float rot,
                                 boolean flipX,
                                 boolean flipY,
                                 Color baseColor,
                                 Color trimColor,
                                 int cornerSize) {
        // Base
        sb.setColor(baseColor);
        sb.draw(baseTex,
                x, y,
                cornerSize / 2, cornerSize / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );

        // Trim
        sb.setColor(trimColor);
        sb.draw(trimTex,
                x, y,
                cornerSize / 2, cornerSize / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );
    }

    private static void easyDrawRegular(SpriteBatch sb, Texture tex, float x, float y, int width, int height, float rot, boolean flipX, boolean flipY, Color color, int cornerSize) {
        sb.setColor(color);
        sb.draw(tex,
                x, y,
                cornerSize / 2, cornerSize / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );
    }

    // TODO: scale based on resolution
    private static void easyDrawWithHighlight(SpriteBatch sb,
                                 Texture baseTex,
                                 Texture trimTex,
                                 Texture highlightTex,
                                 float x,
                                 float y,
                                 int width,
                                 int height,
                                 float rot,
                                 boolean flipX,
                                 boolean flipY,
                                 Color baseColor,
                                 Color trimColor,
                                 Color highlighColor,
                                 int cornerSize) {
        // Base
        easyDrawRegular(sb, baseTex, x, y, width, height, rot, flipX, flipY, baseColor, cornerSize);
//        sb.setColor(baseColor);
//        sb.draw(baseTex,
//                x, y,
//                cornerSize / 2, cornerSize / 2,
//                width, height,
//                Settings.scale, Settings.scale,
//                rot,
//                0, 0,
//                width, height,
//                flipX, flipY );

        // Highlight
        sb.setColor(highlighColor);
        sb.draw(highlightTex,
                x, y,
                cornerSize / 2, cornerSize / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );

        // Trim
        sb.setColor(trimColor);
        sb.draw(trimTex,
                x, y,
                cornerSize / 2, cornerSize / 2,
                width, height,
                Settings.scale, Settings.scale,
                rot,
                0, 0,
                width, height,
                flipX, flipY );
    }

    public static void renderDynamicPieces(SpriteBatch sb, Texture topLeftCornerBase, Texture topLeftCornerTrim, Texture topEdgeTrim,
                                           float left, float bottom, float centerStartX, float centerEndX,
                                           float centerStartY, float centerEndY, int centerWidth, int centerHeight,
                                           Color baseColor, Color trimColor, int cornerSize ) {

        // CORNERS
        easyDraw(sb, topLeftCornerBase, topLeftCornerTrim, left, bottom, cornerSize, cornerSize, 90.0f, false, false,  baseColor, trimColor, cornerSize); // bottom left
        easyDraw(sb, topLeftCornerBase, topLeftCornerTrim, left, centerEndY, cornerSize, cornerSize, 0.0f, false, false, baseColor, trimColor, cornerSize); // top left
        easyDraw(sb, topLeftCornerBase, topLeftCornerTrim, centerEndX, bottom, cornerSize, cornerSize, 90.0f, false, true, baseColor, trimColor, cornerSize); // bottom right
        easyDraw(sb, topLeftCornerBase, topLeftCornerTrim, centerEndX, centerEndY, cornerSize, cornerSize, 0.0f, true, false, baseColor, trimColor, cornerSize); // top right

        // EDGES
        easyDraw(sb, ImageMaster.WHITE_SQUARE_IMG, topEdgeTrim, left, centerStartY, centerHeight, cornerSize, 90.0f, false, false, baseColor, trimColor, cornerSize); // left
        easyDraw(sb, ImageMaster.WHITE_SQUARE_IMG, topEdgeTrim, centerStartX, bottom, centerWidth, cornerSize, 0.0f, false, true, baseColor, trimColor, cornerSize); // bottom
        easyDraw(sb, ImageMaster.WHITE_SQUARE_IMG, topEdgeTrim, centerEndX, centerStartY, centerHeight, cornerSize, 90.0f, false, true, baseColor, trimColor, cornerSize); // right
        easyDraw(sb, ImageMaster.WHITE_SQUARE_IMG, topEdgeTrim, centerStartX, centerEndY, centerWidth, cornerSize, 0.0f, false, false, baseColor, trimColor, cornerSize); // top

        // CENTER
        // TODO: scale based on resolution
        sb.setColor(baseColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, centerStartX, centerStartY, centerWidth, centerHeight);
    }

    public static void renderDynamicBase(SpriteBatch sb, Texture cornerTex, float left, float bottom, float width, float height, int cornerSize, Color baseColor) {
        float centerLeft = left + cornerSize;
        float centerRight = left + width - cornerSize;
        float centerBottom = bottom + cornerSize;
        float centerTop = bottom + height - cornerSize;

        int centerWidth = (int)(centerRight - centerLeft);
        int centerHeight = (int)(centerTop - centerBottom);

        // CORNERS
        easyDrawRegular(sb, cornerTex, left, bottom, cornerSize, cornerSize, 90.0f, false, false, baseColor, cornerSize); // bottom left
        easyDrawRegular(sb, cornerTex, left, centerTop, cornerSize, cornerSize, 0.0f, false, false, baseColor, cornerSize); // top left
        easyDrawRegular(sb, cornerTex, centerRight, centerTop, cornerSize, cornerSize, 0.0f, true, false, baseColor, cornerSize); // top right
        easyDrawRegular(sb, cornerTex, centerRight, bottom, cornerSize, cornerSize, 90.0f, false, true, baseColor, cornerSize); // bottom right

        // CENTER
        // TODO: scale based on resolution
        sb.setColor(baseColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, centerLeft, centerBottom, centerWidth, centerHeight);

        // EDGES
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerLeft, centerTop, centerWidth, cornerSize, 0.0f, false, false, baseColor, cornerSize); // top
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, left, centerBottom, centerHeight, cornerSize, 90.0f, false, false, baseColor, cornerSize); // left
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerRight, centerBottom, centerHeight, cornerSize, 90.0f, false, true, baseColor, cornerSize); // right
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerLeft, bottom, centerWidth, cornerSize, 0.0f, false, true, baseColor, cornerSize); // bottom
    }

    public static void renderDynamicTrim(SpriteBatch sb, Texture cornerTex, Texture edgeTex, float left, float bottom, float width, float height, int cornerSize, Color trimColor) {
        float centerLeft = left + cornerSize;
        float centerRight = left + width - cornerSize;
        float centerBottom = bottom + cornerSize;
        float centerTop = bottom + height - cornerSize;

        int centerWidth = (int)(centerRight - centerLeft);
        int centerHeight = (int)(centerTop - centerBottom);

        // CORNERS
        easyDrawRegular(sb, cornerTex, left, bottom, cornerSize, cornerSize, 90.0f, false, false, trimColor, cornerSize);
        easyDrawRegular(sb, cornerTex, left, centerTop, cornerSize, cornerSize, 0.0f, false, false, trimColor, cornerSize);
        easyDrawRegular(sb, cornerTex, centerRight, centerTop, cornerSize, cornerSize, 0.0f, true, false, trimColor, cornerSize);
        easyDrawRegular(sb, cornerTex, centerRight, bottom, cornerSize, cornerSize, 90.0f, false, true, trimColor, cornerSize);

        // EDGES
        easyDrawRegular(sb, edgeTex, centerLeft, centerTop, centerWidth, cornerSize, 0.0f, false, false, trimColor, cornerSize); // top
        easyDrawRegular(sb, edgeTex, left, centerBottom, centerHeight, cornerSize, 90.0f, false, false, trimColor, cornerSize); // left
        easyDrawRegular(sb, edgeTex, centerRight, centerBottom, centerHeight, cornerSize, 90.0f, false, true, trimColor, cornerSize); // right
        easyDrawRegular(sb, edgeTex, centerLeft, bottom, centerWidth, cornerSize, 0.0f, false, true, trimColor, cornerSize); // bottom
    }

    public static void renderDynamicHighlight(SpriteBatch sb, Texture cornerTex, Texture edgeTex, Texture centerTex, float left, float bottom, float width, float height, int cornerSize) {
        float centerLeft = left + cornerSize;
        float centerRight = left + width - cornerSize;
        float centerBottom = bottom + cornerSize;
        float centerTop = bottom + height - cornerSize;

        int centerWidth = (int)(centerRight - centerLeft);
        int centerHeight = (int)(centerTop - centerBottom);

        // CORNERS
        easyDrawRegular(sb, cornerTex, left, centerTop, cornerSize, cornerSize, 0.0f, false, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize); // top left
        easyDrawRegular(sb, cornerTex, centerRight, centerTop, cornerSize, cornerSize, 0.0f, true, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize); // top right

        // EDGE
        easyDrawRegular(sb, edgeTex, centerLeft, centerTop, centerWidth, cornerSize, 0.0f, false, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize); // top

        // CENTER
        sb.setColor(ColorHelper.BUTTON_HIGHLIGHT);
        sb.draw(centerTex, left, centerBottom, centerWidth + 2 * cornerSize, centerHeight);
    }

    public static void renderDynamicPiecesWithHighlight(SpriteBatch sb,
                                                        Texture topLeftCornerBase, Texture topLeftCornerTrim, Texture topEdgeTrim,
                                                        Texture topLeftCornerHighlight, Texture topEdgeHighlight, Texture leftEdgeHighlight, Texture centerHighlight,
                                                        float left, float bottom, float centerStartX, float centerEndX,
                                                        float centerStartY, float centerEndY, int centerWidth, int centerHeight,
                                                        Color baseColor, Color trimColor, int cornerSize ) {

        // CORNERS
        // BOTTOM LEFT
        easyDrawRegular(sb, topLeftCornerBase, left, bottom, cornerSize, cornerSize, 90.0f, false, false, baseColor, cornerSize);
        easyDrawRegular(sb, topLeftCornerTrim, left, bottom, cornerSize, cornerSize, 90.0f, false, false, trimColor, cornerSize);

        // TOP LEFT
        easyDrawRegular(sb, topLeftCornerBase, left, centerEndY, cornerSize, cornerSize, 0.0f, false, false, baseColor, cornerSize);
        easyDrawRegular(sb, topLeftCornerHighlight, left, centerEndY, cornerSize, cornerSize, 0.0f, false, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize);
        easyDrawRegular(sb, topLeftCornerTrim, left, centerEndY, cornerSize, cornerSize, 0.0f, false, false, trimColor, cornerSize);

        // TOP RIGHT
        easyDrawRegular(sb, topLeftCornerBase, centerEndX, centerEndY, cornerSize, cornerSize, 0.0f, true, false, baseColor, cornerSize);
        easyDrawRegular(sb, topLeftCornerHighlight, centerEndX, centerEndY, cornerSize, cornerSize, 0.0f, true, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize);
        easyDrawRegular(sb, topLeftCornerTrim, centerEndX, centerEndY, cornerSize, cornerSize, 0.0f, true, false, trimColor, cornerSize);

        // BOTTOM RIGHT
        easyDrawRegular(sb, topLeftCornerBase, centerEndX, bottom, cornerSize, cornerSize, 90.0f, false, true, baseColor, cornerSize);
        easyDrawRegular(sb, topLeftCornerTrim, centerEndX, bottom, cornerSize, cornerSize, 90.0f, false, true, trimColor, cornerSize);

        // CENTER
        // TODO: scale based on resolution
        sb.setColor(baseColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, centerStartX, centerStartY, centerWidth, centerHeight);

        // EDGE BASES
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerStartX, centerEndY, centerWidth, cornerSize, 0.0f, false, false, baseColor, cornerSize); // top
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, left, centerStartY, centerHeight, cornerSize, 90.0f, false, false, baseColor, cornerSize); // left
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerEndX, centerStartY, centerHeight, cornerSize, 90.0f, false, true, baseColor, cornerSize); // right
        easyDrawRegular(sb, ImageMaster.WHITE_SQUARE_IMG, centerStartX, bottom, centerWidth, cornerSize, 0.0f, false, true, baseColor, cornerSize); // bottom

        // EDGE HIGHLIGHTS
        easyDrawRegular(sb, topEdgeHighlight, centerStartX, centerEndY, centerWidth, cornerSize, 0.0f, false, false, ColorHelper.BUTTON_HIGHLIGHT, cornerSize); // top

        sb.setColor(ColorHelper.BUTTON_HIGHLIGHT);
        sb.draw(centerHighlight, left, centerStartY, centerWidth + 2 * cornerSize, centerHeight);

        // EDGE TRIMS
        easyDrawRegular(sb, topEdgeTrim, centerStartX, centerEndY, centerWidth, cornerSize, 0.0f, false, false, trimColor, cornerSize); // top
        easyDrawRegular(sb, topEdgeTrim, left, centerStartY, centerHeight, cornerSize, 90.0f, false, false, trimColor, cornerSize); // left
        easyDrawRegular(sb, topEdgeTrim, centerEndX, centerStartY, centerHeight, cornerSize, 90.0f, false, true, trimColor, cornerSize); // right
        easyDrawRegular(sb, topEdgeTrim, centerStartX, bottom, centerWidth, cornerSize, 0.0f, false, true, trimColor, cornerSize); // bottom
    }

    /*
       TODO:
       ACTUAL, GOOD CLIPPING  (this works!)

        sb.flush();
        Rectangle scissors = new Rectangle();

        // TODO: may need more adjustments to the camera (for other resolutions?) -- for now just use this camera offset cheat
        float cameraOffsetX = - Gdx.graphics.getWidth() / 2.0f;
        float cameraOffsetY = - Gdx.graphics.getHeight() / 2.0f;

        Rectangle clipBounds = new Rectangle( getContentLeft() + cameraOffsetX, getContentBottom() + cameraOffsetY, getContentWidth(), getContentHeight());

        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        ScissorStack.calculateScissors(camera, sb.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors); // TODO: pretty sure im supposed to put an if around this + draw calls

        [ DRAW CALLS GO RIGHT HERE ]

        sb.flush();
        ScissorStack.popScissors();
     */
}
