package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;

public class DropDownItem2 extends AbstractButton<DropDownItem2> {
    private boolean last;

    protected BitmapFont font = FontHelper.tipBodyFont;
    protected String text;
    protected float textWidth, textHeight;

    protected static final float TEXT_HORIZONTAL_OFFSET = 40;
    protected static final float TEXT_VERTICAL_OFFSET = 7;
    protected static final float BORDER_HEIGHT = 4;

    public DropDownItem2(InteractiveWidgetManager interactiveWidgetManager, String text) {
        super(interactiveWidgetManager);

        this.text = text;
        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 10000, 10);
    }

    public String getText() { return text; }
    public void setLast(boolean last) { this.last = last; }

    // Always expands
    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }

    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Text
        float textLeft = bottomLeftX + TEXT_HORIZONTAL_OFFSET;
        float textBottom = bottomLeftY + (height - textHeight - BORDER_HEIGHT) * 0.5f + TEXT_VERTICAL_OFFSET;
        FontHelper.renderFontLeftDownAligned(sb, font, text, textLeft, textBottom, Settings.CREAM_COLOR);

        // Border
        if (!last) {
            sb.setColor(Color.RED);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, BORDER_HEIGHT);
        }
    }

    protected void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        if (hb.hovered) sb.setColor(ColorHelper.BUTTON_CLICK_BASE);
        else sb.setColor(ColorHelper.BUTTON_DEFAULT_BASE);

        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Fix hitbox
        super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);

        // Background
        renderBackground(sb, bottomLeftX, bottomLeftY, width, height);

        // Text & border
        renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        hb.render(sb);
    }
}
