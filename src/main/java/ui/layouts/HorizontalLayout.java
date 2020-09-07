package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class HorizontalLayout extends Layout {
    private float horizSpacing;
    private float lastHorizPos;

    private float fixedChildWidth;
    private boolean letChildrenDetermineOwnWidth;

    public HorizontalLayout(float topLeftX, float topLeftY, float prefWidth, float prefHeight, float horizSpacing, float fixedChildWidth) {
        children = new ArrayList<>();

        this.x = topLeftX;
        this.y = topLeftY;
        this.lastHorizPos = x;

        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;

        this.horizSpacing = horizSpacing;
        this.fixedChildWidth = fixedChildWidth;
    }

    public HorizontalLayout(float topLeftX, float topLeftY, float prefWidth, float prefHeight, float horizSpacing) {
        children = new ArrayList<>();

        this.x = topLeftX;
        this.y = topLeftY;
        this.lastHorizPos = x;

        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;

        this.horizSpacing = horizSpacing;
        this.letChildrenDetermineOwnWidth = true;
    }

    private void moveChildIntoPosition(ScreenWidget child, float cx) {
        // Move the child to the proper position determined by this layout
        child.setX(cx);
        child.setY(y);

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
