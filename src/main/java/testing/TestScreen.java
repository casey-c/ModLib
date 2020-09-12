package testing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import config.Config;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.widgets.buttons.TextButton;
import ui.widgets.labels.SimpleLabel;
import ui.widgets.lines.HorizontalLine;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.SoundHelper;
import utils.TextureManager;

import java.util.ArrayList;

public class TestScreen extends DynamicScreen<VerticalLayout> {

    private ArrayList<Layout> tests = new ArrayList<>();


    public TestScreen(int width, int height) {
        super(width, height);

        setContentPadding(64.0f, 64.0f);

        mainLayout = VerticalLayout
                .build(getContentWidth(), getContentHeight())
                .anchoredAt(getContentCenterX(), getContentTop(), AnchorPosition.TOP_CENTER)
                .withSpacing(30.0f)
                //.withFixedChildWidth(300.0f).withFixedChildHeight(40.0f) // buggy
        ;

        mainLayout.addChild(new TextButton("Caw Caw").withOnClick(button -> {
            SoundHelper.cawCaw();
        }));

    }

    /*
    private void gridTest4() {
        mainLayout = GridLayout
                .build(getContentWidth(), getContentHeight())
                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
                .withVerticalPadding(10)
                .withHorizontalPadding(20)
                .with_absolute_rows(-1, 10.0f, 60.0f)
                .with_absolute_cols(-1, 80.0f);

        int s = 4;
        GridLayout bottomGrid = mainLayout.setWidget(0, 0, new GridLayout().withVerticalPadding(5).withHorizontalPadding(5));
        bottomGrid.with_balanced_rows(s).with_balanced_cols(s);
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < s; ++j) {
                bottomGrid.setWidget(i, j, new SimpleLabel(i + ", " + j));
            }
        }

        mainLayout.setWidget(1, 0, new HorizontalLine());
        mainLayout.setWidget(2, 0, new SimpleLabel("Grid Test 4", FontHelper.bannerFont, Settings.GOLD_COLOR), AnchorPosition.CENTER_LEFT);

        VerticalLayout rightLayout = mainLayout.setWidget(2, 1, new VerticalLayout().withSpacing(20), AnchorPosition.TOP_RIGHT);
        rightLayout.addChild(new SimpleLabel("Vert 1"));
        rightLayout.addChild(new SimpleLabel("Vert 2"));
        rightLayout.addChild(new SimpleLabel("Vert 3"));
        rightLayout.addChild(new SimpleLabel("Vert 4"));
        rightLayout.addChild(new SimpleLabel("Vert 5"));
        rightLayout.addChild(new SimpleLabel("Vert 6"));
    }
     */

/*
    private void verticalLayoutTests() {
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
 */

//    private AnchorPosition getTestPos(int i, int j) {
//        if (i == 0 && j == 0) return AnchorPosition.TOP_LEFT;
//        else if (i == 0 && j == 1) return AnchorPosition.TOP_CENTER;
//        else if (i == 0 && j == 2) return AnchorPosition.TOP_RIGHT;
//
//        else if (i == 1 && j == 0) return AnchorPosition.CENTER_LEFT;
//        else if (i == 1 && j == 1) return AnchorPosition.CENTER;
//        else if (i == 1 && j == 2) return AnchorPosition.CENTER_RIGHT;
//
//        else if (i == 2 && j == 0) return AnchorPosition.BOTTOM_LEFT;
//        else if (i == 2 && j == 1) return AnchorPosition.BOTTOM_CENTER;
//        else return AnchorPosition.BOTTOM_RIGHT;
//    }

//    private void newGridTest() {
//        int numRows = 3;
//        int numCols = 3;
//
//        mainLayout = GridLayout2
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT)
//                .withInternalPadding(10.0f, 10.0f)
//                .with_balanced_rows(numRows)
//                .with_balanced_cols(numCols);
//
//        for (int i = 0; i < numRows; ++i) {
//            for (int j = 0; j < numCols; ++j) {
//                mainLayout.setWidget(i, j, new SimpleLabel(i + ", " + j), getTestPos(i, j));
//            }
//        }
//
//        //mainLayout.setWidget(0, 2, new VerticalLine());
//        mainLayout.setRawLayout(0, 2, VerticalLayout.build(0, 0).withSpacing(20.0f).addChild(new SimpleLabel("Vert 1")).addChild(new SimpleLabel("Vert 2")), AnchorPosition.TOP_LEFT);
//
//    }

//    private void addHorizontalLayoutTest(float x, float y, AnchorPosition pos) {
//        // Shorthand is cause the horizontal are too wide lol
//        String shorthand = "??";
//        if (pos == AnchorPosition.TOP_LEFT) shorthand = "TL";
//        else if (pos == AnchorPosition.TOP_CENTER) shorthand = "TC";
//        else if (pos == AnchorPosition.TOP_RIGHT) shorthand = "TR";
//
//        else if (pos == AnchorPosition.CENTER_LEFT) shorthand = "CL";
//        else if (pos == AnchorPosition.CENTER) shorthand = "CC";
//        else if (pos == AnchorPosition.CENTER_RIGHT) shorthand = "CR";
//
//        else if (pos == AnchorPosition.BOTTOM_LEFT) shorthand = "BL";
//        else if (pos == AnchorPosition.BOTTOM_CENTER) shorthand = "BC";
//        else if (pos == AnchorPosition.BOTTOM_RIGHT) shorthand = "BR";
//
//        tests.add(HorizontalLayout
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(x, y, pos)
//                .withSpacing(40.0f)
//                .addChild(new SimpleLabel(shorthand + " 1"))
//                .addChild(new SimpleLabel(shorthand + " 2"))
//        );
//    }
//
//    private void addVerticalLayoutTest(float x, float y, AnchorPosition pos) {
//        tests.add(VerticalLayout
//                .build(getContentWidth(), getContentHeight())
//                .anchoredAt(x, y, pos)
//                .withSpacing(40.0f)
//                .addChild(new SimpleLabel(pos.name() + " 1"))
//                .addChild(new SimpleLabel(pos.name() + " 2"))
//        );
//    }

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

    @Override
    protected void renderScreenBackground(SpriteBatch sb) {
        super.renderScreenBackground(sb);

        if (!visible)
            return;

        if (Config.MOD_LIB_DEBUG_MODE)
            RenderingHelper.renderBoxFilled(sb, getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight(), ColorHelper.ORANGE_COLOR);
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
