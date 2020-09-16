package ui.screens;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.layouts.AnchorPosition;
import ui.layouts.VerticalLayout3;
import ui.widgets.ContainerWidget;
import ui.widgets.DebugWidget;
import utils.ColorHelper;

public class Screen2 extends ContainerWidget<Screen2> implements RenderSubscriber, PostUpdateSubscriber {
    private VerticalLayout3 layout = new VerticalLayout3();
    private boolean visible;

    public Screen2(float width, float height) {
        System.out.println("OJB: making a screen 2");

        setRealDimensions(width, height);
        setPosition( (Settings.WIDTH - width ) * 0.5f, (Settings.HEIGHT - height ) * 0.5f, AnchorPosition.BOTTOM_LEFT );
        withMargins(10, 10, 10, 10);

        System.out.println("get content width: " + getContentWidth());
        System.out.println("get content height: " + getContentHeight());

        layout.setRealDimensions(getContentWidth(), getContentHeight());
        layout.setPosition(getContentLeft(), getContentBottom(), AnchorPosition.BOTTOM_LEFT);

        layout.withVerticalSpacing(30.0f);

        layout.addChild(new DebugWidget(100, 200));
        layout.addChild(new DebugWidget(200, 100));
        layout.computeLayout();

        System.out.println("screen 2:");
        print();

        System.out.println();

        System.out.println("layout");
        layout.print();

        BaseMod.subscribe(this);
    }

    protected void renderBackground(SpriteBatch sb) {
        sb.setColor(ColorHelper.VERY_DIM_BLUE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());
    }

    protected void renderForeground(SpriteBatch sb) {
        layout.render(sb);
//        layout.renderFixed(sb,
//                x + MARGIN_LEFT,
//                y + MARGIN_BOTTOM,
//                AnchorPosition.CENTER,
//                width - MARGIN_LEFT - MARGIN_RIGHT,
//                height - MARGIN_TOP - MARGIN_BOTTOM );
    }

    @Override public void render(SpriteBatch sb) { receiveRender(sb); }

    @Override
    public void receivePostUpdate() {
        // TODO
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (!visible)
            return;

        renderBackground(sb);
        renderForeground(sb);
    }

    @Override
    public void show() {
        if (visible)
            return;

        visible = true;
    }

    @Override
    public void hide() {
        if (!visible)
            return;

        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }
}
