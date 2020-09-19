package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.widgets.Widget;
import ui.widgets.buttons.AbstractButton;
import utils.ColorHelper;

public class DropDownController extends AbstractButton<DropDownController> {
    VerticalLayout layout = new VerticalLayout();
    boolean dropDownVisible;
    String text = "Choice 1";

    private BitmapFont font = FontHelper.tipBodyFont;
    private Color fontColor = Settings.CREAM_COLOR;
    private static final float TEXT_OFFSET_X = 8;
    private static final float TEXT_OFFSET_Y = 7;

    public DropDownController() {
        layout.addChild(new DropDownItem("Choice 1")).withOnClick(item -> {
            selectDropDownItem(item);
        });
        layout.addChild(new DropDownItem("Choice 2")).withOnClick(item -> {
            selectDropDownItem(item);
        });

        hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    private void selectDropDownItem(DropDownItem item) {
        this.text = item.getText();
        System.out.println("Selected " + text);
        disableDropDown();
    }

    private void enableDropDown() {
        dropDownVisible = true;

        for (Widget child : layout.getChildren()) {
            if (!(child instanceof DropDownItem))
                continue;
            DropDownItem item = (DropDownItem)child;
            item.show();
        }

        hide();
    }


    private void disableDropDown() {
        dropDownVisible = false;

        for (Widget child : layout.getChildren()) {
            if (!(child instanceof DropDownItem))
                continue;
            DropDownItem item = (DropDownItem)child;

            item.hide();
        }

        show();
    }

    @Override
    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
        super.setActualFromAnchor(x, y, width, height, anchor);

        layout.setActualFromAnchor(getContentLeft(), getContentTop(), getContentWidth(), getContentHeight(), AnchorPosition.TOP_LEFT);
        layout.computeLayout();
    }

    @Override
    public void click() {
        super.click();

        if (dropDownVisible) disableDropDown();
        else enableDropDown();

        System.out.println("Controller clicked: dropDownVisible is now: " + dropDownVisible);
    }

    // TODO
    @Override public float getPrefWidth() { return 250; }
    @Override public float getPrefHeight() { return 40; }

    @Override
    public void update() {
        super.update();
        layout.update();
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Necessary for hitbox updates
        super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);

        if (dropDownVisible) {
            // no, this doesn't fix the issue
            //float layoutHeight = layout.getPrefHeight();
            //layout.renderAt(sb, bottomLeftX, bottomLeftY - layoutHeight + height, width, layoutHeight);
            
            layout.render(sb);
        }
        else {
            sb.setColor(ColorHelper.VERY_DIM_CYAN);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

            FontHelper.renderFontLeftDownAligned(sb, font, text, bottomLeftX + TEXT_OFFSET_X, bottomLeftY + (0.5f * height) - (0.5f * font.getLineHeight()) + TEXT_OFFSET_Y, fontColor);
        }

        hb.render(sb);
    }
}
