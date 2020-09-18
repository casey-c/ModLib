package testing;

import basemod.TopPanelItem;
import utils.TextureHelper;

public class TestClass extends TopPanelItem {
    private TestScreen screen;

    public TestClass() {
        super(TextureHelper.getTexture(TextureHelper.TextureItem.ICON), TextureHelper.MOD_ID + ":ICON");
        //super(TextureManager.getTexture("ModLib", "ICON"), "ModLib:TEST");
    }

    @Override
    protected void onClick() {
        System.out.println("OJB: top panel item clicked");

        if (screen == null)
            screen = new TestScreen(780, 650);

        //screen.print();

        if (screen.isVisible())
            screen.hide();
        else
            screen.show();
    }
}
