package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;
import utils.DualIntegerKey;

import java.util.ArrayList;
import java.util.HashMap;

public class GridLayout2 extends Layout<GridLayout2> {

    private HashMap<DualIntegerKey, ScreenWidget> children = new HashMap<>();
    private ArrayList<Float> columnWidths = new ArrayList<>();
    private ArrayList<Float> rowHeights = new ArrayList<>();

    private float verticalPadding, horizontalPadding;

    public static GridLayout2 buildRaw() {
        GridLayout2 layout = new GridLayout2();
        return layout;
    }

    public static GridLayout2 build(float prefWidth, float prefHeight) {
        GridLayout2 layout = new GridLayout2();
        layout.setPrefWidthHeight(prefWidth, prefHeight);

        // Start out at a 1x1 grid
        layout.rowHeights.clear();
        layout.columnWidths.clear();

        layout.rowHeights.add(prefHeight);
        layout.columnWidths.add(prefWidth);

        return layout;
    }

    public GridLayout2 withInternalPadding(float horizontalPadding, float verticalPadding) {
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        return this;
    }

    public GridLayout2 withVerticalPadding(float p) {
        this.verticalPadding = p;
        return this;
    }

    public GridLayout2 withHorizontalPadding(float p) {
        this.horizontalPadding = p;
        return this;
    }

    // --------------------------------------------------------------------------------

    public GridLayout2 with_balanced_rows(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of rows; not altering");
            return this;
        }

        float rowHeight = getPrefHeight() / (float)count;
        System.out.println("OJB: making " + count + " rows of size " + rowHeight);

        rowHeights.clear();

        for (int i = 0; i < count; ++i)
            rowHeights.add(rowHeight);

