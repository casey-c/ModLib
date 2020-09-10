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

    private AnchorPosition childAnchor;

    private HorizontalLayout() { this.children = new ArrayList<>(); }
    public static HorizontalLayout buildRaw() { return new HorizontalLayout(); }

    public static HorizontalLayout build(float prefWidth, float prefHeight) {
        return new HorizontalLayout()
                .withDimensions(prefWidth, prefHeight);
    }

    public HorizontalLayout withChildAnchor(AnchorPosition childAnchor) {
        this.childAnchor = childAnchor;

        // Default horiz pos
        if (AnchorPosition.isLeft(childAnchor))
            this.lastHorizPos = getLeft();
        else if (AnchorPosition.isRight(childAnchor))
            this.lastHorizPos = getRight();
        else if (AnchorPosition.isCentralX(childAnchor))
            this.lastHorizPos = getCenterX();

        return this;
    }

    @Override
    public HorizontalLayout anchoredAt(float x, float y, AnchorPosition pos) {
        super.anchoredAt(x, y, pos);

        // By default, the child anchor inherits the parent anchor
        this.childAnchor = pos;

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

//        else if (pos == AnchorPosition.TOP_CENTER || pos == AnchorPosition.BOTTOM_CENTER || pos == AnchorPosition.CENTER)
//            this.lastHorizPos = getCenterX();

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

        // *********************************************************************************************
        // TODO: redo this whole thing with the new child anchor / parent anchor differentiation change
        //   what's below is NOT working correctly
        // *********************************************************************************************
        switch (childAnchor) {
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

            // TODO: verify all these additions
            // TODO: in the centering case, it's probably good to recompute all existing nodes and shift them accordingly
            //   something like: figure out the sum of the prefWidths + spacings and then recenter it again?
            case CENTER_LEFT:
                child.setCenterLeft(cx, getCenterY());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case CENTER_RIGHT:
                child.setCenterRight(cx, getCenterY());
                lastHorizPos = lastHorizPos - widthOffset - horizSpacing;
                break;
            case TOP_CENTER:
                child.setTopCenter(cx, getTop());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case BOTTOM_CENTER:
                child.setTopCenter(cx, getBottom());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case CENTER:
                child.setCenter(cx, getCenterY());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;

        }
    }

    private float getTotalChildWidths() {
        float sum = 0.0f;

        for (ScreenWidget child : children) {
            sum += (letChildrenDetermineOwnWidth) ? child.getPrefWidth() : fixedChildWidth;
            sum += horizSpacing;
        }

        // Undo the last one
        if (children.size() > 0)
            sum -= horizSpacing;

        return sum;
    }

    public void addChild(ScreenWidget child) {
        children.add(child);

        if (AnchorPosition.isCentralX(childAnchor)) {
            recomputeAllChildPositions();
        }
        else {
            moveChildIntoPosition(child, lastHorizPos);
        }
    }

    // TODO: needed if layout position ever changes (maybe have a recursive update to all widgets?)
    public void recomputeAllChildPositions() {
        if (AnchorPosition.isCentralX(childAnchor)) {
            float totalChildWidth = getTotalChildWidths();
            float layoutCenterX = getCenterX();

            lastHorizPos = layoutCenterX - (totalChildWidth / 2.0f);
        }
        else if (AnchorPosition.isLeft(childAnchor))
            lastHorizPos = getLeft();
        else if (AnchorPosition.isRight(childAnchor))
            lastHorizPos = getRight();

        for (ScreenWidget child : children)
            moveChildIntoPosition(child, lastHorizPos);

//        lastHorizPos = 0;
//        for (ScreenWidget child : children)
//            moveChildIntoPosition(child, lastHorizPos);
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }
}
