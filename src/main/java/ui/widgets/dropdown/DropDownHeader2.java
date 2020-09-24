package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class DropDownHeader2 extends AbstractButton<DropDownHeader2> {
    private DropDownMenu2 parent;

    protected BitmapFont font = FontHelper.tipBodyFont;
    private String text;
    private float textHeight, textWidth;

    protected static final float TEXT_HORIZONTAL_OFFSET = 40;
    protected static final float TEXT_VERTICAL_OFFSET = 7;
    protected static final float BORDER_HEIGHT = 4;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE_2.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM_2.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM_2.get();
    private static final int CORNER_SIZE = 16;

    public DropDownHeader2(InteractiveWidgetManager interactiveWidgetManager, DropDownMenu2 parent) {
        super(interactiveWidgetManager);
        this.parent = parent;

        //hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    public void setText(String text) {
        this.text = text;
        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 100000, 10);
    }

    // Always grows to fit content
    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // fix hitbox?
        super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);

        // Background
        if(hb.hovered || parent.isOpen()) {
//            sb.setColor(ColorHelper.FADED_CREAM);
//            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

            RenderingHelper.renderDynamicBase(sb,
                    TEX_CORNER_BASE,
                    (int)bottomLeftX,
                    (int)bottomLeftY,
                    (int)width,
                    (int)height,
                    CORNER_SIZE,
                    ColorHelper.FADED_CREAM);
        }

        // Text
        float textLeft = bottomLeftX + TEXT_HORIZONTAL_OFFSET;
        float textBottom = bottomLeftY + (height - textHeight - BORDER_HEIGHT) * 0.5f + TEXT_VERTICAL_OFFSET;
        FontHelper.renderFontLeftDownAligned(sb, font, text, textLeft, textBottom, Settings.CREAM_COLOR);


        // Border
        if (parent.isOpen()) {
            sb.setColor(ColorHelper.BUTTON_TRIM);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, BORDER_HEIGHT);
        }

        hb.render(sb);
    }
}
