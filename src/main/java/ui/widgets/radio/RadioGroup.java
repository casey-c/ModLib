package ui.widgets.radio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.interactivity.IHasInteractivity;
import ui.interactivity.InteractiveWidgetManager;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout;
import ui.widgets.Widget;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RadioGroup extends Widget<RadioGroup> implements IHasInteractivity {
    private InteractiveWidgetManager interactiveWidgetManager;
    private VerticalLayout verticalLayout;

    public RadioGroup(InteractiveWidgetManager interactiveWidgetManager) {
        interactiveWidgetManager.track(this);

        this.interactiveWidgetManager = interactiveWidgetManager;
        this.verticalLayout = new VerticalLayout().withSpacing(30);
    }

    @Override
    public void setActualFromAnchor(float x, float y, float width, float height, AnchorPosition anchor) {
        super.setActualFromAnchor(x, y, width, height, anchor);
        this.verticalLayout.setActualFromAnchor(x, y, width, height, anchor);

        System.out.println("Anchored the radio group");
        verticalLayout.print();
    }

    public void addChild(String text, boolean startSelected, Consumer<RadioGroup> onChoose) {
        RadioButton child = new RadioButton(interactiveWidgetManager, this, text, onChoose);
        verticalLayout.addChild(child);

        if (startSelected)
            select(child, false);
    }

    public void addChild(String text, Consumer<RadioGroup> onChoose) {
        RadioButton child = new RadioButton(interactiveWidgetManager, this, text, onChoose);
        verticalLayout.addChild(child);

        // Default to select the first child
        if (verticalLayout.getChildren().size() == 1) {
            select(child, false);
        }

        verticalLayout.computeLayout();
    }

    private void deselectAll() {
        for (Widget child : verticalLayout.getChildren()) {
            if (child instanceof RadioButton) {
                RadioButton button = (RadioButton) child;
                button.setSelected(false);
            }
        }
    }

    protected void select(RadioButton button) {
        select(button, true);
    }

    protected void select(RadioButton button, boolean callback) {
        deselectAll();
        button.setSelected(true, callback);
    }

    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }


    @Override
    public void enableInteractivity() {
        for (Widget child : verticalLayout.getChildren()) {
            if (child instanceof RadioButton) {
                RadioButton button = (RadioButton) child;
                button.enableInteractivity();
            }
        }
    }

    @Override
    public void disableInteractivity() {
        for (Widget child : verticalLayout.getChildren()) {
            if (child instanceof RadioButton) {
                RadioButton button = (RadioButton) child;
                button.disableInteractivity();
            }
        }
    }

    @Override public void show() { verticalLayout.show(); }
    @Override public void hide() { verticalLayout.hide(); }

    @Override public void update() { verticalLayout.update(); }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        verticalLayout.render(sb);
    }
}
