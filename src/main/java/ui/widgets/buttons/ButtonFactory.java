package ui.widgets.buttons;

import utils.ColorHelper;
import utils.TextureHelper;

public class ButtonFactory {
    public static IconButton cancelButton() {
        return new IconButton(TextureHelper.TextureItem.X_ICON.get(),
                ColorHelper.ICON_COLOR,
                ColorHelper.CANCEL_BUTTON_BASE,
                ColorHelper.CANCEL_BUTTON_HOVER,
                ColorHelper.CANCEL_BUTTON_CLICK);
    }
}
