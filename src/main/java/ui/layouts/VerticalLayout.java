package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout {
    private float vertSpacing;
    private float lastVertPos;

    private float fixedChildHeight;
    private boolean letChildrenDetermineOwnHeight;

    private ArrayList<ScreenWidget> children;

    public VerticalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float vertSpacing, float fixedChildHeight) {
        children = new ArrayList<>();

        setPrefWidthHeight(prefWidth, prefHeight);
        setBottomLeft(bottomLeftX, bottomLeftY);

        this.lastVertPos = getTopLeftY();

        this.vertSpacing = vertSpacing;
        this.fixedChildHeight = fixedChildHeight;
    }

    public VerticalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float vertSpacing) {
        this(bottomLeftX, bottomLeftY, prefWidth, prefHeight, vertSpacing, 0.0f);
        this.letChildrenDetermineOwnHeight = true;
    }

    // Debug
    public void print() {
        System.out.println("VERTICAL LAYOUT: (" + getBottomLeftX() + ", " + getBottomLeftY() + ") --> (" + getTopRightX() + ", " + getTopRightY() + ")");
        System.out.println("\t last vert position: " + lastVertPos);
    }

    // Move the child to the proper position determined by this layout
    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        child.setTopLeft(getBottomLeftX(), cy);

        if (letChildrenDetermineOwnHeight)
            lastVertPos = cy - child.getPrefHeight() - vertSpacing;
        else
            lastVertPos = lastVertPos - fixedChildHeight - vertSpacing;
    }

    public void addChild(ScreenWidget child) {
        children.add(child);
        moveChildIntoPosition(child, lastVertPos);
    }

    // TODO: Should be called if the layout ever moves at some point
    public void recomputeAllChildPositions() {
        lastVertPos = getTopLeftY();

        for (ScreenWidget child : children)
            moveChildIntoPosition(child, lastVertPos);
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }

}
