package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.layouts.VerticalLayoutPolicy;
import ui.widgets.Widget;
import ui.widgets.buttons.AbstractButton;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;

public class DropDownController extends AbstractButton<DropDownController> {
    VerticalLayout layout = new VerticalLayout();
    boolean dropDownVisible;
    //String text = "Choice 1";

    private SimpleLabel selected;

    private BitmapFont font = FontHelper.tipBodyFont;
    private Color fontColor = Settings.CREAM_COLOR;
    private static final float TEXT_OFFSET_X = 8;
    private static final float TEXT_OFFSET_Y = 7;

    public DropDownController() {
        layout.withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_FULL);

        layout.addChild(new DropDownItem("Choice 1")).withOnClick(this::selectDropDownItem);
        layout.addChild(new DropDownItem("Choice 2")).withOnClick(this::selectDropDownItem);
        layout.addChild(new DropDownItem("Choice 3 is longer")).withOnClick(this::selectDropDownItem);
        layout.addChild(new DropDownItem("Choice 4")).withOnClick(this::selectDropDownItem);
        layout.addChild(new DropDownItem("Choice 5")).withOnClick(this::selectDropDownItem);

        hb = new Hitbox(getPrefWidth(), getPrefHeight());

         selected = new SimpleLabel("???").withOffsets(8, 7);
    }

    private void selectDropDownItem(DropDownItem item) {
        //this.text = item.getText();
        selected.setText(item.getText());
        System.out.println("Selected " + item.getText());
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

    private void setAllGrowthPolicies(GrowthPolicy policy) {
        layout.setGrowthPolicy(growthPolicy);
        for (Widget child : layout.getChildren())
            child.setGrowthPolicy(policy);
    }

    private void updateLayoutPosition() {
        // Dimensions
        float controllerWidth = (growthPolicy.isExpandingWidth()) ? getContentWidth() : getPrefWidth();
        float controllerHeight = (growthPolicy.isExpandingHeight()) ? getContentHeight() : getPrefHeight();

        // Position
        float controllerLeft = getContentLeft();
        float controllerBottom = getContentBottom();

        if (contentAnchorPosition.isCentralX())
            controllerLeft = getContentCenterX() - (controllerWidth * 0.5f);
        else if (contentAnchorPosition.isRight())
            controllerLeft = getContentRight() - controllerWidth;

        if (contentAnchorPosition.isCentralY())
            controllerBottom = getContentCenterY() - (controllerHeight * 0.5f);
        else if (contentAnchorPosition.isTop())
            controllerBottom = getContentTop() - controllerHeight;

        float controllerTop = controllerBottom + controllerHeight;

        layout.setActualFromAnchor(controllerLeft, controllerTop, controllerWidth, layout.getPrefHeight(), AnchorPosition.TOP_LEFT);
        layout.setContentAnchorPosition(contentAnchorPosition);
        setAllGrowthPolicies(growthPolicy);

        layout.computeLayout();

        System.out.println("layout is now set");
        print();
        layout.print();

        // forces a hitbox recompute?
        show();
    }

    @Override
    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
        super.setActualFromAnchor(x, y, width, height, anchor);
        updateLayoutPosition();
    }

    @Override
    public void setContentAnchorPosition(AnchorPosition position) {
        super.setContentAnchorPosition(position);
        updateLayoutPosition();
    }

    @Override
    public void setGrowthPolicy(GrowthPolicy policy) {
        super.setGrowthPolicy(policy);
        updateLayoutPosition();
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
        //super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);

        if (dropDownVisible) {
            layout.render(sb);
        }
        else {
//            if (layout.getChildren().size() > 0) {
//                DropDownItem w = (DropDownItem) layout.getChildren().getFirst();
//                w.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
//            }

            sb.setColor(ColorHelper.VERY_DIM_CYAN);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

            selected.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
//
//            FontHelper.renderFontLeftDownAligned(sb, font, text, bottomLeftX + TEXT_OFFSET_X, bottomLeftY + (0.5f * height) - (0.5f * font.getLineHeight()) + TEXT_OFFSET_Y, fontColor);
        }

        hb.render(sb);
    }
}
