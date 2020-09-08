package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.widgets.ScreenWidget;
import utils.DualIntegerKey;

import java.util.ArrayList;
import java.util.HashMap;

public class GridLayout extends Layout {
    private static enum GRID_TYPE {
        ABSOLUTE,
        RELATIVE,
        BALANCED,
        NONE
    }

    private HashMap<DualIntegerKey, Layout> children;

    private ArrayList<Float> columnWidths;
    private ArrayList<Float> rowHeights;
    private GRID_TYPE colType, rowType;

    private GridLayout(float prefWidth, float prefHeight) {
        setPrefWidthHeight(prefWidth, prefHeight);
        //setBottomLeft(bottomLeftX, bottomLeftY);

        this.children = new HashMap<>();
        columnWidths = new ArrayList<>();
        rowHeights = new ArrayList<>();

        this.colType = GRID_TYPE.NONE;
        this.rowType = GRID_TYPE.NONE;
    }

    public static GridLayout build(float prefWidth, float prefHeight) {
        GridLayout layout = new GridLayout(prefWidth, prefHeight);
        return layout;
    }

    public GridLayout anchoredAt(float x, float y, AnchorPosition pos) {
        this.anchorPosition = pos;

        // Move this layout to the proper spot
        if (pos == AnchorPosition.BOTTOM_LEFT)
            this.setBottomLeft(x, y);
        else if (pos == AnchorPosition.TOP_LEFT)
            this.setTopLeft(x, y);
        else if (pos == AnchorPosition.TOP_RIGHT)
            this.setTopRight(x, y);
        else if (pos == AnchorPosition.BOTTOM_RIGHT)
            this.setBottomRight(x, y);

        return this;
    }

    public GridLayout with_relative_rows(float... ratios) {
        float sum = 0.0f;
        for (float r : ratios) {
            if (r <= 0.0f) return this;
            sum += r;
        }

        // epsilon safety threshold?
        if (sum < 0.001f)
            return this;

        rowHeights.clear();

        for (float r : ratios) {
            rowHeights.add((r / sum) * getPrefHeight());
        }

        rowType = GRID_TYPE.RELATIVE;

        return this;
    }

    public GridLayout with_balanced_rows(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of rows; not altering");
            return this;
        }

        float rowHeight = getPrefHeight() / (float)count;
        System.out.println("OJB: making " + count + " rows of size " + rowHeight);

        rowHeights.clear();

        for (int i = 0; i < count; ++i)
            rowHeights.add(rowHeight);

        rowType = GRID_TYPE.BALANCED;
        return this;
    }

    public GridLayout with_relative_cols(float... ratios) {
        float sum = 0.0f;
        for (float r : ratios) {
            if (r <= 0.0f) return this;
            sum += r;
        }

        // epsilon safety threshold?
        if (sum < 0.001f)
            return this;

        columnWidths.clear();

        for (float r : ratios) {
            columnWidths.add((r / sum) * getPrefWidth());
        }

        colType = GRID_TYPE.RELATIVE;
        return this;
    }

    public GridLayout with_balanced_cols(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of columns; not altering");
            return this;
        }

        float colWidth = getPrefWidth() / (float)count;
        System.out.println("OJB: making " + count + " cols of size " + colWidth);
        columnWidths.clear();

        for (int i = 0; i < count; ++i)
            columnWidths.add(colWidth);

        colType = GRID_TYPE.BALANCED;
        return this;
    }

    // TODO: add in anchors for GRIDs
    private void fixRawLayout(int row, int col, Layout layout, AnchorPosition pos) {
        layout.withDimensions(getLayoutWidth(col), getLayoutHeight(row));
        float x = (pos == AnchorPosition.BOTTOM_LEFT || pos == AnchorPosition.TOP_LEFT) ? getLayoutX(col) : getLayoutX(col) + getLayoutWidth(col);
        float y = (pos == AnchorPosition.BOTTOM_LEFT || pos == AnchorPosition.BOTTOM_RIGHT) ? getLayoutY(row) : getLayoutY(row) + getLayoutHeight(row);
        layout.anchoredAt(x, y, pos);
    }

    public Layout setRawLayout(int row, int col, Layout layout, AnchorPosition pos) {
        if (!inBounds(row, col))
            return layout;

        System.out.println("OJB: original raw layout");
        layout.print();

        fixRawLayout(row, col, layout, pos);

        System.out.println("OJB: fixed raw layout");
        layout.print();

        children.put(new DualIntegerKey(row, col), layout);
        return layout;
    }

//    public static GridLayout build(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight) {
//        GridLayout res = new GridLayout(bottomLeftX, bottomLeftY, prefWidth, prefHeight);
//        return res;
//    }

