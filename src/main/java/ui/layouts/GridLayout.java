package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private GridLayout(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight) {
        setPrefWidthHeight(prefWidth, prefHeight);
        setBottomLeft(bottomLeftX, bottomLeftY);

        this.children = new HashMap<>();
        columnWidths = new ArrayList<>();
        rowHeights = new ArrayList<>();

        this.colType = GRID_TYPE.NONE;
        this.rowType = GRID_TYPE.NONE;
    }

    public GridLayout with_balanced_rows(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of rows; not altering");
            return this;
        }

        float rowHeight = getPrefHeight() / (float)count;
        System.out.println("OJB: making " + count + " rows of size " + rowHeight);

        for (int i = 0; i < count; ++i)
            rowHeights.add(rowHeight);

        rowType = GRID_TYPE.BALANCED;
        return this;
    }

    public GridLayout with_balanced_cols(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of columns; not altering");
            return this;
        }

        float colWidth = getPrefWidth() / (float)count;
        System.out.println("OJB: making " + count + " cols of size " + colWidth);

        for (int i = 0; i < count; ++i)
            columnWidths.add(colWidth);

        colType = GRID_TYPE.BALANCED;
        return this;
    }

    public static GridLayout build(float bottomLeftX, float bottomLeftY, float prefWidth, float prefHeight) {
        GridLayout res = new GridLayout(bottomLeftX, bottomLeftY, prefWidth, prefHeight);
        return res;
    }

    public boolean hasExistingAt(int row, int col) {
        return children.containsKey( new DualIntegerKey(row, col));
    }

    public @Nullable Layout getLayoutAt(int row, int col) {
        if (hasExistingAt(row, col))
            return children.get(new DualIntegerKey(row, col));
        else
            return null;
    }

    public boolean inBounds(int row, int col) {
        return (row < rowHeights.size()) && (col < columnWidths.size());
    }

    private float getLayoutX(int col) {
        float pos = getBottomLeftX();

        int index = 0;
        for (float w : columnWidths) {
            if (index++ >= col)
                break;

            pos += w;
        }

        return pos;
    }

    private float getLayoutY(int row) {
        float pos = getBottomLeftY();

        int index = 0;
        for (float h : rowHeights) {
            if (index++ >= row)
                break;

            pos += h;
        }

        return pos;
    }

    private float getLayoutWidth(int col) {
        if (colType == GRID_TYPE.BALANCED) {
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
        if (rowType == GRID_TYPE.BALANCED) {
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

    public @Nullable VerticalLayout makeVerticalLayoutAt(int row, int col, float vertSpacing) {
        if (!inBounds(row, col))
            return null;

        VerticalLayout layout = new VerticalLayout(
                getLayoutX(col),
                getLayoutY(row),
                getLayoutWidth(col),
                getLayoutHeight(row),
                vertSpacing
        );

        setLayoutAt(row, col, layout);
        print();

        return layout;
    }

    public @Nullable VerticalLayout makeVerticalLayoutAt(int row, int col, float vertSpacing, float fixedChildHeight) {
        if (!inBounds(row, col))
            return null;

        VerticalLayout layout = new VerticalLayout(
                getLayoutX(col),
                getLayoutY(row),
                getLayoutWidth(col),
                getLayoutHeight(row),
                vertSpacing,
                fixedChildHeight
        );

        setLayoutAt(row, col, layout);
        print();

        return layout;
    }

    public @Nullable HorizontalLayout makeHorizontalLayoutAt(int row, int col, float horizSpacing) {
        if (!inBounds(row, col))
            return null;

        HorizontalLayout layout = new HorizontalLayout(
                getLayoutX(col),
                getLayoutY(row),
                getLayoutWidth(col),
                getLayoutHeight(row),
                horizSpacing
        );

        setLayoutAt(row, col, layout);
        print();

        return layout;
    }

    public @Nullable HorizontalLayout makeHorizontalLayoutAt(int row, int col, float horizSpacing, float fixedChildWidth) {
        if (!inBounds(row, col))
            return null;

        HorizontalLayout layout = new HorizontalLayout(
                getLayoutX(col),
                getLayoutY(row),
                getLayoutWidth(col),
                getLayoutHeight(row),
                horizSpacing,
                fixedChildWidth
        );

        setLayoutAt(row, col, layout);
        print();

        return layout;
    }

    // Debug
    public void print() {
        System.out.println("GRID LAYOUT: (" + getBottomLeftX() + ", " + getBottomLeftY() + ") --> (" + getTopRightX() + ", " + getTopRightY() + ")");
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
