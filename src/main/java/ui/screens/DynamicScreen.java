package ui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class DynamicScreen<T extends DynamicScreen<T>> extends AbstractScreen<T> {
    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_BASE);
    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_CORNER_TRIM);
    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.SCREEN_EDGE_TRIM);

    private final int CORNER_SIZE = 100;

    private int centerWidth, centerHeight;

    // Minimum size (each corner piece is 100x100, so no edges/no center = 100+100 in each dimension)
//    private float atLeastCornerSize(int input) {
//        return (input < (CORNER_SIZE * 2)) ? CORNER_SIZE * 2 : (float)input;
//    }

    public DynamicScreen(int width, int height) {
        super(width, height);

        this.centerWidth = width - (CORNER_SIZE * 2);
        this.centerHeight = height - (CORNER_SIZE * 2);
    }

    public Color getBaseColor() { return ColorHelper.SCREEN_BASE_COLOR; }
    public Color getTrimColor() { return Settings.CREAM_COLOR; }

    @Override
    protected void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        RenderingHelper.renderDynamicPieces(sb,
                TEX_CORNER_BASE,
                TEX_CORNER_TRIM,
                TEX_EDGE_TRIM,
                bottomLeftX,
                bottomLeftY,
                bottomLeftX + CORNER_SIZE,
                bottomLeftX + CORNER_SIZE + centerWidth,
                bottomLeftY + CORNER_SIZE,
                bottomLeftY + CORNER_SIZE + centerHeight,
                centerWidth,
                centerHeight,
                getBaseColor(),
                getTrimColor(),
                CORNER_SIZE);
    }
}
