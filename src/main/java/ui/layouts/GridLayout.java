package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.widgets.ScreenWidget;
import utils.DualIntegerKey;

import java.util.ArrayList;
import java.util.HashMap;

public class GridLayout extends Layout<GridLayout> {
    private HashMap<DualIntegerKey, ScreenWidget> children;

    private ArrayList<Float> columnWidths;
    private ArrayList<Float> rowHeights;

    // --------------------------------------------------------------------------------

    private GridLayout(float prefWidth, float prefHeight) {
        setPrefWidthHeight(prefWidth, prefHeight);

        this.children = new HashMap<>();
        columnWidths = new ArrayList<>();
        rowHeights = new ArrayList<>();
    }

    public static GridLayout build(float prefWidth, float prefHeight) {
        GridLayout layout = new GridLayout(prefWidth, prefHeight);
        return layout;
    }

    // TODO: lots of code duplication between row and col setters below; probably should refactor at some point

    // --------------------------------------------------------------------------------

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

        return this;
    }


    public GridLayout with_absolute_rows(float... heights) {
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
    }

    public <T extends Layout<T>> T setRawLayout(int row, int col, T  layout, AnchorPosition pos) {
        if (!inBounds(row, col))
            return layout;

        fixRawLayout(row, col, layout, pos);

        children.put(new DualIntegerKey(row, col), layout);
        return layout;
    }

    public <T extends ScreenWidget> T setWidget(int row, int col, T widget, AnchorPosition pos) {
        if (!inBounds(row, col))
            return widget;

        // Fix the widget in place specified by this grid
        widget.setPrefWidthHeight(getLayoutWidth(col), getLayoutHeight(row));

        if (pos == AnchorPosition.BOTTOM_LEFT)
            widget.setBottomLeft(getLayoutX(col), getLayoutY(row));
        else if (pos == AnchorPosition.TOP_LEFT)
            widget.setTopLeft(getLayoutX(col), getLayoutY(row) + getLayoutHeight(row));
        else if (pos == AnchorPosition.TOP_RIGHT)
            widget.setTopRight(getLayoutX(col) + getLayoutWidth(col), getLayoutY(row) + getLayoutHeight(row));
        else if (pos == AnchorPosition.BOTTOM_RIGHT)
            widget.setTopRight(getLayoutX(col) + getLayoutWidth(col), getLayoutY(row));

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
        System.out.println("GRID LAYOUT: (" + getLeft() + ", " + getBottom() + ") --> (" + getRight() + ", " + getTop() + ")");
        System.out.println("\tNum rows: " + rowHeights.size() + " | Num cols: " + columnWidths.size());
        System.out.println("\tTotal children: " + children.size());
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ScreenWidget c : children.values())
            c.render(sb);
    }
}
