package ui.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.AnchorPosition;
import ui.widgets.DebugWidget;

import java.util.ArrayList;

public class Screen2 extends AbstractScreen2<Screen2> {

    private ArrayList<DebugWidget> widgets = new ArrayList<>();

    public Screen2(float width, float height) {
        super(width, height);

        setMargins(40);

        for (AnchorPosition anchor : AnchorPosition.values()) {
            widgets.add(new DebugWidget(200, 100)
                    .anchoredAt(this)
                    .withContentAnchorPosition(anchor)
            );
        }
    }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        for (DebugWidget dw : widgets)
            dw.render(sb);
    }
}
