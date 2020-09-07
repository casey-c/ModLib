package testing;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.*;
import ui.screens.AbstractScreen;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;

public class TestScreen extends LargeScreen<HorizontalLayout> {
    public TestScreen() {
        mainLayout = HorizontalLayout
                .build( SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X, SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y)
                .anchoredAt(CONTENT_MIN_X, CONTENT_MIN_Y, LayoutAnchorPosition.BOTTOM_LEFT)
                .withSpacing(40.0f);

        mainLayout.addChild(new SimpleLabel("Hello, world", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
        mainLayout.addChild(new SimpleLabel("TEST", FontHelper.tipBodyFont, Settings.GOLD_COLOR));

        //nestedLayoutTest();
//        mainLayout = GridLayout
//                .build(CONTENT_MIN_X, CONTENT_MIN_Y,
//                        SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
//                        SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y)
//                .with_balanced_rows(2)
//                .with_balanced_cols(2);
//
//
//        System.out.println("------");
//        VerticalLayout l0 = mainLayout.makeVerticalLayoutAt(0, 0, 40.0f);
//        l0.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
//        l0.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
//        l0.print();
//
//        VerticalLayout l1 = mainLayout.makeVerticalLayoutAt(0, 1, 40.0f);
//        l1.addChild(new SimpleLabel("L1 c0", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
//        l1.addChild(new SimpleLabel("L1 c1", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
//        l1.print();
//
//        VerticalLayout l2 = mainLayout.makeVerticalLayoutAt(1, 0, 40.0f);
//        l2.addChild(new SimpleLabel("L2 c0", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
//        l2.addChild(new SimpleLabel("L2 c1", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
//        l2.print();
//
//        HorizontalLayout l3 = mainLayout.makeHorizontalLayoutAt(1, 1, 40.0f);
//        l3.addChild(new SimpleLabel("L3 c0", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
//        l3.addChild(new SimpleLabel("L3 c1", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
//        l3.print();

        // TODO: add orientation to layouts? like is it anchored bottomLeft, topLeft, br, or tr


        System.out.println("------");

        mainLayout.print();
    }

    private void nestedLayoutTest() {
//        mainLayout = new VerticalLayout(
//                CONTENT_MIN_X,
//                CONTENT_MIN_Y,
//                SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
//                SCREEN_H - 2.0f * CONTENT_OUTER_PADDING_Y,
//                40.0f
//        );
//
//        HorizontalLayout innerLayout = new HorizontalLayout(
//                CONTENT_MIN_X,
//                CONTENT_MIN_Y,
//                SCREEN_W - 2.0f * CONTENT_OUTER_PADDING_X,
//                80.0f,
//                40.0f
//        );
//
//        mainLayout.addChild(new SimpleLabel("Test Screen", FontHelper.bannerFont, Settings.GOLD_COLOR));
//        mainLayout.addChild(new SimpleLabel("Hello, world", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
//        mainLayout.addChild(SimplePadding.vertical(20.0f));
//
//        mainLayout.addChild(innerLayout);
//        innerLayout.addChild(new SimpleLabel("More Text", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
//        innerLayout.addChild(new SimpleLabel("YESSSSSS", FontHelper.tipBodyFont, Settings.BLUE_TEXT_COLOR));
//
//        mainLayout.addChild(new SimpleLabel("Hello, world", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
    }
}
