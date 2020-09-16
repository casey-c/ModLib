package ui.layouts;

import ui.GrowthPolicy;
import ui.widgets.Widget;

public class LayoutObject {
    public Widget widget;
    public AnchorPosition anchorPosition;
    public GrowthPolicy policy;

    public float x, y;
    public float width, height;


    public LayoutObject(Widget widget, GrowthPolicy policy, AnchorPosition anchorPosition) {
        this.widget = widget;
        this.policy = policy;
        this.anchorPosition = anchorPosition;
    }


//    public float getWidth() {
//        if (GrowthPolicy.isPreferredWidth(policy))
//            return widget.getPrefWidth();
//        else
//            return width;
//    }
}
