package testing;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.*;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;

public class TestScreen extends LargeScreen<GridLayout> {
    public TestScreen() {
        this.CONTENT_PADDING_X = 100.0f;
        this.CONTENT_PADDING_Y = 40.0f;

        mainLayout = GridLayout
                .build(getContentWidth(), getContentHeight())
                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
                .with_relative_rows(8, 1)
                .with_balanced_cols(1);

        HorizontalLayout upper = (HorizontalLayout)mainLayout.setRawLayout(1,0, HorizontalLayout.buildRaw(), AnchorPosition.BOTTOM_LEFT);
        upper.addChild(new SimpleLabel("TEST BANNER", FontHelper.bannerFont, Settings.GOLD_COLOR));
        upper.addChild(SimplePadding.horizontal(200.0f));
        upper.addChild(new SimpleLabel("Hello, world"));

        VerticalLayout bottom = ((VerticalLayout)mainLayout.setRawLayout(0, 0, VerticalLayout.buildRaw(), AnchorPosition.TOP_LEFT)).withSpacing(20.0f);
        bottom.addChild(SimplePadding.vertical(50.0f));
        for (int i = 0; i < 10; ++i)
            bottom.addChild(new SimpleLabel("Test " + i));
    }

    private void dynGridTest() {
//        int numRows = 4;
//        int numCols = 3;
//
//        mainLayout = GridLayout
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .with_balanced_rows(numRows)
//                .with_balanced_cols(numCols);
//
//        for (int i = 0; i < numRows; ++i) {
//            for (int j = 0; j < numCols; ++j) {
//                VerticalLayout layout = (VerticalLayout)mainLayout.setRawLayout(i, j, VerticalLayout.buildRaw(), AnchorPosition.TOP_LEFT);
//                layout.addChild(new SimpleLabel("Hello " + i + j));
//                layout.addChild(new SimpleLabel("Goodbye " + i + j));
//            }
//        }
    }

    private void balancedGridTest() {
//        mainLayout = GridLayout.build(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .with_balanced_cols(2)
//                .with_balanced_rows(2);
//
//        // 0, 0
//        AnchorPosition pos = AnchorPosition.TOP_LEFT;
//
//        HorizontalLayout l0 = (HorizontalLayout)mainLayout.setRawLayout(0, 0, HorizontalLayout.buildRaw(), pos);
//        l0.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
//        l0.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.CREAM_COLOR));
//        l0.print();
//
//        // 0, 1
//        VerticalLayout l1 = (VerticalLayout)mainLayout.setRawLayout(0, 1, VerticalLayout.buildRaw(), pos);
//        l1.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
//        l1.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.RED_TEXT_COLOR));
//        l1.print();
//
//        // 1, 0
//        HorizontalLayout l2 = (HorizontalLayout)mainLayout.setRawLayout(1, 0, HorizontalLayout.buildRaw(), pos);
//        l2.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
//        l2.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.GOLD_COLOR));
//        l2.print();
//
//        // 1, 1
//        HorizontalLayout l3 = (HorizontalLayout)mainLayout.setRawLayout(1, 1, HorizontalLayout.buildRaw(), pos);
//        l3.addChild(new SimpleLabel("L0 c0", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
//        l3.addChild(new SimpleLabel("L0 c1", FontHelper.tipBodyFont, Settings.GREEN_TEXT_COLOR));
//        l3.print();
//
//        System.out.println("------");
//        mainLayout.print();
//
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