        return this;
    }

    public GridLayout2 with_relative_rows(float... ratios) {
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

        return this;
    }


    public GridLayout2 with_absolute_rows(float... heights) {
        // -1, 80.0f;
        float sum = 0.0f;
        int numNegative = 0;
        for (float h : heights) {
            if (h < 0.0f)
                numNegative++;
            else
                sum += h;
        }

        // epsilon safety check
        if (sum < 0.0001f)
            return this;

        float remaining = getPrefHeight() - sum;
        float balancedHeightForNegativeVals = (numNegative > 0 && remaining > 0.0f) ? (remaining / numNegative) : 0.0f;

        rowHeights.clear();
        for (float h : heights) {
            if (h < 0.0f)
                rowHeights.add(balancedHeightForNegativeVals);
            else
                rowHeights.add(h);
        }

        return this;
    }

    // --------------------------------------------------------------------------------

    public GridLayout2 with_balanced_cols(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of columns; not altering");
            return this;
        }

        float colWidth = getPrefWidth() / (float)count;
        System.out.println("OJB: making " + count + " cols of size " + colWidth);
        columnWidths.clear();

        for (int i = 0; i < count; ++i)
            columnWidths.add(colWidth);

        return this;
    }

    public GridLayout2 with_relative_cols(float... ratios) {
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

        return this;
    }


    public GridLayout2 with_absolute_cols(float... widths) {
        float sum = 0.0f;
        int numNegative = 0;
        for (float w : widths) {
            if (w < 0.0f) {
                numNegative++;
                continue;
            }

            sum += w;
        }

        // epsilon safety check
        if (sum < 0.0001f)
            return this;

        float remaining = getPrefWidth() - sum;
        float balancedWidthForNegativeVals = (numNegative > 0 && remaining > 0.0f) ? (remaining / numNegative) : 0.0f;

        columnWidths.clear();
        for (float w : widths) {
            if (w < 0.0f)
                columnWidths.add(balancedWidthForNegativeVals);
            else
                columnWidths.add(w);
        }

        return this;
    }

    // --------------------------------------------------------------------------------

    public boolean inBounds(int row, int col) {
        return (row < rowHeights.size()) && (col < columnWidths.size());
    }

    // TODO: add in anchors for GRIDs
    private <T extends Layout<T>> void fixRawLayout(int row, int col, T layout, AnchorPosition pos) {
        layout.withDimensions(getLayoutWidth(col), getLayoutHeight(row));
        float x = (pos == AnchorPosition.BOTTOM_LEFT || pos == AnchorPosition.TOP_LEFT) ? getLayoutX(col) : getLayoutX(col) + getLayoutWidth(col);
        float y = (pos == AnchorPosition.BOTTOM_LEFT || pos == AnchorPosition.BOTTOM_RIGHT) ? getLayoutY(row) : getLayoutY(row) + getLayoutHeight(row);
        layout.anchoredAt(x, y, pos);
        layout.recomputeLayout();
    }

    public <T extends Layout<T>> T setRawLayout(int row, int col, T layout, AnchorPosition pos) {
        if (!inBounds(row, col))
            return layout;

        fixRawLayout(row, col, layout, pos);

        children.put(new DualIntegerKey(row, col), layout);
        return layout;
    }

    public <T extends ScreenWidget> T setWidget(int row, int col, T widget) {
        return setWidget(row, col, widget, AnchorPosition.CENTER);
    }

    public <T extends ScreenWidget> T setWidget(int row, int col, T widget, AnchorPosition pos) {
        if (!inBounds(row, col))
            return widget;

        // Fix the widget in place specified by this grid
        widget.setPrefWidthHeight(getLayoutWidth(col), getLayoutHeight(row));

        float x = getLayoutX(col);
//        if (AnchorPosition.isCentralX(pos))
//            x = getLayoutX(col) + getLayoutWidth(col) / 2.0f;
//        else if (AnchorPosition.isRight(pos))
//            x = getLayoutX(col) + getLayoutWidth(col);

        float y = getLayoutY(row);
//        if (AnchorPosition.isCentralY(pos))
//            y = getLayoutY(row) + getLayoutHeight(row) / 2.0f;
//        else if (AnchorPosition.isRight(pos))
//            y = getLayoutY(row) + getLayoutHeight(row);

        widget.setBottomLeft(x, y);

        // TODO: actual better per widget anchors? -- i don't think this will work (didn't really think about it at all - just a guess)
        widget.setAnchor(pos);

//        if (pos == AnchorPosition.BOTTOM_LEFT)
//            widget.setBottomLeft(getLayoutX(col), getLayoutY(row));
//        else if (pos == AnchorPosition.TOP_LEFT)
//            widget.setTopLeft(getLayoutX(col), getLayoutY(row) + getLayoutHeight(row));
//        else if (pos == AnchorPosition.TOP_RIGHT)
//            widget.setTopRight(getLayoutX(col) + getLayoutWidth(col), getLayoutY(row) + getLayoutHeight(row));
//        else if (pos == AnchorPosition.BOTTOM_RIGHT)
//            widget.setTopRight(getLayoutX(col) + getLayoutWidth(col), getLayoutY(row));

        System.out.println("OJB: set widget success");
        widget.print();

        children.put(new DualIntegerKey(row, col), widget);
        return widget;
    }

    // --------------------------------------------------------------------------------
    // Layout getters

    private float getLayoutX(int col) {
        float pos = getLeft();

        int index = 0;
        for (float w : columnWidths) {
            if (index++ >= col)
                break;

            pos += w;
            pos += horizontalPadding;
        }

        if (columnWidths.size() > 0)
            pos -= horizontalPadding;

        return pos;
    }

    private float getLayoutY(int row) {
        float pos = getBottom();

        int index = 0;
        for (float h : rowHeights) {
            if (index++ >= row)
                break;

            pos += h;
            pos += verticalPadding;
        }

        if (rowHeights.size() > 0)
            pos -= verticalPadding;
        return pos;
    }

    private float getLayoutWidth(int col) {
        if (col < columnWidths.size())
            return columnWidths.get(col);
        else
            return getPrefWidth();
    }

    private float getLayoutHeight(int row) {
        if (row < rowHeights.size())
            return rowHeights.get(row);
        else
            return getPrefHeight();
    }

    // --------------------------------------------------------------------------------

    // Debug
    public void print() {
        System.out.println("GRID LAYOUT (width, height): " + getPrefWidth() + ", " + getPrefHeight());
        System.out.println("GRID LAYOUT bl (x, y): " + getLeft() + ", " + getBottom());
        System.out.println("GRID LAYOUT tr (x, y): " + getRight() + ", " + getTop());
        System.out.println();
        System.out.println("GRID LAYOUT (#rows, #cols): " + rowHeights.size() + ", " + columnWidths.size());
        System.out.println("GRID LAYOUT (#children): " + children.size());
        System.out.println();
        System.out.println("GRID LAYOUT row heights:");
        for (float h : rowHeights)
            System.out.println("\t " + h);
        System.out.println("GRID LAYOUT column widths:");
        for (float w : columnWidths)
            System.out.println("\t " + w);

        System.out.println();

//        System.out.println("GRID LAYOUT: (" + getLeft() + ", " + getBottom() + ") --> (" + getRight() + ", " + getTop() + ")");
//        System.out.println("\tNum rows: " + rowHeights.size() + " | Num cols: " + columnWidths.size());
//        System.out.println("\tTotal children: " + children.size());
    }

    // --------------------------------------------------------------------------------

    private float sum(ArrayList<Float> list) {
        float sum = 0.0f;
        for (float f : list)
            sum += f;
        return sum;
    }

    private float min(ArrayList<Float> list) {
        float m = 1000000.0f;
        for (float f : list) {
            if (f < m)
                m = f;
        }
        return m;
    }

    private float max(ArrayList<Float> list) {
        float m = 0.0f;
        for (float f : list) {
            if (f > m)
                m = f;
        }
        return m;
    }

    private float totalVerticalPadding() {
        return (rowHeights.size() > 0) ? (rowHeights.size() - 1) * verticalPadding : 0.0f;
    }

    private float totalHorizontalPadding() {
        return (columnWidths.size() > 0) ? (columnWidths.size() - 1) * horizontalPadding : 0.0f;
    }

    @Override public float totalChildrenHeight() { return sum(rowHeights) + totalVerticalPadding(); }
    @Override public float totalChildrenWidth() { return sum(columnWidths) + totalHorizontalPadding(); }

    @Override public float minChildWidth() { return min(columnWidths); }
    @Override public float maxChildWidth() { return max(columnWidths); }

    @Override public float minChildHeight() { return min(rowHeights); }
    @Override public float maxChildHeight() { return max(rowHeights); }

    @Override
    public void recomputeLayout() {
        // TODO? (unused currently - but probably should be)
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget w : children.values())
            w.render(sb);
    }
}
