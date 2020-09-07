package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout {
    private float vertSpacing;
    private float lastVertPos;

    public VerticalLayout(float topLeftX, float topLeftY, float prefWidth, float prefHeight, float vertSpacing) {
        children = new ArrayList<>();

        System.out.println("OJB: making a vertical layout start top left at " + topLeftX + ", " + topLeftY);
        System.out.println("OJB: vertical layout w,h: " + prefWidth + ", " + prefHeight);

        this.x = topLeftX;
        this.y = topLeftY;
        this.lastVertPos = y;

        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;

        this.vertSpacing = vertSpacing;
    }

    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        // Move the child to the proper position determined by this layout
        child.setX(x);
        child.setY(cy);

        lastVertPos = cy - child.getPrefHeight() - vertSpacing;
    }

    public void addChild(ScreenWidget child) {
        System.out.println("OJB: vertical layout starting lastVertPos at " + lastVertPos);

        children.add(child);
        moveChildIntoPosition(child, lastVertPos);

        System.out.println("OJB: vertical layout added a child with preferred height " + child.getPrefHeight());
        System.out.println("OJB: vertical layout now has lastVertPos at " + lastVertPos);
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
