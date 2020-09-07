package testing;

import basemod.TopPanelItem;
import ui.screens.LargeScreen;
import utils.TextureManager;

public class TestClass extends TopPanelItem {
    private LargeScreen screen;

    public TestClass() {
        super(TextureManager.getTexture("ModLib", "ICON"), "ModLib:TEST");
    }

    @Override
    protected void onClick() {
        System.out.println("OJB: top panel item clicked");

        if (screen == null)
            screen = new LargeScreen();

        if (screen.isVisible())
            screen.hide();
        else
            screen.show();
    }
}
