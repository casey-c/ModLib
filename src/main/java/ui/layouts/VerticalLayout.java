package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout {
    private float vertSpacing;
    private float lastVertPos;

    public VerticalLayout(float topLeftX, float topLeftY, float prefWidth, float prefHeight, float vertSpacing) {
        children = new ArrayList<ScreenWidget>();

        this.x = topLeftX;
        this.y = topLeftY;

        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;

        this.vertSpacing = vertSpacing;
    }

    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        // Move the child to the proper position determined by this layout
        child.setX(x);
        child.setY(cy);

        lastVertPos = cy + child.getPrefHeight() + vertSpacing;
    }

    public void addChild(ScreenWidget child) {
        children.add(child);
        moveChildIntoPosition(child, lastVertPos);
    }

    // TODO: Should be called if the layout ever moves at some point
    public void recomputeAllChildPositions() {
        lastVertPos = 0;
        for (ScreenWidget child : children) {
            moveChildIntoPosition(child, lastVertPos);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }

}
