package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;
import utils.DualIntegerKey;

import java.util.ArrayList;
import java.util.HashMap;

public class GridLayout extends Layout<GridLayout> {

    private HashMap<DualIntegerKey, ScreenWidget> children = new HashMap<>();
    private ArrayList<Float> columnWidths = new ArrayList<>();
    private ArrayList<Float> rowHeights = new ArrayList<>();

    private float verticalPadding, horizontalPadding;

    public static GridLayout buildRaw() {
        GridLayout layout = new GridLayout();
        return layout;
    }

    public static GridLayout build(float prefWidth, float prefHeight) {
        GridLayout layout = new GridLayout();
        layout.setPrefWidthHeight(prefWidth, prefHeight);

        // Start out at a 1x1 grid
        layout.rowHeights.clear();
        layout.columnWidths.clear();

        layout.rowHeights.add(prefHeight);
        layout.columnWidths.add(prefWidth);

        return layout;
    }

    public GridLayout withInternalPadding(float horizontalPadding, float verticalPadding) {
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        return this;
    }

    public GridLayout withVerticalPadding(float p) {
        this.verticalPadding = p;
        return this;
    }

    public GridLayout withHorizontalPadding(float p) {
        this.horizontalPadding = p;
        return this;
    }

    // --------------------------------------------------------------------------------

    private float heightWithoutPadding(int count) {
        float padding = (count > 0 ) ? (count - 1) * verticalPadding : 0.0f;
        return getPrefHeight() - padding;
    }

    private float widthWithoutPadding(int count) {
        float padding = (count > 0 ) ? (count - 1) * horizontalPadding : 0.0f;
        return getPrefWidth() - padding;
    }

    // --------------------------------------------------------------------------------

    public GridLayout with_balanced_rows(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of rows; not altering");
            return this;
        }

        float rowHeight = heightWithoutPadding(count) / (float)count;

        rowHeights.clear();

        for (int i = 0; i < count; ++i)
            rowHeights.add(rowHeight);

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
            rowHeights.add((r / sum) * heightWithoutPadding(ratios.length));
        }

        return this;
    }


    public GridLayout with_absolute_rows(float... heights) {
        float sum = 0.0f;
        int numNegative = 0;
        for (float h : heights) {
            if (h < 0.0f)
                numNegative++;
            else {
                sum += h;
            }
        }

        // epsilon safety check
        if (sum < 0.0001f)
            return this;

        float remaining = getPrefHeight() - sum - ((heights.length - numNegative - 1) * verticalPadding) - (numNegative * verticalPadding);
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

    public GridLayout with_balanced_cols(int count) {
        if (count <= 0) {
            System.out.println("OJB: WARNING: nonpositive number of columns; not altering");
            return this;
        }

        float colWidth = widthWithoutPadding(count) / (float)count;
        columnWidths.clear();

        for (int i = 0; i < count; ++i)
            columnWidths.add(colWidth);

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
            columnWidths.add((r / sum) * widthWithoutPadding(ratios.length));
        }

        return this;
    }


    public GridLayout with_absolute_cols(float... widths) {
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

        float remaining = getPrefWidth() - sum - ((widths.length - numNegative - 1) * horizontalPadding) - (numNegative * horizontalPadding);
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

    public <T extends ScreenWidget> T setWidget(int row, int col, T widget) {
        return setWidget(row, col, widget, AnchorPosition.CENTER);
    }

    public <T extends ScreenWidget> T setWidget(int row, int col, T widget, AnchorPosition pos) {
        if (!inBounds(row, col))
            return widget;

        // Fix the widget in place specified by this grid
        widget.setPrefWidthHeight(getLayoutWidth(col), getLayoutHeight(row));
        widget.setBottomLeft(getLayoutX(col), getLayoutY(row));
        widget.setAnchor(pos);

        if (widget instanceof Layout)
            ((Layout) widget).recomputeLayout();

        children.put(new DualIntegerKey(row, col), widget);
        return widget;
    }

    // --------------------------------------------------------------------------------
    // Layout getters

    private float getLayoutX(int col) {
        float pos = getLeft();

        for (int i = 0; i < col && i < columnWidths.size(); ++i) {
            pos += columnWidths.get(i);
            pos += horizontalPadding;
        }

        return pos;
    }

    private float getLayoutY(int row) {
        float pos = getBottom();

        for (int i = 0; i < row && i < rowHeights.size(); ++i) {
            pos += rowHeights.get(i);
            pos += verticalPadding;
        }

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
