package ui.widgets.lines;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.Widget;
import utils.ColorHelper;

public class VerticalLine extends Widget<VerticalLine> {
    private static final float LINE_WIDTH = 2;
    private Color lineColor;

    private boolean ignoreAnchors;

    public VerticalLine(Color lineColor, boolean ignoreAnchors) {
        this.lineColor = lineColor;
        this.ignoreAnchors = ignoreAnchors;
    }

    // Convenience constructors
    public VerticalLine(Color lineColor) { this(lineColor, false); }
    public VerticalLine(boolean ignoreAnchors) { this(ColorHelper.TRANSPARENT_WHITE, ignoreAnchors); }
    public VerticalLine() { this(ColorHelper.TRANSPARENT_WHITE, false); }

    @Override public float getPrefWidth() { return LINE_WIDTH; }
    @Override public float getPrefHeight() { return getContentHeight(); }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        sb.setColor(lineColor);

        // Always centered (ignore params to this function)
        if (ignoreAnchors)
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentCenterX() - (LINE_WIDTH * 0.5f), getContentBottom(), LINE_WIDTH, getContentHeight());
        else
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, LINE_WIDTH, height);
    }
}
