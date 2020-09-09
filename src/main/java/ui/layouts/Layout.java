package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.Collection;

// TODO: either add layout specific functions (clearAll? recomputePositions?) or remove this code smell!
public abstract class Layout<T extends Layout<T>> extends ScreenWidget {

    public T anchoredAt(float x,  float y, AnchorPosition pos) {
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
        else if (pos == AnchorPosition.CENTER_LEFT)
            this.setCenterLeft(x, y);
        else if (pos == AnchorPosition.CENTER_RIGHT)
            this.setCenterRight(x, y);
        else if (pos == AnchorPosition.TOP_CENTER)
            this.setTopCenter(x, y);
        else if (pos == AnchorPosition.BOTTOM_CENTER)
            this.setBottomCenter(x, y);
        else if (pos == AnchorPosition.CENTER)
            this.setCenter(x, y);

        return (T)this;
    }

    public T withDimensions(float prefWidth, float prefHeight) {
        this.setPrefWidthHeight(prefWidth, prefHeight);
        return (T)this;
    }
}
