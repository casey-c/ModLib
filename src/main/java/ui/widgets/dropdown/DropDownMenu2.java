package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.IHasInteractivity;
import ui.interactivity.InteractiveWidgetManager;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.layouts.VerticalLayoutPolicy;
import ui.widgets.Widget;
import utils.ColorHelper;
import utils.SoundHelper;

public class DropDownMenu2 extends Widget<DropDownMenu2> implements IHasInteractivity {
    private DropDownHeader2 header;
    private VerticalLayout bottomLayout;

    private boolean open;
    private InteractiveWidgetManager interactiveWidgetManager;

    // --------------------------------------------------------------------------------

    public DropDownMenu2(InteractiveWidgetManager manager) {
        this.interactiveWidgetManager = manager;
        manager.track(this);
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
    }

    public void setup() {
        header = new DropDownHeader2(interactiveWidgetManager)
                .anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight())
                .withOnClick(onClick -> {
                    toggle();
                    if (open) SoundHelper.cawCaw();
                });

        System.out.println("Made drop down menu");
        print();
        header.print();

        bottomLayout = new VerticalLayout()
                .anchoredAt(getContentLeft(), getContentBottom(), getContentWidth(), 1, AnchorPosition.TOP_LEFT)
                .withFixedRowHeight(getContentHeight())
                .withChildExpansionPolicy(VerticalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_FULL);

        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager)).withOnClick(this::select);
        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager)).withOnClick(this::select);
        bottomLayout.addChild(new DropDownItem2(interactiveWidgetManager)).withOnClick(this::select);
        bottomLayout.computeLayout();

        bottomLayout.print();
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

    // --------------------------------------------------------------------------------

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        if (open)
            sb.setColor(ColorHelper.VERY_DIM_RED);
        else
            sb.setColor(ColorHelper.VERY_DIM_BLUE);

        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

        // TODO: render layout
        if (open)
            bottomLayout.render(sb);

        header.render(sb);
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
