package ui.widgets.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.widgets.ScreenWidget;

public class SimpleLabel extends ScreenWidget {
    private String text;
    private BitmapFont font;
    private Color color;

    public SimpleLabel(String text, BitmapFont font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;

        setPrefWidth(FontHelper.getSmartWidth(font, text, 10000.0f, 10.0f));
        setPrefHeight(font.getLineHeight());
    }

    @Override
    public void render(SpriteBatch sb) {
        FontHelper.renderFontLeftTopAligned(sb, font, text, x, y, color);
    }
}
