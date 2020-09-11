package ui.widgets.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import config.Config;
import ui.layouts.AnchorPosition;
import ui.widgets.ScreenWidget;
import utils.ColorHelper;

public class SimpleLabel extends ScreenWidget {
    private String text;
    private BitmapFont font;
    private Color color;

    private float textWidth, textHeight;

    public SimpleLabel(String text, BitmapFont font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;

        // TODO: alternatively make these override getPrefHeight() etc. directly (this should be better for a dynamic
        //   label that changes after constructed)
        this.textWidth = FontHelper.getSmartWidth(font, text, 10000.0f, 10.0f);
        this.textHeight = font.getLineHeight();

        setPrefWidth(textWidth);
        setPrefHeight(textHeight);
    }

    public SimpleLabel(String text) {
        this(text, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
    }

    @Override
    public void print() {
        System.out.println( "SimpleLabel: bl(" + getLeft() + ", " + getBottom() +  ")  --> c("  + getCenterX() + ", " + getCenterY() + ")  --> tr("  + getRight() + ", " + getTop() + ")  | (w, h): ("  + getPrefWidth() + ", " + getPrefHeight() + ")");
        System.out.println("\t Anchor position: " + anchorPosition.name());
        System.out.println("\t Smart Width: " + FontHelper.getSmartWidth(font, text, 10000.0f, 10.0f));
        System.out.println("\t Smart Height: " + font.getLineHeight());
        System.out.println("\t Text: " + text);
        System.out.println("---");
    }

    @Override
    public void render(SpriteBatch sb) {
        float posX = getLeft();
        if (AnchorPosition.isCentralX(anchorPosition))
            posX = getCenterX() - (textWidth * 0.5f);
        else if (AnchorPosition.isRight(anchorPosition))
            posX = getRight() - textWidth;

        float posY = getBottom();
        if (AnchorPosition.isCentralY(anchorPosition))
            posY = getCenterY() - (textHeight * 0.5f);
        else if (AnchorPosition.isTop(anchorPosition))
            posY = getTop() - textHeight;

        if (Config.MOD_LIB_DEBUG_MODE) {
            sb.setColor(ColorHelper.VERY_DIM_GREEN);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());
        }

        FontHelper.renderFontLeftDownAligned(sb, font, text, posX, posY, color);

    }
}
