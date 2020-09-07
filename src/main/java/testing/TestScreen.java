package testing;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.HorizontalLayout;
import ui.layouts.Layout;
import ui.layouts.VerticalLayout;
import ui.screens.AbstractScreen;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;

public class TestScreen extends LargeScreen<VerticalLayout> {
    public TestScreen() {
        mainLayout = new VerticalLayout(
                CONTENT_MIN_X, CONTENT_MAX_Y,
                SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
                SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y,
                40.0f,
                40.0f
        );

        mainLayout.addChild(new SimpleLabel("Test Screen", FontHelper.bannerFont, Settings.GOLD_COLOR));
        mainLayout.addChild(new SimpleLabel("Hello, world", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
    }
}
