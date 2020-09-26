package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jetbrains.annotations.Nullable;
import ui.widgets.Widget;

import java.util.HashMap;

public class LayoutSwitcher<T extends Enum<T>> extends Widget<LayoutSwitcher<T>> {
    private HashMap<T, GridLayout> layouts = new HashMap<>();
    private GridLayout selectedLayout;

    public LayoutSwitcher(Widget parent, Class<T> enumType) {
        anchoredAt(parent);

        System.out.println("Making layout switcher: ");
        for (T e : java.util.EnumSet.allOf(enumType)) {
            GridLayout g = new GridLayout().anchoredAt(parent);
            layouts.put(e, g);

            System.out.println(e.name());

            // select the first element in the enum
            if (selectedLayout == null)
                selectedLayout = g;
        }

        System.out.println("\nMade a layout switcher with " + layouts.size() + " different layouts");
        print();
    }

    public void selectLayout(T layout) {
        if (layouts.containsKey(layout)) {
            // TODO: might need to call hide/show on layout swaps
//            Layout oldLayout = selectedLayout;
            selectedLayout = layouts.get(layout);

//            if (oldLayout != selectedLayout) {
//                oldLayout.hide();
//                selectedLayout.show();
//            }
        }
    }

    public @Nullable GridLayout getSelectedLayout() { return selectedLayout; }

    public @Nullable  GridLayout getLayoutByName(T layout) {
        if (layouts.containsKey(layout))
            return layouts.get(layout);
        else
            return null;
    }

    @Override public float getPrefWidth() { return getContentWidth(); }
    @Override public float getPrefHeight() { return getContentHeight(); }

    @Override
    public void update() {
        if (selectedLayout != null)
            selectedLayout.update();
    }

    @Override
    public void show() {
        if (selectedLayout != null)
            selectedLayout.show();
    }

    @Override
    public void hide() {
        if (selectedLayout != null)
            selectedLayout.hide();
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        if (selectedLayout != null) {
            selectedLayout.render(sb);
        }
    }
}
