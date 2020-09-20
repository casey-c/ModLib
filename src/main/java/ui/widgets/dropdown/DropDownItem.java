package ui.widgets.dropdown;

/*

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;

public class DropDownItem extends AbstractButton<DropDownItem> {

//    private String text;
//    private float textWidth, textHeight;
//
//    private BitmapFont font = FontHelper.tipBodyFont;
//    private Color fontColor = Settings.CREAM_COLOR;
//
//    private static final float TEXT_OFFSET_X = 8;
//    private static final float TEXT_OFFSET_Y = 7;

    private SimpleLabel label;

    public DropDownItem(InteractiveWidgetManager manager, String text) {
        super(manager);
        label = new SimpleLabel(text).withOffsets(8, 7);

//        this.text = text;
//
//        this.textWidth = FontHelper.getSmartWidth(font, text, 100000.0f, 10);
//        this.textHeight = font.getLineHeight();

        hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    public String getText() { return label.getText(); }

    private static final float HORIZONTAL_PADDING = 20;
    @Override public float getPrefWidth() {
        return label.getPrefWidth() + HORIZONTAL_PADDING;
        //return 250;
    }

    private static final float VERTICAL_PADDING = 20;
    @Override public float getPrefHeight() {
        return label.getPrefHeight() + VERTICAL_PADDING;
        //return 40;
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        // Neccessary for hitboxes
        //super.renderAt(sb, bottomLeftX, bottomLeftY, w, h);

        // TODO: draw actual textures
        if (hb.hovered) sb.setColor(ColorHelper.VERY_DIM_MAGENTA);
        else sb.setColor(ColorHelper.VERY_DIM_GREEN);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, w, h);

        label.renderAt(sb, bottomLeftX, bottomLeftY, w, h);
        hb.render(sb);
    }
}

 */
