package ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;
import utils.ColorHelper;

public class DebugWidget extends Widget<DebugWidget> {
    private float width, height;

    public DebugWidget(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public DebugWidget(Widget parent) {
        this(parent.getContentWidth(), parent.getContentHeight());
        setActualFromAnchor(parent.getContentLeft(), parent.getContentBottom(), parent.getContentWidth(), parent.getContentHeight(), AnchorPosition.BOTTOM_LEFT);
    }

    @Override protected float getPrefWidth() { return width; }
    @Override protected float getPrefHeight() { return height; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        sb.setColor(ColorHelper.VERY_DIM_GREEN);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);
    }
}
