package ui.layouts;

import ui.widgets.Widget;

public class LayoutObject {
    public Widget widget;
    public AnchorPosition anchorPosition;
    public GrowthPolicy policy;

    public float x, y;
    public float width, height;


    public LayoutObject(Widget widget, AnchorPosition anchorPosition, GrowthPolicy policy) {
        this.widget = widget;
        this.anchorPosition = anchorPosition;
        this.policy = policy;
    }


//    public float getWidth() {
//        if (GrowthPolicy.isPreferredWidth(policy))
//            return widget.getPrefWidth();
//        else
//            return width;
//    }
}
