package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.relics.WhiteBeast;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.layouts.VerticalLayoutPolicy;
import ui.widgets.Widget;
import ui.widgets.buttons.AbstractButton;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DropDownController2 extends Widget<DropDownController2> {
    private boolean showingDropDown;

    private DropDownItem2 selectedItem;
    private ArrayList<DropDownItem2> items = new ArrayList<>();
    private VerticalLayout itemLayout;

    public DropDownController2() {
        this.contentAnchorPosition = AnchorPosition.TOP_LEFT;

        itemLayout = new VerticalLayout()
                .withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_MAX)
                .withGlobalChildAnchor(contentAnchorPosition);
    }


//    @Override public float getPrefWidth() { return selectedItem == null ? 0 : selectedItem.getPrefWidth(); }
//    @Override public float getPrefHeight() { return selectedItem == null ? 0 : selectedItem.getPrefHeight(); }

    public void addItem(String text, Consumer<DropDownController2> onSelect) {
        DropDownItem2 item = new DropDownItem2(this, text, onSelect);
        items.add(item);

        // Auto select the first one we add to the list (TODO: better / more customizable options (e.g. default values, etc.))
        if (selectedItem == null) {
            selectedItem = item;
        }

        // Add to the layout
        itemLayout.addChild(item);

        // Convenience to do it now (not as efficient as waiting until all adds complete, but it shouldn't be an issue)
        itemLayout.computeLayout();
    }

    // --------------------------------------------------------------------------------

    public void select(DropDownItem2 item) {
        if (showingDropDown) {
            this.selectedItem = item;
            disableDropDown();
            selectedItem.notifySelect();
        }
        else {
            enableDropDown();
        }

        System.out.println("Selected an item");
        print();
        itemLayout.print();
        selectedItem.print();
    }

    // --------------------------------------------------------------------------------

    private void disableDropDown() {
        showingDropDown = false;

        // Update the hitboxes
        itemLayout.hide();
        selectedItem.show();
    }

    private void enableDropDown() {
        showingDropDown = true;

        // Enable the hitboxes
        itemLayout.show();
    }

    // --------------------------------------------------------------------------------
    // Pass through all the options to the layout

    @Override
    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
        super.setActualFromAnchor(x, y, width, height, anchor);
        itemLayout.setActualFromAnchor(x, y, width, height, anchor);
        itemLayout.computeLayout();

        if (selectedItem != null) selectedItem.setHitboxNeedsFixing();
    }

    @Override
    public void setGrowthPolicy(GrowthPolicy policy) {
        super.setGrowthPolicy(policy);
        itemLayout.setGrowthPolicy(policy);

        if (policy.isExpandingWidth())
            itemLayout.withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_FULL);
        else
            itemLayout.withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_MAX);

        itemLayout.computeLayout();
        if (selectedItem != null) selectedItem.setHitboxNeedsFixing();
    }

    @Override
    public void setMargins(float left, float right, float top, float bottom) {
        super.setMargins(left, right, top, bottom);
        itemLayout.setMargins(left, right, top, bottom);
        itemLayout.computeLayout();
        if (selectedItem != null) selectedItem.setHitboxNeedsFixing();
    }

    @Override
    public float getPrefWidth() {
        return itemLayout.getPrefWidth();
    }

    @Override
    public float getPrefHeight() {
        return itemLayout.maxChildPrefHeight();
    }

    @Override
    public void setContentAnchorPosition(AnchorPosition position) {
        super.setContentAnchorPosition(position);
        itemLayout.setContentAnchorPosition(position);
        itemLayout.setGlobalChildAnchor(position);
        itemLayout.computeLayout();
        if (selectedItem != null) selectedItem.setHitboxNeedsFixing();
    }


    // --------------------------------------------------------------------------------

    @Override
    public void render(SpriteBatch sb) {
        // This is Widget::render(sb) but slightly modified

        // Dimensions

//        if (showingDropDown) {
//            width = (growthPolicy.isExpandingWidth()) ? getContentWidth() : itemLayout.getPrefWidth();
//            height = (growthPolicy.isExpandingHeight()) ? getContentHeight() : itemLayout.getPrefHeight();
//        }
//        else {
            float width = (growthPolicy.isExpandingWidth()) ? getContentWidth() : getPrefWidth();
            //float height = (growthPolicy.isExpandingHeight()) ? getContentHeight() : getPrefHeight();
            float height = getPrefHeight();
//        }

        // Position
        float left = getContentLeft();
        float bottom = getContentBottom();

        if (contentAnchorPosition.isCentralX())
            left = getContentCenterX() - (width * 0.5f);
        else if (contentAnchorPosition.isRight())
            left = getContentRight() - width;

        if (contentAnchorPosition.isCentralY())
            bottom = getContentCenterY() - (height * 0.5f);
        else if (contentAnchorPosition.isTop())
            bottom = getContentTop() - height;

        // Render the layout
        if (showingDropDown) {
//            System.out.println("Rendering drop down");
//            System.out.println(left);
//            System.out.println(bottom);
//            System.out.println(width);
//            System.out.println(height);
//            System.out.println();

            // literally doesn't matter what params we set, since VertLayouts just call child.render(sb), not renderAt
            itemLayout.renderAt(sb, left, bottom, width, height);

//            print();
//            itemLayout.print();

        } else if (selectedItem != null) {
//            System.out.println("Rendering no drop down");
//            System.out.println(left);
//            System.out.println(bottom);
//            System.out.println(width);
//            System.out.println(height);
//            System.out.println();
            selectedItem.renderAt(sb, left, bottom, width, height);

//            print();
//            selectedItem.print();
        }


    }

    @Override
    public void update() {
        super.update();
        if (showingDropDown)
            itemLayout.update();
        else
            selectedItem.update();
    }

    @Override public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) { }
}
