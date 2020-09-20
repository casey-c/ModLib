package ui.interactivity;

import ui.widgets.Widget;

import java.util.LinkedList;

public class InteractiveWidgetManager {
    LinkedList<IHasInteractivity> tracked = new LinkedList<>();

    public void track(IHasInteractivity widget) {
        tracked.add(widget);
    }

    public void enableAll() {
        for (IHasInteractivity w : tracked)
            w.enableInteractivity();

    }

    public void disableAll() {
        for (IHasInteractivity w : tracked)
            w.disableInteractivity();
    }

    public void enableJustThis(IHasInteractivity w) {
        disableAll();
        w.enableInteractivity();
    }
}
