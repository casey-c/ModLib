package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class SimpleButton<T extends SimpleButton<T>> extends AbstractButton<T> {
    protected float width, height;

    protected Color baseColor, hoverColor, clickColor;

    protected static final int CORNER_SIZE = 20;
//    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_BASE);
//    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_TRIM);
//    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_EDGE_TRIM);
//
//    private static final Texture TEX_TOP_EDGE_HIGHLIGHT = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_TOP_EDGE);
//    private static final Texture TEX_CORNER_HIGHLIGHT = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CORNER);
//    private static final Texture TEX_CENTER_HIGHLIGHT = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CENTER);

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.BUTTON_CORNER_BASE.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.BUTTON_CORNER_TRIM.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.BUTTON_EDGE_TRIM.get();

    private static final Texture TEX_TOP_EDGE_HIGHLIGHT = TextureHelper.TextureItem.BUTTON_HIGHLIGHT_TOP_EDGE.get();
    private static final Texture TEX_CORNER_HIGHLIGHT = TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CORNER.get();
    private static final Texture TEX_CENTER_HIGHLIGHT = TextureHelper.TextureItem.BUTTON_HIGHLIGHT_CENTER.get();

    // TODO: really really really need to do some refactoring on these constructors - they may be the worst I've ever
    //   written in my entire life.....

    public SimpleButton(float width, float height) {
        this(width, height, ColorHelper.BUTTON_DEFAULT_BASE, ColorHelper.BUTTON_HOVER_BASE, ColorHelper.BUTTON_CLICK_BASE);
    }

    protected SimpleButton() {
        this(ColorHelper.BUTTON_DEFAULT_BASE, ColorHelper.BUTTON_HOVER_BASE, ColorHelper.BUTTON_CLICK_BASE);
    }

    public SimpleButton(Color baseColor, Color hoverColor, Color clickColor) {
        this.width = 0;
        this.height = 0;
        hb = new Hitbox(width, height);

        this.baseColor = baseColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    public SimpleButton(float width, float height, Color baseColor, Color hoverColor, Color clickColor) {
        this.width = width;
        this.height = height;

        hb = new Hitbox(width, height);

        this.baseColor = baseColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    public Color getBaseColor() {
        if (hb.hovered)
            return (hb.clickStarted) ? clickColor : hoverColor;
        else
            return baseColor;
    }

    public Color getTrimColor() {
        return ColorHelper.BUTTON_TRIM;
    }

    @Override public float getPrefWidth() { return width; }
    @Override public float getPrefHeight() { return height; }

    public void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        RenderingHelper.renderDynamicBase(sb, TEX_CORNER_BASE, bottomLeftX, bottomLeftY, width, height, CORNER_SIZE, getBaseColor());
        RenderingHelper.renderDynamicHighlight(sb, TEX_CORNER_HIGHLIGHT, TEX_TOP_EDGE_HIGHLIGHT, TEX_CENTER_HIGHLIGHT, bottomLeftX, bottomLeftY, width, height, CORNER_SIZE);
        RenderingHelper.renderDynamicTrim(sb, TEX_CORNER_TRIM, TEX_EDGE_TRIM, bottomLeftX, bottomLeftY, width, height, CORNER_SIZE, getTrimColor());
    }

    public void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Debug (not necessary to call super() to call this)
        hb.render(sb);
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Necessary for hitbox fixes
        //super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
        fixHitbox(bottomLeftX, bottomLeftY, width, height);

        renderBackground(sb, bottomLeftX, bottomLeftY, width, height);
        renderForeground(sb, bottomLeftX, bottomLeftY, width, height);
    }
}
