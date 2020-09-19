package ui.widgets.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.widgets.Widget;

public class SimpleLabel extends Widget<SimpleLabel> {
    private String text;
    private BitmapFont font;
    private Color fontColor;

    private float textHeight, textWidth;

    private float horizOffset, vertOffset;

    public SimpleLabel(String text, BitmapFont font, Color fontColor) {
        this.text = text;
        this.font = font;
        this.fontColor = fontColor;

        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 100000, 10);
    }

    public SimpleLabel(String text) {
        this(text, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
    }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 100000, 10);
    }

    public SimpleLabel withOffsets(float horizOffset, float vertOffset) {
        this.horizOffset = horizOffset;
        this.vertOffset = vertOffset;

        return this;
    }

    @Override public float getPrefWidth() { return textWidth; }
    @Override public float getPrefHeight() { return textHeight; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        sb.setColor(Color.WHITE);
        FontHelper.renderFontLeftDownAligned(sb, font, text, bottomLeftX + horizOffset, bottomLeftY + vertOffset, fontColor);
    }
}
