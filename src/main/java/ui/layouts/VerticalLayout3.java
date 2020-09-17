package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.GrowthPolicy;
import ui.widgets.Widget;
import utils.ColorHelper;

public class VerticalLayout3 extends OneDimensionalLayout3<VerticalLayout3> {

    private HorizontalLayoutPolicy horizontalLayoutPolicy = HorizontalLayoutPolicy.CHILD_PREFERRED_WIDTH;

    private boolean dynamicChildHeight = true;
    private float fixedHeight;

    public VerticalLayout3 withHorizontalLayoutPolicy(HorizontalLayoutPolicy policy) {
        this.horizontalLayoutPolicy = policy;
        return this;
    }

    public VerticalLayout3 withFixedHeight(float height) {
        this.dynamicChildHeight = false;
        this.fixedHeight = height;
        return this;
    }

    public <T extends Widget> T addChild(T w, AnchorPosition childAnchor) {
        children.add(w);
        w.setContentAnchorPosition(childAnchor);
        return w;
    }

    public <T extends Widget> T addChild(T w) {
        children.add(w);
        w.setContentAnchorPosition(AnchorPosition.TOP_LEFT);
        return w;
    }

    @Override
    public void computeLayout() {
        float x = getContentLeft();
        float y = getContentTop();

        // Obey the global anchor position
        if (globalChildAnchor.isCentralX())
            x = getContentCenterX() - (maxChildPrefWidth() * 0.5f);
        else if (globalChildAnchor.isRight())
            x = getContentRight() - maxChildPrefWidth();

        if (globalChildAnchor.isCentralY())
            y = getContentCenterY() + (sumChildPrefHeight() * 0.5f);
        else if (globalChildAnchor.isBottom())
            y = getContentBottom() + sumChildPrefHeight();

        // Width
        float maxChildWidth = horizontalLayoutPolicy == HorizontalLayoutPolicy.CHILD_EXPAND_WIDTH_TO_MAX ? maxChildPrefWidth() : 0;

        for (Widget child : children) {
            float height = (dynamicChildHeight) ? child.getPrefHeight() : fixedHeight;

            float width = getContentWidth();

            switch (horizontalLayoutPolicy) {
                case CHILD_PREFERRED_WIDTH:
                    child.setGrowthPolicy(GrowthPolicy.PREFERRED_BOTH);
                    break;
                case CHILD_EXPAND_WIDTH_TO_FULL:
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_X);
                    break;
                case CHILD_EXPAND_WIDTH_TO_MAX:
                    width = maxChildWidth;
                    child.setGrowthPolicy(GrowthPolicy.EXPANDING_X);
                    break;
            }

            // TODO: this is bugged. figure out what the proper thing to do is
            //child.setActualFromAnchor(x, y, width, height, AnchorPosition.TOP_LEFT);
            child.setActualFromAnchor(x, y, width, height, globalChildAnchor);

            y = y - height - verticalSpacing;
        }
    }

    @Override public float getPrefWidth() { return 0; }
    @Override public float getPrefHeight() { return 0; }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
//        sb.setColor(ColorHelper.VERY_DIM_RED);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentBottom(), getContentWidth(), getContentHeight());

        for (Widget w : children)
            w.render(sb);
    }
}

