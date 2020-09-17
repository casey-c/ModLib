package ui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.GrowthPolicy;
import ui.layouts.AnchorPosition;
import ui.layouts.HorizontalLayoutPolicy;
import ui.layouts.VerticalLayout3;
import ui.widgets.DebugWidget;
import utils.ColorHelper;

public class Screen2 extends DynamicScreen2<Screen2> {
    private VerticalLayout3 layout;

    public Screen2(int width, int height) {
        super(width, height);

        setMargins(80);

        layout = new VerticalLayout3()
                .anchoredAt(this)
                .withVerticalSpacing(30.0f)
                .withGlobalChildAnchor(AnchorPosition.CENTER)
                .withHorizontalLayoutPolicy(HorizontalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_MAX)
                .withFixedRowHeight(60);

        int offset = 0;
        for (int i = 0; i < 4; ++i) {
            layout.addChild(new DebugWidget(20 + offset, 40));
            offset += 20;
        }

        layout.computeLayout();
    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }
}
