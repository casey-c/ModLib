package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;

public class DropDownHeader2 extends AbstractButton<DropDownHeader2> {
    public DropDownHeader2(InteractiveWidgetManager interactiveWidgetManager) {
        super(interactiveWidgetManager);

        //hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    // Always grows to fit content
    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // fix hitbox?
        super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);

        if(hb.hovered)
            sb.setColor(ColorHelper.VERY_DIM_MAGENTA);
        else
            sb.setColor(ColorHelper.VERY_DIM_GREEN);

        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

        hb.render(sb);
    }
}
