package ui.widgets.lines;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.Widget;
import utils.ColorHelper;

public class HorizontalLine extends Widget<HorizontalLine> {
    private static final float LINE_HEIGHT = 2;
    private Color lineColor;

    private boolean ignoreAnchors;

    public HorizontalLine(Color lineColor, boolean ignoreAnchors) {
        this.lineColor = lineColor;
        this.ignoreAnchors = ignoreAnchors;
    }

    // Convenience constructors
    public HorizontalLine(Color lineColor) { this(lineColor, false); }
    public HorizontalLine(boolean ignoreAnchors) { this(ColorHelper.TRANSPARENT_WHITE, ignoreAnchors); }
    public HorizontalLine() { this(ColorHelper.TRANSPARENT_WHITE, false); }

    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return LINE_HEIGHT; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        sb.setColor(lineColor);

        // Always centered (ignore params to this function)
        if (ignoreAnchors)
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentCenterY() - (LINE_HEIGHT * 0.5f), getContentWidth(), LINE_HEIGHT);
        else
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, LINE_HEIGHT);
    }
}
