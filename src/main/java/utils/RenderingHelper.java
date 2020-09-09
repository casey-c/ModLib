package utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

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
}
