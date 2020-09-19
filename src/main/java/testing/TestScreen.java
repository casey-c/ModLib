package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.GrowthPolicy;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.widgets.SimplePadding;
import ui.widgets.buttons.ButtonFactory;
import ui.widgets.buttons.CheckboxButton;
import ui.widgets.buttons.TextButton;
import ui.widgets.labels.SimpleLabel;
import ui.widgets.lines.HorizontalLine;
import ui.widgets.lines.VerticalLine;
import utils.ColorHelper;
import utils.SoundHelper;

public class TestScreen extends DynamicScreen<TestScreen> {
    private GridLayout layout;

    public TestScreen(int width, int height) {
        super(width, height);

        setMargins(80, 40, 40, 80);

        layout = new GridLayout()
                .anchoredAt(this)
                .withSpacing(30.0f)
                .withAbsoluteRows(60.0f, 10, -1)
                .withAbsoluteCols(-1, 10, 50);

        layout.setWidget(0,0, 0, 1, new SimpleLabel("Spanning Text", FontHelper.bannerFont, Settings.GOLD_COLOR)).withContentAnchorPosition(AnchorPosition.CENTER_LEFT);
        layout.setWidget(1, 1, 0, 1, new HorizontalLine());

        layout.setWidget(0, 2, 2, 2, new VerticalLayout())
                .withSpacing(10)
                .withChild(ButtonFactory.cancelButton().withOnClick(onClick -> {
                    hide();
                }))
                .withChild(ButtonFactory.resetButton().withOnClick(onClick -> { SoundHelper.cawCaw(); }))
                .computeLayout();

        GridLayout bottomGrid = layout.setWidget(2, 0, new GridLayout())
                .withSpacing(40).withBalancedCols(2).withBalancedRows(2);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                //bottomGrid.setWidget(i, j, new TextButton(i + ", " + j)).withGrowthPolicy(GrowthPolicy.EXPANDING_BOTH);
                bottomGrid.setWidget(i, j, new CheckboxButton());
            }
        }

        layout.setWidget(2, 1, new VerticalLine());

    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }

    @Override
    public void update() {
        layout.update();
    }
}
