package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class HorizontalLayout extends Layout {
    private float horizSpacing;
    private float lastHorizPos;

    private LayoutAnchorPosition anchorPosition;
    private float fixedChildWidth;
    private boolean letChildrenDetermineOwnWidth = true;

    private ArrayList<ScreenWidget> children;


    private HorizontalLayout(float pw, float ph) {
        setPrefWidthHeight(pw, ph);
        children = new ArrayList<>();
    }

    public static HorizontalLayout build(float prefWidth, float prefHeight) {
        HorizontalLayout layout = new HorizontalLayout(prefWidth, prefHeight);
        return layout;
    }

    public HorizontalLayout anchoredAt(float x, float y, LayoutAnchorPosition pos) {
        this.anchorPosition = pos;
        this.setBottomLeft(x, y);

        if (pos == LayoutAnchorPosition.TOP_LEFT || pos == LayoutAnchorPosition.BOTTOM_LEFT)
            this.lastHorizPos = getBottomLeftX();
        else
            this.lastHorizPos = getBottomRightX();

        return this;
    }

//    public HorizontalLayout anchoredBottomLeft(float x, float y) {
//        this.anchorPosition = LayoutAnchorPosition.BOTTOM_LEFT;
//        this.setBottomLeft(x, y);
//        lastHorizPos = getBottomLeftX();
//        return this;
//    }
//
//    public HorizontalLayout anchoredTopLeft(float x, float y) {
//        this.anchorPosition = LayoutAnchorPosition.TOP_LEFT;
//        this.setTopLeft(x, y);
//        lastHorizPos = getBottomLeftX();
//        return this;
//    }
//
//    public HorizontalLayout anchoredTopRight(float x, float y) {
//        this.anchorPosition = LayoutAnchorPosition.TOP_RIGHT;
//        this.setTopLeft(x, y);
//        lastHorizPos = getBottomRightX();
//        return this;
//    }
//
//    public HorizontalLayout anchoredBottomRight(float x, float y) {
//        this.anchorPosition = LayoutAnchorPosition.BOTTOM_RIGHT;
//        this.setTopLeft(x, y);
//        lastHorizPos = getBottomRightX();
//        return this;
//    }

    public HorizontalLayout withSpacing(float horizSpacing) {
        this.horizSpacing = horizSpacing;
        return this;
    }

    public HorizontalLayout withFixedWidth(float width) {
        this.fixedChildWidth = width;
        this.letChildrenDetermineOwnWidth = false;
        return this;
    }

//    private HorizontalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float horizSpacing, float fixedChildWidth) {
//        children = new ArrayList<>();
//
//        setPrefWidthHeight(prefWidth, prefHeight);
//        setBottomLeft(bottomLeftX, bottomLeftY);
//
//        this.lastHorizPos = getBottomLeftX();
//
//        this.horizSpacing = horizSpacing;
//        this.fixedChildWidth = fixedChildWidth;
//    }
//
//
//    private HorizontalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float horizSpacing) {
//        this(bottomLeftX, bottomLeftY, prefWidth, prefHeight, horizSpacing, 0.0f);
//        this.letChildrenDetermineOwnWidth = true;
//    }

    // Debug
    public void print() {
        System.out.println("HORIZONTAL LAYOUT: (" + getBottomLeftX() + ", " + getBottomLeftY() + ") --> (" + getTopRightX() + ", " + getTopRightY() + ")");
        System.out.println("\t last horiz position: " + lastHorizPos);
    }

    private void moveChildIntoPosition(ScreenWidget child, float cx) {
//        // TODO: Move according to the anchor
//        if (anchorPosition == LayoutAnchorPosition.BOTTOM_LEFT)
//            child.setBottomLeft(cx, getBottomLeftY());
//        else if (anchorPosition == LayoutAnchorPosition.BOTTOM_RIGHT)
//            child.setBottomRight(cx, getBottomLeftY());
//        else if (anchorPosition == LayoutAnchorPosition.TOP_LEFT)
//            child.setTopLeft(cx, getBottomLeftY());
//        else if (anchorPosition == LayoutAnchorPosition.TOP_RIGHT)
//            child.setTopRight(cx, getBottomLeftY());

        // Update the last position counter
        float widthOffset = (letChildrenDetermineOwnWidth) ? child.getPrefWidth() : fixedChildWidth;

        switch (anchorPosition) {
            case BOTTOM_LEFT:
                child.setBottomLeft(cx, getBottomLeftY());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case TOP_LEFT:
                child.setTopLeft(cx, getTopLeftY());
                lastHorizPos = lastHorizPos + widthOffset + horizSpacing;
                break;
            case BOTTOM_RIGHT:
                child.setBottomRight(cx, getBottomRightY());
                lastHorizPos = lastHorizPos - widthOffset - horizSpacing;
                break;
            case TOP_RIGHT:
                child.setTopRight(cx, getTopRightY());
                lastHorizPos = lastHorizPos - widthOffset - horizSpacing;
                break;
        }

//        if (letChildrenDetermineOwnWidth) {
//            lastHorizPos = cx + child.getPrefWidth() + horizSpacing;
//        } else {
//            lastHorizPos = lastHorizPos + fixedChildWidth + horizSpacing;
//        }
    }

    public void addChild(ScreenWidget child) {
        children.add(child);
        moveChildIntoPosition(child, lastHorizPos);
    }

    // TODO: Should be called if the layout ever moves at some point
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
