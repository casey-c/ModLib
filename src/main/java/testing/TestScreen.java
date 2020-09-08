package testing;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.*;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;

public class TestScreen extends LargeScreen<GridLayout> {
    public TestScreen() {
        mainLayout = GridLayout.build(getContentWidth(), getContentHeight())
                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
                .with_balanced_cols(2)
                .with_balanced_rows(2);

//        mainLayout = VerticalLayout
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT);

//                .(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .with_balanced_cols(2)
//                .with_balanced_rows(2);

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

        // TODO: have .setRawLayout return the layout, so we can pass in .buildRaw() as the param (condense to one line)
        //   * also have .setRawLayout take in an anchor?


        // 0, 0
        VerticalLayout l0 = VerticalLayout.buildRaw();
        mainLayout.setRawLayout(0, 0, l0);

        l0.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
        l0.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
        l0.print();

        // 0, 1
        VerticalLayout l1 = VerticalLayout.buildRaw();
        mainLayout.setRawLayout(0, 1, l1);

        l1.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
        l1.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
        l1.print();

        // 1, 0
        VerticalLayout l2 = VerticalLayout.buildRaw();
        mainLayout.setRawLayout(1, 0, l2);

        l2.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
        l2.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
        l2.print();

        // 1, 1
        VerticalLayout l3 = VerticalLayout.buildRaw();
        mainLayout.setRawLayout(1, 1, l3);

        l3.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
        l3.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
        l3.print();
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


        System.out.println("------");

        mainLayout.print();
    }

    private void nestedLayoutTest() {
//        mainLayout = VerticalLayout
//                .build( getContentWidth(), getContentHeight() )
//                .anchoredAt(getContentLeft(), getContentTop(), AnchorPosition.TOP_LEFT)
//                .withSpacing(40.0f);
//
//        HorizontalLayout innerLayout = HorizontalLayout
//                .build(getContentWidth(), 80.0f)
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .withSpacing(40.0f);
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
