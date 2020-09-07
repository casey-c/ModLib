package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout {
    private float vertSpacing;
    private float lastVertPos;

    private float fixedChildHeight;
    private boolean letChildrenDetermineOwnHeight;

    public VerticalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float vertSpacing, float fixedChildHeight) {
        children = new ArrayList<>();

        setPrefWidthHeight(prefWidth, prefHeight);
        setBottomLeft(bottomLeftX, bottomLeftY);

        this.lastVertPos = getTopLeftY();

        this.vertSpacing = vertSpacing;
        this.fixedChildHeight = fixedChildHeight;

        print();
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

    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        // Move the child to the proper position determined by this layout
        child.setTopLeft(getBottomLeftX(), cy);
//        child.setBottomLeftX(x);
//        child.setTopLeftY(cy);

//        child.setX(x);
//        child.setY(cy);

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
        //lastVertPos = 0;
        lastVertPos = getTopLeftY();

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
