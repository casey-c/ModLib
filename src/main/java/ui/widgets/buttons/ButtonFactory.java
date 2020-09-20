package ui.widgets.buttons;

import ui.interactivity.InteractiveWidgetManager;
import utils.ColorHelper;
import utils.TextureHelper;

public class ButtonFactory {
    public static IconButton cancelButton(InteractiveWidgetManager manager) {
        return new IconButton(manager, TextureHelper.TextureItem.X_ICON.get(),
                ColorHelper.ICON_COLOR,
                ColorHelper.CANCEL_BUTTON_BASE,
                ColorHelper.CANCEL_BUTTON_HOVER,
                ColorHelper.CANCEL_BUTTON_CLICK);
    }

    public static IconButton resetButton(InteractiveWidgetManager manager) {
        return new IconButton(manager, TextureHelper.TextureItem.RESET_ICON.get(), ColorHelper.ICON_COLOR);
    }

}
