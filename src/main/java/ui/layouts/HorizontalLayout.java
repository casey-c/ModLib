package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class HorizontalLayout extends Layout {
    private float horizSpacing;
    private float lastHorizPos;

    private float fixedChildWidth;
    private boolean letChildrenDetermineOwnWidth;

    public HorizontalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float horizSpacing, float fixedChildWidth) {
        children = new ArrayList<>();

        setPrefWidthHeight(prefWidth, prefHeight);
        setBottomLeft(bottomLeftX, bottomLeftY);

        this.lastHorizPos = getBottomLeftX();

        this.horizSpacing = horizSpacing;
        this.fixedChildWidth = fixedChildWidth;

        print();
    }


    public HorizontalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float horizSpacing) {
        this(bottomLeftX, bottomLeftY, prefWidth, prefHeight, horizSpacing, 0.0f);
        this.letChildrenDetermineOwnWidth = true;
    }

    // Debug
    public void print() {
        System.out.println("HORIZONTAL LAYOUT: (" + getBottomLeftX() + ", " + getBottomLeftY() + ") --> (" + getTopRightX() + ", " + getTopRightY() + ")");
        System.out.println("\t last horiz position: " + lastHorizPos);
    }

    private void moveChildIntoPosition(ScreenWidget child, float cx) {
        child.setBottomLeft(cx, getBottomLeftY());

        System.out.println("Moved child into position");
        child.print();

        if (letChildrenDetermineOwnWidth)
            lastHorizPos = cx + child.getPrefWidth() + horizSpacing;
        else
            lastHorizPos = lastHorizPos + fixedChildWidth + horizSpacing;
    }

    public void addChild(ScreenWidget child) {
        System.out.println("OJB: horiz layout starting lastHorizPos at " + lastHorizPos);

        children.add(child);
        moveChildIntoPosition(child, lastHorizPos);

        System.out.println("OJB: horiz layout added a child with preferred width " + child.getPrefWidth());
        System.out.println("OJB: horiz layout now has lastHorizPos at " + lastHorizPos + "\n");
    }

    // TODO: Should be called if the layout ever moves at some point
    public void recomputeAllChildPositions() {
        lastHorizPos = 0;
        for (ScreenWidget child : children) {
            moveChildIntoPosition(child, lastHorizPos);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }
}
