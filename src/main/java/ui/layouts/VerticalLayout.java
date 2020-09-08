package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.ArrayList;

public class VerticalLayout extends Layout<VerticalLayout> {
    private float vertSpacing;
    private float lastVertPos;

    private float fixedChildHeight;
    private boolean letChildrenDetermineOwnHeight = true;

    private ArrayList<ScreenWidget> children;

//    private VerticalLayout(float pw, float ph) {
//        this.setPrefWidthHeight(pw, ph);
//        this.children = new ArrayList<>();
//    }

    private VerticalLayout() {
        this.children = new ArrayList<>();
    }

//    public static VerticalLayout buildRaw() {
//        VerticalLayout layout = new VerticalLayout();
//        return layout;
//    }

    public static VerticalLayout build(float prefWidth, float prefHeight) {
        return new VerticalLayout().withDimensions(prefWidth, prefHeight);
    }

    public static VerticalLayout buildRaw() {
        return new VerticalLayout();
    }

//    public VerticalLayout withDimensions(float prefWidth, float prefHeight) {
//        this.setPrefWidthHeight(prefWidth, prefHeight);
//        return this;
//    }

    @Override
    public VerticalLayout anchoredAt(float x, float y, AnchorPosition pos) {
        super.anchoredAt(x, y, pos);

        if (pos == AnchorPosition.TOP_LEFT || pos == AnchorPosition.TOP_RIGHT)
            this.lastVertPos = getTop();
        else
            this.lastVertPos = getBottom();

        return this;
    }


//    public VerticalLayout anchoredAt(float x, float y, AnchorPosition pos) {
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
//        if (pos == AnchorPosition.TOP_LEFT || pos == AnchorPosition.TOP_RIGHT)
//            this.lastVertPos = getTop();
//        else
//            this.lastVertPos = getBottom();
//
//        return this;
//    }

    public VerticalLayout withSpacing(float vertSpacing) {
        this.vertSpacing = vertSpacing;
        return this;
    }

    public VerticalLayout withFixedHeight(float height) {
        this.fixedChildHeight = height;
        this.letChildrenDetermineOwnHeight = false;
        return this;
    }


    //    public VerticalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float vertSpacing, float fixedChildHeight) {
//        children = new ArrayList<>();
//
//        setPrefWidthHeight(prefWidth, prefHeight);
//        setBottomLeft(bottomLeftX, bottomLeftY);
//
//        this.lastVertPos = getTopLeftY();
//
//        this.vertSpacing = vertSpacing;
//        this.fixedChildHeight = fixedChildHeight;
//    }
//
//    public VerticalLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight, float vertSpacing) {
//        this(bottomLeftX, bottomLeftY, prefWidth, prefHeight, vertSpacing, 0.0f);
//        this.letChildrenDetermineOwnHeight = true;
//    }

    // Debug
    public void print() {
        System.out.println("VERTICAL LAYOUT: (" + getLeft() + ", " + getBottom() + ") --> (" + getRight() + ", " + getTop() + ")");
        System.out.println("\t last vert position: " + lastVertPos);
    }

    // Move the child to the proper position determined by this layout
    private void moveChildIntoPosition(ScreenWidget child, float cy) {
        // Update the last position counter
        float heightOffset = (letChildrenDetermineOwnHeight) ? child.getPrefHeight() : fixedChildHeight;

        switch (anchorPosition) {
            case BOTTOM_LEFT:
                child.setBottomLeft(getLeft(), cy);
                lastVertPos = lastVertPos + heightOffset + vertSpacing;
                break;
            case TOP_LEFT:
                child.setTopLeft(getLeft(), cy);
                lastVertPos = lastVertPos - heightOffset - vertSpacing;
                break;
            case BOTTOM_RIGHT:
                child.setBottomRight(getRight(), cy);
                lastVertPos = lastVertPos + heightOffset + vertSpacing;
                break;
            case TOP_RIGHT:
                child.setTopRight(getRight(), cy);
                lastVertPos = lastVertPos - heightOffset - vertSpacing;
                break;
        }
//        child.setTopLeft(getBottomLeftX(), cy);
//
//        if (letChildrenDetermineOwnHeight)
//            lastVertPos = cy - child.getPrefHeight() - vertSpacing;
//        else
//            lastVertPos = lastVertPos - fixedChildHeight - vertSpacing;
    }

    public void addChild(ScreenWidget child) {
        children.add(child);
        moveChildIntoPosition(child, lastVertPos);
    }

    // TODO:
//    public void recomputeAllChildPositions() {
//        lastVertPos = getTopLeftY();
//
//        for (ScreenWidget child : children)
//            moveChildIntoPosition(child, lastVertPos);
//    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children)
            c.render(sb);
    }
}
