package ui.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.AnchorPosition;
import ui.layouts.HorizontalLayoutPolicy;
import ui.layouts.VerticalLayout3;
import ui.widgets.DebugWidget;

public class Screen2 extends AbstractScreen2<Screen2> {

//    private ArrayList<DebugWidget> widgets = new ArrayList<>();
    private VerticalLayout3 layout;

    public Screen2(float width, float height) {
        super(width, height);

        setMargins(40);

        layout = new VerticalLayout3()
                .anchoredAt(this)
                .withVerticalSpacing(30.0f)
                .withFixedHeight(40.0f);


        for (AnchorPosition anchor : AnchorPosition.values()) {
            layout.addChild(new DebugWidget(20, 20), anchor);
        }

        layout.computeLayout();
    }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);

//        for (DebugWidget dw : widgets)
//            dw.render(sb);
    }
}
