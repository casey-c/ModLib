package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.Widget;
import utils.DualIntegerKey;

import java.util.ArrayList;
import java.util.HashMap;

// Like other layouts; use like:

// e.g.
// layout = new GridLayout().withSpacing().withOtherSetup().withBalancedRows().withBalancedCols();
// layout.addChild(0, 0, x); layout.addChild(0, 1, y);

// todo: maybe make this not required; but can call computeLayout() to force a recompute of all children
// after you're done adding children; call computeLayout() to set all the children into the proper anchored positions

public class GridLayout3 extends Layout2<GridLayout3> {
    private HashMap<DualIntegerKey, Widget> children = new HashMap<>();
    private ArrayList<Float> rowHeights = new ArrayList<>();
    private ArrayList<Float> colWidths = new ArrayList<>();

    public GridLayout3() {
        // todo?
    }

    // --------------------------------------------------------------------------------

    private boolean rowInBounds(int row) { return row >= 0 && row < rowHeights.size(); }
    private boolean colInBounds(int col) { return col >= 0 && col < colWidths.size(); }
    private boolean inBounds(int row, int col) { return rowInBounds(row) && colInBounds(col); }

    private float getColWidth(int col) {
        if (col < 0 || col >= colWidths.size())
            return 0;
        else
            return colWidths.get(col);
    }

    private float getRowHeight(int row) {
        if (row < 0 || row >= rowHeights.size())
            return 0;
        else
            return rowHeights.get(row);
    }

    private float getColLeft(int col) {
        float left = getContentLeft();

        for (int i = 0; i < col && i < colWidths.size(); ++i) {
            left += getColWidth(i);
            left += horizontalSpacing;
        }

        return left;
    }

    private float getColRight(int col) {
        return getColLeft(col) + getColWidth(col);
    }

    private float getRowTop(int row) {
        float top = getContentTop();

        for (int i = 0; i < row && i < rowHeights.size(); ++i) {
            top -= getRowHeight(i);
            top -= verticalSpacing;
        }

        return top;
    }

    private float getRowBottom(int row) {
        return getRowTop(row) - getRowHeight(row);
    }

    // --------------------------------------------------------------------------------

    private void moveChildIntoPlace(int row, int col, Widget w) {
        w.setActualFromAnchor(getColLeft(col), getRowBottom(row), getColWidth(col), getRowHeight(row), AnchorPosition.BOTTOM_LEFT);
    }

    private void moveChildIntoSpan(int topRow, int botRow, int leftCol, int rightCol, Widget w) {
        float left = getColLeft(leftCol);
        float right = getColRight(rightCol);
        float top = getRowTop(topRow);
        float bot = getRowBottom(botRow);

        float width = right - left;
        float height = top - bot;

        w.setActualFromAnchor(left, bot, width, height, AnchorPosition.BOTTOM_LEFT);
    }

    public <T extends Widget<T>> T setWidget(int row, int col, T w) {
        if (!inBounds(row, col))
            return w;

        children.put(new DualIntegerKey(row, col), w);
        moveChildIntoPlace(row, col, w);

        return w;
    }

    public <T extends Widget<T>> T setWidget(int topRow, int botRow, int leftCol, int rightCol, T w) {
        if (!rowInBounds(topRow) || !rowInBounds(botRow) || !colInBounds(leftCol) || !colInBounds(rightCol))
            return w;

        children.put(new DualIntegerKey(topRow, leftCol), w);
        moveChildIntoSpan(topRow, botRow, leftCol, rightCol, w);

        return w;
    }

    // --------------------------------------------------------------------------------

    public void setBalancedCols(int numCols) {
        if (numCols <= 0) return;

        float width = getContentWidth() - (numCols - 1) * horizontalSpacing;
        float widthPerCol = width / numCols;

        colWidths.clear();

        for (int i = 0; i < numCols; ++i)
            colWidths.add(widthPerCol);
    }

    public GridLayout3 withBalancedCols(int numCols) {
        this.setBalancedCols(numCols);
        return this;
    }

