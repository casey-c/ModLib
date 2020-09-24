package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
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
import utils.SoundHelper;
import utils.TextureHelper;

public class DropDownMenu2 extends Widget<DropDownMenu2> implements IHasInteractivity {
    private DropDownHeader2 header;
    private VerticalLayout bottomLayout;

    private boolean open;
    private InteractiveWidgetManager interactiveWidgetManager;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE_2.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM_2.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM_2.get();
    private static final int CORNER_SIZE = 16;

    // --------------------------------------------------------------------------------

    public DropDownMenu2(InteractiveWidgetManager manager) {
        this.interactiveWidgetManager = manager;
        manager.track(this);

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

    private void select(DropDownItem2 item) {
        closeDropDown();
        header.setText(item.getText());
    }

    public void clickOutsideHandler() {
        if (!header.isCurrentlyInteractive() || !open)
            return;

        float mx = InputHelper.mX;
        float my = InputHelper.mY;

        //System.out.println("DropDownController found a left click: " + mx + ", " + my);
        //itemLayout.print();

        float left = getContentLeft();
        float right = getContentRight();
        float top = getContentTop();
        float bot = getContentBottom() - bottomLayout.getPrefHeight();

        if ((mx < left || mx > right) || (my < bot || my > top)) {
            //System.out.println("Mouse not in bounds");
            //select(selectedItem);
            closeDropDown();
        }
        else {
            //System.out.println("Mouse in bounds");
        }

    }

    public void setup() {
        header = new DropDownHeader2(interactiveWidgetManager, this)
                .anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight())
                .withOnClick(onClick -> { toggle(); });

        bottomLayout = new VerticalLayout()
                .anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), 1, AnchorPosition.TOP_LEFT)
                .withFixedRowHeight(getContentHeight())
                .withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_FULL);

        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager, "Choice 1")).withOnClick(this::select);
        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager, "Choice 2")).withOnClick(this::select);
        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager, "Choice 3")).withOnClick(this::select).setLast(true);
        bottomLayout.computeLayout();

        // TODO select defaults etc.
        header.setText("Choice 1");
    }

    // --------------------------------------------------------------------------------

    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }

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
            if (w instanceof DropDownItem2) {
                DropDownItem2 item = (DropDownItem2) w;
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
