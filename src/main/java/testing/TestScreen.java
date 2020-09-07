package testing;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.Layout;
import ui.layouts.VerticalLayout;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;

public class TestScreen extends LargeScreen {
    public TestScreen() {
        ((VerticalLayout)mainLayout).addChild(new SimpleLabel("Test Screen", FontHelper.bannerFont, Settings.GOLD_COLOR));
        //((VerticalLayout)mainLayout).addChild(SimplePadding.vertical(20.0f));
        ((VerticalLayout)mainLayout).addChild(new SimpleLabel("Hello, world", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
    }
}