    public void setBalancedRows(int numRows) {
        if (numRows <= 0) return;

        float height = getContentHeight() - (numRows - 1) * verticalSpacing;
        float heightPerCol = height / numRows;

        rowHeights.clear();

        for (int i = 0; i < numRows; ++i)
            rowHeights.add(heightPerCol);
    }

    public GridLayout3 withBalancedRows(int numRows) {
        this.setBalancedRows(numRows);
        return this;
    }

    // --------------------------------------------------------------------------------

    private float heightWithoutSpacing(int count) {
        float spacing = (count > 0 ) ? (count - 1) * verticalSpacing : 0.0f;
        return getContentHeight() - spacing;
    }

    private float widthWithoutSpacing(int count) {
        float spacing = (count > 0 ) ? (count - 1) * horizontalSpacing : 0.0f;
        return getContentWidth() - spacing;
    }

    // --------------------------------------------------------------------------------

    public void setRelativeRows(float... ratios) {
        float sum = 0.0f;
        for (float r : ratios) {
            if (r <= 0.0f) return;
            sum += r;
        }

        // epsilon safety threshold?
        if (sum < 0.001f)
            return;

        rowHeights.clear();

        for (float r : ratios) {
            rowHeights.add((r / sum) * heightWithoutSpacing(ratios.length));
        }
    }

    public GridLayout3 withRelativeRows(float... ratios) {
        setRelativeRows(ratios);
        return this;
    }

    public void setRelativeCols(float... ratios) {
        float sum = 0.0f;
        for (float r : ratios) {
            if (r <= 0.0f) return;
            sum += r;
        }

        // epsilon safety threshold?
        if (sum < 0.001f)
            return;

        colWidths.clear();

        for (float r : ratios) {
            colWidths.add((r / sum) * widthWithoutSpacing(ratios.length));
        }
    }

    public GridLayout3 withRelativeCols(float... ratios) {
        setRelativeCols(ratios);
        return this;
    }

    // --------------------------------------------------------------------------------

    public void setAbsoluteRows(float... heights) {
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
            return;

        float remaining = getContentHeight() - sum - ((heights.length - numNegative - 1) * verticalSpacing) - (numNegative * verticalSpacing);
        float balancedHeightForNegativeVals = (numNegative > 0 && remaining > 0.0f) ? (remaining / numNegative) : 0.0f;

        rowHeights.clear();
        for (float h : heights) {
            if (h < 0.0f)
                rowHeights.add(balancedHeightForNegativeVals);
            else
                rowHeights.add(h);
        }
    }

    public GridLayout3 withAbsoluteRows(float... heights) {
        setAbsoluteRows(heights);
        return this;
    }

    public void setAbsoluteCols(float... widths) {
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
            return;

        float remaining = getContentWidth() - sum - ((widths.length - numNegative - 1) * horizontalSpacing) - (numNegative * horizontalSpacing);
        float balancedWidthForNegativeVals = (numNegative > 0 && remaining > 0.0f) ? (remaining / numNegative) : 0.0f;

        colWidths.clear();
        for (float w : widths) {
            if (w < 0.0f)
                colWidths.add(balancedWidthForNegativeVals);
            else
                colWidths.add(w);
        }
    }

    public GridLayout3 withAbsoluteCols(float... widths) {
        setAbsoluteCols(widths);
        return this;
    }

    // --------------------------------------------------------------------------------

    @Override
    public void computeLayout() {
        for (DualIntegerKey key : children.keySet()) {
            int row = key.x;
            int col = key.y;
            Widget w = children.get(key);

            // TODO: recompute span. (children are only set into the hashmap at their top/left - nothing is stored
            //   to remind them they need to span more
            moveChildIntoPlace(row, col, w);
        }

    }

    @Override
    public float getPrefWidth() {
        return 0;
    }

    @Override
    public float getPrefHeight() {
        return 0;
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        for (Widget w : children.values())
            w.render(sb);
    }
}
