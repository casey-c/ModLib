package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import input.ClickHelper;
import ui.GrowthPolicy;
import ui.interactivity.IHasInteractivity;
import ui.interactivity.InteractiveWidgetManager;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.layouts.VerticalLayoutPolicy;
import ui.widgets.Widget;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TextDropDownMenu extends Widget<TextDropDownMenu> implements IHasInteractivity {
    private boolean showingDropDown;

    private TextDropDownMenuItem selectedItem;
    private ArrayList<TextDropDownMenuItem> items = new ArrayList<>();
    private VerticalLayout itemLayout;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM.get();
    private final int CORNER_SIZE = 16;

    private InteractiveWidgetManager interactiveWidgetManager;

    public TextDropDownMenu(InteractiveWidgetManager manager) {
        this.contentAnchorPosition = AnchorPosition.TOP_LEFT;
        this.interactiveWidgetManager = manager;

        itemLayout = new VerticalLayout()
                .withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_MAX)
                .withGlobalChildAnchor(contentAnchorPosition);

        // Make sure we can enable / disable interactivity
        manager.track(this);

        ClickHelper.watchLeftClick(this, x -> { clickOutsideHandler();});
    }

    public void clickOutsideHandler() {
        if (!interactive || !showingDropDown)
            return;

        float mx = InputHelper.mX;
        float my = InputHelper.mY;

        //System.out.println("DropDownController found a left click: " + mx + ", " + my);
        //itemLayout.print();

        float left = itemLayout.getLeft();
        float right = itemLayout.getRight();
        float top = itemLayout.getTop();
        float bot = top - itemLayout.getPrefHeight();

        if ((mx < left || mx > right) || (my < bot || my > top)) {
            //System.out.println("Mouse not in bounds");
            select(selectedItem);
        }
        else {
            //System.out.println("Mouse in bounds");
        }

    }

    public boolean selectByIndex(int index) {
        if (index > 0 && index < items.size()) {
            this.selectedItem = items.get(index);
            selectedItem.notifySelect();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean selectByString(String text) {
        for (TextDropDownMenuItem item : items) {
            if (item.getText() == text) {
                this.selectedItem = item;
                selectedItem.notifySelect();
                return true;
            }
        }

        return false;
    }


//    @Override public float getPrefWidth() { return selectedItem == null ? 0 : selectedItem.getPrefWidth(); }
//    @Override public float getPrefHeight() { return selectedItem == null ? 0 : selectedItem.getPrefHeight(); }

    public boolean isShowingDropDown() { return showingDropDown; }

    public void addItem(String text, Consumer<TextDropDownMenu> onSelect) {
        TextDropDownMenuItem item = new TextDropDownMenuItem(interactiveWidgetManager, this, text, onSelect);
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

    public void select(TextDropDownMenuItem item) {
        if (!interactive)
            return;

        if (showingDropDown) {
            this.selectedItem = item;
            disableDropDown();
            selectedItem.notifySelect();
        }
        else {
            enableDropDown();
        }
    }


    // --------------------------------------------------------------------------------

    private void disableDropDown() {
        showingDropDown = false;

        // Update the hitboxes
        itemLayout.hide();
        selectedItem.show();

        interactiveWidgetManager.enableAll();
    }

    private void enableDropDown() {
        showingDropDown = true;

        // Enable the hitboxes
        itemLayout.show();

        interactiveWidgetManager.enableJustThis(this);


        interactive = true;
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
    // TODO: make this not copypasted from DropDownItem

    public Color getBaseColor() {
        //return (hb != null && hb.hovered) ? ColorHelper.VERY_DIM_GREEN : ColorHelper.VERY_DIM_MAGENTA;
        return ColorHelper.BUTTON_DEFAULT_BASE;
    }

    public Color getTrimColor() {
        return ColorHelper.BUTTON_TRIM;
    }

    // --------------------------------------------------------------------------------


    @Override public boolean mustBeRenderedLast() { return showingDropDown; }

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
            bottom = itemLayout.getLayoutBottom();
            height = itemLayout.getPrefHeight();

            // Render background
            RenderingHelper.renderDynamicBase(sb, TEX_CORNER_BASE, (int)left, (int)bottom, (int)width, (int)height, CORNER_SIZE, getBaseColor());

            // literally doesn't matter what params we set, since VertLayouts just call child.render(sb), not renderAt
            itemLayout.renderAt(sb, left, bottom, width, height);

            // Render trim
            RenderingHelper.renderDynamicTrim(sb, TEX_CORNER_TRIM, TEX_EDGE_TRIM, (int)left, (int)bottom, (int)width, (int)height, CORNER_SIZE, getTrimColor());

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

    private boolean interactive = true;
    @Override public boolean isCurrentlyInteractive() { return interactive; }
    @Override public void enableInteractivity() {
        interactive = true;
        for (TextDropDownMenuItem item : items)
            item.enableInteractivity();
    }
    @Override public void disableInteractivity() { interactive = false; }

}
