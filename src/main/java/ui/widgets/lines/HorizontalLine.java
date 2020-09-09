package ui.widgets.lines;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.ScreenWidget;
import utils.ColorHelper;

public class HorizontalLine extends ScreenWidget {
    @Override
    public float getPrefHeight() {
        return 3.0f; // Horizontal line is always this height
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(ColorHelper.TRANSPARENT_WHITE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getCenterY(), getPrefWidth(), getPrefHeight());
    }
}
