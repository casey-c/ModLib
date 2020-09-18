package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.*;
import ui.screens.DynamicScreen2;
import ui.widgets.DebugWidget;
import ui.widgets.SimplePadding;
import ui.widgets.labels.SimpleLabel;
import ui.widgets.lines.HorizontalLine;
import ui.widgets.lines.VerticalLine;
import utils.ColorHelper;

public class TestScreen extends DynamicScreen2<TestScreen> {
    private GridLayout layout;

    public TestScreen(int width, int height) {
        super(width, height);

        setMargins(80);

        layout = new GridLayout()
                .anchoredAt(this)
                .withSpacing(30.0f)
                .withAbsoluteRows(60.0f, -1)
                .withRelativeCols(1, 2);

        layout.setWidget(0,0, 0, 1, new SimpleLabel("Spanning Text", FontHelper.bannerFont, Settings.GOLD_COLOR))
                .withContentAnchorPosition(AnchorPosition.CENTER)
                .print();

        layout.setWidget(1, 0, new VerticalLayout())
                .withVerticalSpacing(30)
                .withChild(new SimpleLabel("Row 1"))
                .withChild(new HorizontalLine(true))
                .withChild(SimplePadding.vertical(40.0f))
                .withChild(new HorizontalLine(true))
                .withChild(new SimpleLabel("Row 2"))
                .computeLayout();


        layout.setWidget(1, 1, new DebugWidget(100, 100));
        //layout.setWidget(1, 1, new HorizontalLine2());
        //layout.setWidget(1, 1, new VerticalLine());
    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }
}
