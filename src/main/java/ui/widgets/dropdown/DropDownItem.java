package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;

public class DropDownItem extends AbstractButton<DropDownItem> {

    private String text;
    private float textWidth, textHeight;

    private BitmapFont font = FontHelper.tipBodyFont;
    private Color fontColor = Settings.CREAM_COLOR;

    private static final float TEXT_OFFSET_X = 8;
    private static final float TEXT_OFFSET_Y = 7;

    public DropDownItem(String text) {
        this.text = text;

        this.textWidth = FontHelper.getSmartWidth(font, text, 100000.0f, 10);
        this.textHeight = font.getLineHeight();

        hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    public String getText() { return text; }

    // TODO
    @Override public float getPrefWidth() { return 250; }
    @Override public float getPrefHeight() { return 40; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        // Neccessary for hitboxes
        super.renderAt(sb, bottomLeftX, bottomLeftY, w, h);

        if (hb.hovered) sb.setColor(ColorHelper.VERY_DIM_MAGENTA);
        else sb.setColor(ColorHelper.VERY_DIM_GREEN);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, w, h);

        FontHelper.renderFontLeftDownAligned(sb, font, text, bottomLeftX + TEXT_OFFSET_X, bottomLeftY + (0.5f * h) - (0.5f * textHeight) + TEXT_OFFSET_Y, fontColor);

        hb.render(sb);
    }
}
