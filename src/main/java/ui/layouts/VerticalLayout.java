package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout {
    private float vertSpacing;
    private float lastVertPos;

    private float fixedChildHeight;
    private boolean letChildrenDetermineOwnHeight;

    public VerticalLayout(float topLeftX, float topLeftY, float prefWidth, float prefHeight, float vertSpacing, float fixedChildHeight) {
        children = new ArrayList<>();

        System.out.println("OJB: making a vertical layout start top left at " + topLeftX + ", " + topLeftY);
        System.out.println("OJB: vertical layout w,h: " + prefWidth + ", " + prefHeight);

        this.x = topLeftX;
        this.y = topLeftY;
        this.lastVertPos = y;

        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;

        this.vertSpacing = vertSpacing;
        this.fixedChildHeight = fixedChildHeight;
    }

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
        this.letChildrenDetermineOwnHeight = true;
    }

    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        // Move the child to the proper position determined by this layout
        child.setX(x);
        child.setY(cy);

        if (letChildrenDetermineOwnHeight)
            lastVertPos = cy - child.getPrefHeight() - vertSpacing;
        else
            lastVertPos = lastVertPos - fixedChildHeight - vertSpacing;
    }

    public void addChild(ScreenWidget child) {
        System.out.println("OJB: vertical layout starting lastVertPos at " + lastVertPos);

        children.add(child);
        moveChildIntoPosition(child, lastVertPos);

        System.out.println("OJB: vertical layout added a child with preferred height " + child.getPrefHeight());
        System.out.println("OJB: vertical layout now has lastVertPos at " + lastVertPos + "\n");
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
