package ui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.*;
import ui.widgets.DebugWidget;
import utils.ColorHelper;

public class Screen2 extends DynamicScreen2<Screen2> {
    private GridLayout3 layout;

    public Screen2(int width, int height) {
        super(width, height);

        setMargins(80);

        layout = new GridLayout3()
                .anchoredAt(this)
                .withSpacing(30.0f)
                .withAbsoluteRows(120.0f, -1)
                .withRelativeCols(1, 2);

        layout.setWidget(0,0, 0, 1, new DebugWidget(100, 100));
        layout.setWidget(1, 0, new VerticalLayout3())
                .withVerticalSpacing(30)
                .withChild(new DebugWidget(100, 100))
                .withChild(new DebugWidget(100, 100))
                .computeLayout();

        layout.setWidget(1, 1, new DebugWidget(100, 100)).withContentAnchorPosition(AnchorPosition.BOTTOM_RIGHT);
    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }
}
