package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;

public class TextButton extends SimpleButton<TextButton> {
    private String text;
    private BitmapFont font;
    private Color fontColor;

    private float textHeight, textWidth;

    // TODO: tweak these experimentally
    private static final int TEXT_VERTICAL_OFFSET = 7;
    private static final float TEXT_HORIZONTAL_PADDING = 4.0f;
    private static final float TEXT_VERTICAL_PADDING = 4.0f;

    public TextButton(String text) {
        this(text, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
    }

    public TextButton(String text, BitmapFont font, Color fontColor) {
        super();
        setText(text, font);
        this.fontColor = fontColor;
    }

    public TextButton(String text, BitmapFont font, Color fontColor, Color baseColor, Color hoverColor, Color clickColor) {
        super(baseColor, hoverColor, clickColor);
        setText(text, font);
        this.fontColor = fontColor;

    }


    // --------------------------------------------------------------------------------

    public void setText(String text) { setText(text, FontHelper.tipBodyFont); }
    public void setText(String text, BitmapFont font) {
        this.text = text;
        this.font = font;

        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 100000, 10);

        this.width = textWidth + 2 * (CORNER_SIZE + TEXT_HORIZONTAL_PADDING);
        this.height = textHeight + 2 * (CORNER_SIZE + TEXT_VERTICAL_PADDING);

        this.hb = new Hitbox(width, height);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        FontHelper.renderFontLeftDownAligned(sb, font, text,
                bottomLeftX + (0.5f * w) - (textWidth * 0.5f),
                bottomLeftY + (0.5f * h) - (textHeight * 0.5f) + TEXT_VERTICAL_OFFSET,
                Settings.CREAM_COLOR);

        hb.render(sb);
    }
}