//    public boolean hasExistingAt(int row, int col) {
//        return children.containsKey( new DualIntegerKey(row, col));
//    }
//
//    public @Nullable Layout getLayoutAt(int row, int col) {
//        if (hasExistingAt(row, col))
//            return children.get(new DualIntegerKey(row, col));
//        else
//            return null;
//    }

    public boolean inBounds(int row, int col) {
        return (row < rowHeights.size()) && (col < columnWidths.size());
    }

    private float getLayoutX(int col) {
        float pos = getLeft();

        int index = 0;
        for (float w : columnWidths) {
            if (index++ >= col)
                break;

            pos += w;
        }

        return pos;
    }

    private float getLayoutY(int row) {
        float pos = getBottom();

        int index = 0;
        for (float h : rowHeights) {
            if (index++ >= row)
                break;

            pos += h;
        }

        return pos;
    }

    private float getLayoutWidth(int col) {
        if (colType == GRID_TYPE.BALANCED || colType == GRID_TYPE.RELATIVE) {
            if (col < columnWidths.size())
                return columnWidths.get(col);
            else
                return getPrefWidth();
        }

        // TODO: absolute etc.
        return getPrefWidth();
//        return getPrefHeight();
//        float sum = 0.0f;
//
//        int index = 0;
//        for (float w : columnWidths) {
//            if (index++ > col)
//                break;
//
//            sum += w;
//        }
//
//        return sum;
    }

    private float getLayoutHeight(int row) {
        if (rowType == GRID_TYPE.BALANCED || rowType == GRID_TYPE.RELATIVE) {
            if (row < rowHeights.size())
                return rowHeights.get(row);
            else
                return getPrefHeight();
        }

        // TODO:
        return getPrefHeight();

        // TODO: this is probably for absolute
//        float sum = 0.0f;
//
//        int index = 0;
//        for (float h : rowHeights) {
//            if (index++ > row)
//                break;
//
//            sum += h;
//        }
//
//        return sum;
    }

    private void setLayoutAt(int row, int col, Layout layout) {
        // TODO: check in bounds
//        if (!inBounds(row, col)) {
//            System.out.println("OJB warning: can't set layout at " + row + ", " + col + ". Check if you built properly");
//            return;
//        }

        // In bounds, so set the layout
        children.put(new DualIntegerKey(row, col), layout);

//        // TODO: move the layout
//        float lx = getLayoutX(col);
//        float ly = getLayoutY(row);
//
//        print();
//        System.out.println("OJB: should move layout x to (" + lx + ", " + ly + ")");
    }

//    public @Nullable VerticalLayout makeVerticalLayoutAt(int row, int col, float vertSpacing) {
//        if (!inBounds(row, col))
//            return null;
//
//        VerticalLayout layout = new VerticalLayout(
//                getLayoutX(col),
//                getLayoutY(row),
//                getLayoutWidth(col),
//                getLayoutHeight(row),
//                vertSpacing
//        );
//
//        setLayoutAt(row, col, layout);
//        print();
//
//        return layout;
//    }
//
//    public @Nullable VerticalLayout makeVerticalLayoutAt(int row, int col, float vertSpacing, float fixedChildHeight) {
//        if (!inBounds(row, col))
//            return null;
//
//        VerticalLayout layout = new VerticalLayout(
//                getLayoutX(col),
//                getLayoutY(row),
//                getLayoutWidth(col),
//                getLayoutHeight(row),
//                vertSpacing,
//                fixedChildHeight
//        );
//
//        setLayoutAt(row, col, layout);
//        print();
//
//        return layout;
//    }

//    public @Nullable HorizontalLayout makeHorizontalLayoutAt(int row, int col, float horizSpacing) {
//        if (!inBounds(row, col))
//            return null;
//
////        HorizontalLayout layout = new HorizontalLayout(
////                getLayoutX(col),
////                getLayoutY(row),
////                getLayoutWidth(col),
////                getLayoutHeight(row),
////                horizSpacing
////        );
//        HorizontalLayout layout = HorizontalLayout
//                .build(getLayoutWidth(col), getLayoutHeight(row))
//                .withSpacing(horizSpacing);
//
//        setLayoutAt(row, col, layout);
//        print();
//
//        return layout;
//    }

    // TODO: not updated to the new API
//    public @Nullable HorizontalLayout makeHorizontalLayoutAt(int row, int col, float horizSpacing, float fixedChildWidth) {
//        if (!inBounds(row, col))
//            return null;
//
//        HorizontalLayout layout = HorizontalLayout
//                .build(getLayoutWidth(col), getLayoutHeight(row))
//                .withSpacing(horizSpacing)
//                .withFixedWidth(fixedChildWidth);
//
////        HorizontalLayout layout = new HorizontalLayout(
////                getLayoutX(col),
////                getLayoutY(row),
////                getLayoutWidth(col),
////                getLayoutHeight(row),
////                horizSpacing,
////                fixedChildWidth
////        );
//
//        setLayoutAt(row, col, layout);
//        print();
//
//        return layout;
//    }

    // Debug
    public void print() {
        System.out.println("GRID LAYOUT: (" + getLeft() + ", " + getBottom() + ") --> (" + getRight() + ", " + getTop() + ")");
        System.out.println("\tNum rows: " + rowHeights.size() + " | Num cols: " + columnWidths.size());
        System.out.println("\tTotal children: " + children.size());
    }

//    public GridLayout with_absolute_cols(float... widths) {
//        for (float colWidth : widths)
//            columnWidths.add(colWidth);
//
//        colType = GRID_TYPE.ABSOLUTE;
//        return this;
//    }
//
//    public GridLayout with_absolute_rows(float... heights) {
//        for (float rowHeight : heights)
//            rowHeights.add(rowHeight);
//
//        rowType = GRID_TYPE.ABSOLUTE;
//        return this;
//    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children.values())
            c.render(sb);
    }
}
