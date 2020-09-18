package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.layouts.*;
import ui.screens.DynamicScreen2;
import ui.widgets.DebugWidget;
import ui.widgets.labels.SimpleLabel2;
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

        layout.setWidget(0,0, 0, 1, new SimpleLabel2("Spanning Text", FontHelper.bannerFont, Settings.GOLD_COLOR))
                .withContentAnchorPosition(AnchorPosition.CENTER)
                .print();

        layout.setWidget(1, 0, new VerticalLayout())
                .withVerticalSpacing(30)
                .withChild(new SimpleLabel2("Row 1"))
                .withChild(new SimpleLabel2("Row 2"))
                .computeLayout();

        layout.setWidget(1, 1, new DebugWidget(100, 100));
    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }
}
