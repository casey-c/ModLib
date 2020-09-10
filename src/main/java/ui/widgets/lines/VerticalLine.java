package ui.widgets.lines;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.ScreenWidget;
import utils.ColorHelper;

public class VerticalLine extends ScreenWidget<VerticalLine> {
    @Override
    public float getPrefWidth() {
        return 3.0f; // Vertical line is always this width
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(ColorHelper.TRANSPARENT_WHITE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getCenterX(), getBottom(), getPrefWidth(), getPrefHeight());
    }
}
