package ui.widgets.buttons;

/*
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import config.Config;
import ui.layouts.AnchorPosition;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class TextButtonOLD extends AbstractButtonOLD<TextButtonOLD> {
    private String text;
    private static final BitmapFont font = FontHelper.tipBodyFont;
    private static final int CORNER_SIZE = 20;

    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_BASE);
    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_TRIM);
    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_EDGE_TRIM);

    private float textWidth, textHeight;

    private boolean dynamicWidth = true;
    private boolean dynamicHeight = true;

    // TODO: tweak these experimentally
    private static final int TEXT_VERTICAL_OFFSET = 7;

    private static final float TEXT_HORIZONTAL_PADDING = 4.0f;
    private static final float TEXT_VERTICAL_PADDING = 4.0f;

    public TextButtonOLD(String text) {
        this(text, AnchorPosition.CENTER);
    }

    public TextButtonOLD(String text, AnchorPosition pos) {
        this.text = text;
        this.anchorPosition = pos;

        // TODO: look over this & think about it more (don't think it's correct as is)
        this.textWidth = FontHelper.getSmartWidth(font, text, 10000.0f, 10.0f);
        this.textHeight = font.getLineHeight();

        // Initially defaults to prefWH as the dynamically calculated version.
        setPrefWidth(getDynamicWidth());
        setPrefHeight(getDynamicHeight());

        hb.width = getDynamicWidth();
        hb.height = getDynamicHeight();
    }

    public TextButtonOLD fixToDynamicHeight() {
        this.setPrefHeight(getDynamicHeight());
        return this;
    }

    public TextButtonOLD fixToDynamicWidth() {
        this.setPrefHeight(getDynamicWidth());
        return this;
    }

    public TextButtonOLD withDynamicHeight() {
        //setPrefHeight(getDynamicHeight());
        this.dynamicHeight = true;
        //this.hb.height = getPrefHeight();
        this.hb.height = getDynamicHeight();
        return this;
    }

    public TextButtonOLD withDynamicWidth() {
        //setPrefWidth(getDynamicWidth());
        this.dynamicWidth = true;
        //this.hb.width = getPrefWidth();
        this.hb.width = getDynamicWidth();
        return this;
    }

    public TextButtonOLD withFixedWidth(float width) {
        setPrefWidth(width);
        return this;
    }

    public TextButtonOLD withFixedHeight(float height) {
        setPrefHeight(height);
        return this;
    }

    @Override
    public void setPrefWidth(float width) {
        super.setPrefWidth(width);

        this.dynamicWidth = false;
        this.hb.width = width;
    }

    @Override
    public void setPrefHeight(float height) {
        super.setPrefHeight(height);

        this.dynamicHeight = false;
        this.hb.height = height;
    }

    public Color getBaseColor() {
        if (hb.hovered)
            //return (InputHelper.isMouseDown) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.BUTTON_HOVER_BASE;
            return (hb.clickStarted) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.BUTTON_HOVER_BASE;
        else
            return ColorHelper.BUTTON_DEFAULT_BASE;
    }

    public Color getTrimColor() {
        return ColorHelper.BUTTON_TRIM;
    }

    private float getFixedCenterWidth() { return getPrefWidth() - (2 * CORNER_SIZE); }
    private float getFixedCenterHeight() { return getPrefHeight() - (2 * CORNER_SIZE); }

    private float getDynamicCenterWidth() { return getDynamicWidth() - (2 * CORNER_SIZE); }
    private float getDynamicCenterHeight() { return getDynamicHeight() - (2 * CORNER_SIZE); }

    private float getDynamicWidth() {
        return textWidth + 2 * (CORNER_SIZE + TEXT_HORIZONTAL_PADDING);
    }

    private float getDynamicHeight() {
        return textHeight + 2 * (CORNER_SIZE + TEXT_VERTICAL_PADDING);
    }

    // --------------------------------------------------------------------------------

    private float getButtonWidth() {
        return (dynamicWidth) ? getDynamicWidth() : getPrefWidth();
    }

    private float getButtonHeight() {
        return (dynamicHeight) ? getDynamicHeight() : getPrefHeight();
    }

    private float getButtonLeft() {
        if (dynamicWidth) {
            if (AnchorPosition.isCentralX(anchorPosition))
                return getLeft() + (getPrefWidth() - getDynamicWidth()) * 0.5f;
            else if (AnchorPosition.isRight(anchorPosition))
                return getRight() - (getDynamicWidth());
        }

        return getLeft();
    }

    private float getButtonBottom() {
        if (dynamicHeight) {
            if (AnchorPosition.isCentralY(anchorPosition))
                return getBottom() + (getPrefHeight() - getDynamicHeight()) * 0.5f;
            else if (AnchorPosition.isTop(anchorPosition))
                return getTop() - (getDynamicHeight());
        }

        return getBottom();
    }

    private float getButtonCenterX() {
        return getButtonLeft() + (getButtonWidth() * 0.5f);
    }

    private float getButtonCenterY() {
        return getButtonBottom() + (getButtonHeight() * 0.5f);
    }

    @Override
    public void show() {
        hb.move(getButtonLeft() + (0.5f * getButtonWidth()), getButtonBottom() + (0.5f * getButtonHeight()));
    }

    // --------------------------------------------------------------------------------

    @Override
    public void render(SpriteBatch sb) {

        if (Config.MOD_LIB_DEBUG_MODE) {
            sb.setColor(Color.OLIVE);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());
        }

        float left = getButtonLeft();
        float bottom = getButtonBottom();

        int cw = (int)((dynamicWidth) ? getDynamicCenterWidth() : getFixedCenterWidth());
        int ch = (int)((dynamicHeight) ? getDynamicCenterHeight() : getFixedCenterHeight());

        //RenderingHelper.renderDynamicPieces(sb,
        RenderingHelper.renderDynamicPiecesWithHighlight(sb,
                TEX_CORNER_BASE,
                TEX_CORNER_TRIM,
                TEX_EDGE_TRIM,
                TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CORNER),
                TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_TOP_EDGE),
                TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_LEFT_EDGE),
                TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CENTER),
                left,
                bottom,
                left + CORNER_SIZE,
                left + CORNER_SIZE + cw,
                bottom + CORNER_SIZE,
                bottom + CORNER_SIZE + ch,
                cw,
                ch,
                getBaseColor(),
                getTrimColor(),
                CORNER_SIZE);

        // Text
        sb.setColor(Color.WHITE);
        FontHelper.renderFontLeftDownAligned(sb, font, text,
                //getLeft() + CORNER_SIZE + TEXT_HORIZONTAL_OFFSET,
                getButtonCenterX() - (textWidth / 2.0f),
                getButtonCenterY() - (textHeight / 2.0f) + TEXT_VERTICAL_OFFSET,
                Settings.CREAM_COLOR);

        hb.render(sb);
    }
}

 */
