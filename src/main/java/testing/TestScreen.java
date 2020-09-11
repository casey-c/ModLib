package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureManager;

import java.util.ArrayList;

public class TestScreen extends DynamicScreen<VerticalLayout> {

    private ArrayList<Layout> tests = new ArrayList<>();

    public TestScreen(int width, int height) {
        super(width, height);

        setContentPadding(100.0f, 60.0f);

        //mainLayout = VerticalLayout .build(getContentWidth(), getContentHeight()) .anchoredAt(getContentCenterX(), getContentCenterY(), AnchorPosition.CENTER) .withSpacing(40.0f);

        addVerticalLayoutTest(getContentLeft(), getContentTop(), AnchorPosition.TOP_LEFT);
        addVerticalLayoutTest(getContentCenterX(), getContentTop(), AnchorPosition.TOP_CENTER);
        addVerticalLayoutTest(getContentRight(), getContentTop(), AnchorPosition.TOP_RIGHT);

        addVerticalLayoutTest(getContentLeft(), getContentCenterY(), AnchorPosition.CENTER_LEFT);
        addVerticalLayoutTest(getContentCenterX(), getContentCenterY(), AnchorPosition.CENTER);
        addVerticalLayoutTest(getContentRight(), getContentCenterY(), AnchorPosition.CENTER_RIGHT);

        addVerticalLayoutTest(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT);
        addVerticalLayoutTest(getContentCenterX(), getContentBottom(), AnchorPosition.BOTTOM_CENTER);
        addVerticalLayoutTest(getContentRight(), getContentBottom(), AnchorPosition.BOTTOM_RIGHT);

    }

    private void addHorizontalLayoutTest(float x, float y, AnchorPosition pos) {
        // Shorthand is cause the horizontal are too wide lol
        String shorthand = "??";
        if (pos == AnchorPosition.TOP_LEFT) shorthand = "TL";
        else if (pos == AnchorPosition.TOP_CENTER) shorthand = "TC";
        else if (pos == AnchorPosition.TOP_RIGHT) shorthand = "TR";

        else if (pos == AnchorPosition.CENTER_LEFT) shorthand = "CL";
        else if (pos == AnchorPosition.CENTER) shorthand = "CC";
        else if (pos == AnchorPosition.CENTER_RIGHT) shorthand = "CR";

        else if (pos == AnchorPosition.BOTTOM_LEFT) shorthand = "BL";
        else if (pos == AnchorPosition.BOTTOM_CENTER) shorthand = "BC";
        else if (pos == AnchorPosition.BOTTOM_RIGHT) shorthand = "BR";

        tests.add(HorizontalLayout
                .build(getContentWidth(), getContentHeight())
                .anchoredAt(x, y, pos)
                .withSpacing(40.0f)
                .addChild(new SimpleLabel(shorthand + " 1"))
                .addChild(new SimpleLabel(shorthand + " 2"))
        );
    }

    private void addVerticalLayoutTest(float x, float y, AnchorPosition pos) {
        tests.add(VerticalLayout
                .build(getContentWidth(), getContentHeight())
                .anchoredAt(x, y, pos)
                .withSpacing(40.0f)
                .addChild(new SimpleLabel(pos.name() + " 1"))
                .addChild(new SimpleLabel(pos.name() + " 2"))
        );
    }

    @Override
    public void print() {
        super.print();

        if (mainLayout != null)
            mainLayout.print();
    }

    @Override
    public Color getTrimColor() {
        return ColorHelper.rainbowColor();
        //return ColorHelper.ORANGE_COLOR;
    }

    // Clipping demonstration
    private static final Texture ERROR_TEX = TextureManager.getTexture("oh", "yeahhhhhhhhhhhhhhh");
    @Override
    protected void renderScreenForeground(SpriteBatch sb) {
        super.renderScreenForeground(sb);

        if (!visible)
            return;

        for (Layout l : tests)
            l.render(sb);

        sb.setColor(Color.WHITE);
        RenderingHelper.renderClipped(sb, ERROR_TEX, InputHelper.mX - 32.0f, InputHelper.mY - 32.0f, false, false,
                getScreenLeft(), getScreenRight(), getScreenBottom(), getScreenTop());

    }

//    private void dynGridTest() {
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
//    }

//    private void balancedGridTest() {
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
//    }

//    private void nestedLayoutTest() {
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
//    }

//    private void alignmentTest() {
//        mainLayout = HorizontalLayout
//                .build(getContentWidth(), getContentHeight())
//                //.anchoredAt(getContentLeft(), getContentTop(), AnchorPosition.TOP_LEFT)
//                //.anchoredAt(getContentCenterX(), getContentTop(), AnchorPosition.TOP_CENTER)
//                //.anchoredAt(getContentRight(), getContentTop(), AnchorPosition.TOP_RIGHT)
//
//                //.anchoredAt(getContentLeft(), getContentCenterY(), AnchorPosition.CENTER_LEFT)
//                .anchoredAt(getContentCenterX(), getContentCenterY(), AnchorPosition.CENTER)
//                //.anchoredAt(getContentRight(), getContentCenterY(), AnchorPosition.CENTER_RIGHT)
//
//                //.anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                //.anchoredAt(getContentCenterX(), getContentBottom(), AnchorPosition.BOTTOM_CENTER)
//                //.anchoredAt(getContentRight(), getContentBottom(), AnchorPosition.BOTTOM_RIGHT)
//
//                .withFixedChildWidth(250.0f)
//                .withFixedChildHeight(150.0f)
//                .withSpacing(40.0f);
//    }
}
