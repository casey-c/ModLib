package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class HorizontalLayout extends Layout<HorizontalLayout> {
    private float horizSpacing;
    private float lastHorizPos;

    private float fixedChildWidth;
    private boolean letChildrenDetermineOwnWidth = true;

    private ArrayList<ScreenWidget> children;

    private HorizontalLayout() { this.children = new ArrayList<>(); }
    public static HorizontalLayout buildRaw() { return new HorizontalLayout(); }

    public static HorizontalLayout build(float prefWidth, float prefHeight) {
        return new HorizontalLayout().withDimensions(prefWidth, prefHeight);
    }


    @Override
    public HorizontalLayout anchoredAt(float x, float y, AnchorPosition pos) {
        super.anchoredAt(x, y, pos);
//        this.anchorPosition = pos;
//
//        // Move this layout to the proper spot
//        if (pos == AnchorPosition.BOTTOM_LEFT)
//            this.setBottomLeft(x, y);
//        else if (pos == AnchorPosition.TOP_LEFT)
//            this.setTopLeft(x, y);
//        else if (pos == AnchorPosition.TOP_RIGHT)
//            this.setTopRight(x, y);
//        else if (pos == AnchorPosition.BOTTOM_RIGHT)
//            this.setBottomRight(x, y);
//
        // Setup the horizontal position counter
        if (pos == AnchorPosition.TOP_LEFT || pos == AnchorPosition.BOTTOM_LEFT)
            this.lastHorizPos = getLeft();
        else
            this.lastHorizPos = getRight();

        return this;
    }

    public HorizontalLayout withSpacing(float horizSpacing) {
        this.horizSpacing = horizSpacing;
        return this;
    }

    public HorizontalLayout withFixedWidth(float width) {
        this.fixedChildWidth = width;
        this.letChildrenDetermineOwnWidth = false;
        return this;
    }

    // Debug
    public void print() {
        System.out.println("HORIZONTAL LAYOUT: (" + getLeft() + ", " + getBottom() + ") --> (" + getRight() + ", " + getTop() + ")");
        System.out.println("\t last horiz position: " + lastHorizPos);
    }

    private void moveChildIntoPosition(ScreenWidget child, float cx) {
        // Update the last position counter
        float widthOffset = (letChildrenDetermineOwnWidth) ? child.getPrefWidth() : fixedChildWidth;

        switch (anchorPosition) {
            case BOTTOM_LEFT:
                child.setBottomLeft(cx, getBottom());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case TOP_LEFT:
                child.setTopLeft(cx, getTop());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case BOTTOM_RIGHT:
                child.setBottomRight(cx, getBottom());
                lastHorizPos = lastHorizPos - widthOffset - horizSpacing;
                break;
            case TOP_RIGHT:
                child.setTopRight(cx, getTop());
                lastHorizPos = lastHorizPos - widthOffset - horizSpacing;
                break;
        }
    }

    public void addChild(ScreenWidget child) {
        children.add(child);
        moveChildIntoPosition(child, lastHorizPos);
    }

    // TODO: needed if layout position ever changes (maybe have a recursive update to all widgets?)
//    public void recomputeAllChildPositions() {
//        lastHorizPos = 0;
//        for (ScreenWidget child : children)
//            moveChildIntoPosition(child, lastHorizPos);
//    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }
}
