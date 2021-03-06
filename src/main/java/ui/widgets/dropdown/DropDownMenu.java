package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import input.ClickHelper;
import ui.interactivity.IHasInteractivity;
import ui.interactivity.InteractiveWidgetManager;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.layouts.VerticalLayoutPolicy;
import ui.widgets.Widget;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

import java.util.LinkedList;
import java.util.function.Consumer;

public class DropDownMenu extends Widget<DropDownMenu> implements IHasInteractivity {
    private DropDownHeader header;
    private VerticalLayout bottomLayout;

    private boolean open;
    private InteractiveWidgetManager interactiveWidgetManager;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE_2.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM_2.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM_2.get();
    private static final int CORNER_SIZE = 16;

    private static final Texture TEX_ICON = TextureHelper.TextureItem.DROPDOWN_ICON.get();
    private static final float ICON_HORIZONTAL_OFFSET = 26;

    private DropDownItem last = null;
    private DropDownItem selected = null;
    private boolean initialized;

    // --------------------------------------------------------------------------------

    public DropDownMenu(InteractiveWidgetManager manager) {
        this.interactiveWidgetManager = manager;
        manager.track(this);

        header = new DropDownHeader(interactiveWidgetManager, this, "???");
        bottomLayout = new VerticalLayout();

        ClickHelper.watchLeftClick(this, x -> { clickOutsideHandler(); });
    }

    private void openDropDown() {
        open = true;
        interactiveWidgetManager.enableJustThis(this);
    }

    private void closeDropDown() {
        open = false;
        interactiveWidgetManager.enableAll();
    }

    private void toggle() {
        if (open) closeDropDown();
        else openDropDown();
    }

    private void select(DropDownItem item) { select(item, true);  }
    private void select(DropDownItem item, boolean fireCallback) {
        closeDropDown();
        header.setText(item.getText());

        if (selected != null)
            selected.setSelected(false);

        item.setSelected(true);
        selected = item;

        // Callback
        if (fireCallback)
            item.activate();
    }

    public void clickOutsideHandler() {
        if (!initialized || !header.isCurrentlyInteractive() || !open)
            return;

        float mx = InputHelper.mX;
        float my = InputHelper.mY;

        float left = getContentLeft();
        float right = getContentRight();
        float top = getContentTop();
        float bot = getContentBottom() - bottomLayout.getPrefHeight();

        if ((mx < left || mx > right) || (my < bot || my > top))
            closeDropDown();
    }


    public DropDownMenu withChild(String text, Consumer<DropDownMenu> onSelect) { return withChild(text, false, onSelect); }
    public DropDownMenu withChild(String text, boolean selected, Consumer<DropDownMenu> onSelect) {
        addChild(text, selected, onSelect);
        return this;
    }

    public void addChild(String text, Consumer<DropDownMenu> onSelect) { addChild(text, false, onSelect); }
    public void addChild(String text, boolean selected, Consumer<DropDownMenu> onSelect) {
        if (!initialized)
            setup();

        DropDownItem item = bottomLayout
                .addChild(new DropDownItem(interactiveWidgetManager, text, dropDownItem -> { onSelect.accept(this); }))
                .withOnClick(this::select);

        // Update the last
        if (last != null)
            last.setLast(false);

        item.setLast(true);
        last = item;

        if (selected || this.selected == null)
            select(item, false);

        bottomLayout.computeLayout();
    }


    private void setup() {
        initialized = true;

        header.anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight())
              .withOnClick(onClick -> { toggle(); });

        bottomLayout.anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), 1, AnchorPosition.TOP_LEFT)
                    .withFixedRowHeight(getContentHeight())
                    .withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_FULL);
    }

    // --------------------------------------------------------------------------------

    public boolean selectByString(String text) {
        LinkedList<Widget> children = bottomLayout.getChildren();
        for (Widget c : children) {
            if (c instanceof DropDownItem) {
                DropDownItem item = (DropDownItem) c;
                if (item.getText() == text) {
                    select(item, false);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean selectByIndex(int index) {
        int pos = 0;
        LinkedList<Widget> children = bottomLayout.getChildren();
        for (Widget c : children) {
            if (c instanceof DropDownItem) {
                DropDownItem item = (DropDownItem) c;

                if (pos == index) {
                    select(item, false);
                    return true;
                }

                pos++;
            }
        }

        return false;
    }

    // --------------------------------------------------------------------------------

    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }
    @Override public boolean mustBeRenderedLast() { return open; }

    // --------------------------------------------------------------------------------

    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }

    @Override
    public void update() {
        header.update();
        if (open)
            bottomLayout.update();
    }

    private float getTotalHeight(float height) { return (open) ? height + bottomLayout.getPrefHeight() : height; }
    private float getTotalBottom(float bottom) { return (open) ? bottom - bottomLayout.getPrefHeight() : bottom; }

    // --------------------------------------------------------------------------------
    public void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
//        if (open) sb.setColor(ColorHelper.VERY_DIM_RED);
//        else sb.setColor(ColorHelper.VERY_DIM_BLUE);


        // TODO: fancy corners
        RenderingHelper.renderDynamicBase(sb,
                TEX_CORNER_BASE,
                (int)bottomLeftX,
                (int)getTotalBottom(bottomLeftY),
                (int)width,
                (int)getTotalHeight(height),
                CORNER_SIZE,
                ColorHelper.DROPDOWN_BASE);
//        sb.setColor(ColorHelper.BUTTON_DEFAULT_BASE);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, getTotalBottom(bottomLeftY), width, getTotalHeight(height));
    }


    public void renderItems(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        if (open)
            bottomLayout.render(sb);
        header.render(sb);

        // Icon
        sb.setColor(ColorHelper.FADED_CREAM);
        sb.draw(TEX_ICON, bottomLeftX + width - ICON_HORIZONTAL_OFFSET - TEX_ICON.getWidth(), bottomLeftY + (height - TEX_ICON.getHeight()) * 0.5f);
    }
    public void renderTrim(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // TODO
        RenderingHelper.renderDynamicTrim(sb,
                TEX_CORNER_TRIM,
                TEX_EDGE_TRIM,
                (int)bottomLeftX,
                (int)getTotalBottom(bottomLeftY),
                (int)width,
                (int)getTotalHeight(height),
                CORNER_SIZE,
                ColorHelper.BUTTON_TRIM );

        if (open) {
            RenderingHelper.renderDynamicTrim(sb,
                    TEX_CORNER_TRIM,
                    TEX_EDGE_TRIM,
                    (int)bottomLeftX,
                    (int)bottomLeftY,
                    (int)width,
                    (int)height,
                    CORNER_SIZE,
                    ColorHelper.BUTTON_TRIM );
        }
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        renderBackground(sb, bottomLeftX, bottomLeftY, width, height);
        renderItems(sb, bottomLeftX, bottomLeftY, width, height);
        renderTrim(sb, bottomLeftX, bottomLeftY, width, height);
    }

    @Override
    public void enableInteractivity() {
        header.enableInteractivity();
        for (Widget w : bottomLayout.getChildren()) {
            if (w instanceof DropDownItem) {
                DropDownItem item = (DropDownItem) w;
                item.enableInteractivity();
            }
        }
    }

    @Override
    public void disableInteractivity() {
        interactiveWidgetManager.enableAll();
    }

    @Override
    public void show() {
        header.show();
        bottomLayout.show();
    }

    @Override
    public void hide() {
        header.hide();
        bottomLayout.hide();
    }
}
