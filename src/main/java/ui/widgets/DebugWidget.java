package ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DebugWidget extends Widget {
    public DebugWidget(float width, float height) {
        setRealDimensions(width, height);
    }

//    @Override public float getPrefWidth() { return realWidth; }
//    @Override public float getPrefHeight() { return realHeight; }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.BLUE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getRealWidth(), getRealHeight());
    }
}
