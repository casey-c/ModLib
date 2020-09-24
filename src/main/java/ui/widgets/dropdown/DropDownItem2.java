package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class DropDownItem2 extends AbstractButton<DropDownItem2> {
    private boolean last;

    protected BitmapFont font = FontHelper.tipBodyFont;
    protected String text;
    protected float textWidth, textHeight;

    protected static final float TEXT_HORIZONTAL_OFFSET = 40;
    protected static final float TEXT_VERTICAL_OFFSET = 7;
    protected static final float BORDER_HEIGHT = 4;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE_2.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM_2.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM_2.get();
    private static final int CORNER_SIZE = 16;

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
            sb.setColor(ColorHelper.BUTTON_TRIM);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, BORDER_HEIGHT);
        }
    }

    protected void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        Color c = (hb.hovered) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.DROPDOWN_BASE;
        sb.setColor(c);

        if (last) {
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY + (height * 0.5f), width, height * 0.5f);
            RenderingHelper.renderDynamicBase(sb, TEX_CORNER_BASE, (int)bottomLeftX, (int)bottomLeftY, (int)width, (int)height, CORNER_SIZE, c);
        }
        else {
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);
        }

        // TODO: render a gradient (this looks terrible)
//        RenderingHelper.renderDynamicHighlight(sb,
//                TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CORNER.get(),
//                TextureHelper.TextureItem.BUTTON_HIGHLIGHT_TOP_EDGE.get(),
//                TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CENTER.get(),
//                (int)bottomLeftX,
//                (int)bottomLeftY,
//                (int)width,
//                (int)height,
//                CORNER_SIZE );

//        if (hb.hovered) sb.setColor(ColorHelper.BUTTON_CLICK_BASE);
//        else sb.setColor(ColorHelper.BUTTON_DEFAULT_BASE);

        //sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

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
