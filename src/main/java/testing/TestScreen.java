package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.screens.LargeScreen;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;
import ui.widgets.lines.HorizontalLine;
import ui.widgets.lines.VerticalLine;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureManager;

public class TestScreen extends DynamicScreen<HorizontalLayout> {
    public TestScreen(int width, int height) {
        super(width, height);

        //setContentPadding(100.0f, 60.0f);
        setContentPadding(100.0f, 100.0f, 60.0f, 100.0f);

        mainLayout = HorizontalLayout
                .build(getContentWidth(), getContentHeight())
                .withSpacing(40.0f)
                .anchoredAt(getContentCenterX(), getContentCenterY(), AnchorPosition.CENTER);

        mainLayout.addChild(new SimpleLabel("Test 1"));
        mainLayout.addChild(new SimpleLabel("Test 2"));
        mainLayout.addChild(new SimpleLabel("Test 3"));

        //----
//        mainLayout = GridLayout
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .with_absolute_rows(-1, 20.0f, 40.0f)
//                .with_balanced_cols(1);
////                .with_absolute_rows(-1, 80.0f)
////                .with_absolute_cols(-1, 40.0f);
//
//        mainLayout.setWidget(2, 0, new SimpleLabel("Hello", FontHelper.bannerFont, Settings.GOLD_COLOR), AnchorPosition.BOTTOM_LEFT);
//        mainLayout.setWidget(1, 0, new HorizontalLine(), AnchorPosition.BOTTOM_LEFT);
//
//        GridLayout bottomGrid = mainLayout.setRawLayout(0, 0, GridLayout.buildRaw(), AnchorPosition.BOTTOM_LEFT)
//                .with_balanced_cols(4)
//                .with_balanced_rows(4);
//
//        for (int i = 0; i < 4; ++i) {
//            for (int j = 0; j < 4; ++j) {
//                bottomGrid.setWidget(i, j, new SimpleLabel("Caw"), AnchorPosition.BOTTOM_LEFT);
//            }
//        }

        //----

//        HorizontalLayout upper = mainLayout.setRawLayout(1,0, HorizontalLayout.buildRaw(), AnchorPosition.BOTTOM_LEFT);
//        upper.addChild(new SimpleLabel("TEST BANNER", FontHelper.bannerFont, Settings.GOLD_COLOR));
//        upper.addChild(SimplePadding.horizontal(200.0f));
//        upper.addChild(new SimpleLabel("Hello, world"));
//
//        VerticalLayout bottom = mainLayout.setRawLayout(0, 0, VerticalLayout.buildRaw(), AnchorPosition.TOP_LEFT).withSpacing(20.0f);
//        bottom.addChild(SimplePadding.vertical(50.0f));
//        for (int i = 0; i < 10; ++i)
//            bottom.addChild(new SimpleLabel("Test " + i));

        //mainLayout.setWidget(0, 1, new SimpleLabel("TEST"), AnchorPosition.BOTTOM_LEFT);
    }

    @Override
    public void print() {
        super.print();
        mainLayout.print();
    }

    @Override
    public Color getTrimColor() {
        return ColorHelper.rainbowColor();
    }

    // Clipping demonstration
    private static final Texture ERROR_TEX = TextureManager.getTexture("oh", "yeahhhhhhhhhhhhhhh");
    @Override
    protected void renderScreenForeground(SpriteBatch sb) {
        super.renderScreenForeground(sb);

        if (!visible)
            return;

        sb.setColor(Color.WHITE);
        RenderingHelper.renderClipped(sb, ERROR_TEX, InputHelper.mX - 32.0f, InputHelper.mY - 32.0f, false, false,
                getScreenLeft(), getScreenRight(), getScreenBottom(), getScreenTop());
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
