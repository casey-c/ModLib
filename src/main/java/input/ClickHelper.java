package input;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.HashMap;
import java.util.function.Consumer;

@SpireInitializer
public class ClickHelper implements PostUpdateSubscriber {
    // --------------------------------------------------------------------------------
    /**
     * Can be used to update or remove tracked callback functions
     */
    public static class ID {
        private int id;
        private ID(int id) {
            this.id = id;
        }
    }

    private static class GenericObject {
        private Object obj;
        private Consumer<Object> callback;

        private GenericObject(Object obj, Consumer<Object> callback) {
            this.obj = obj;
            this.callback = callback;
        }

        private void activate() {
            callback.accept(obj);
        }
    }

    private static class HBObject {
        private Hitbox hb;
        private Object obj;
        private Consumer<Object> callback;

        private HBObject(Hitbox hb, Object obj, Consumer<Object> callback) {
            this.hb = hb;
            this.obj = obj;
            this.callback = callback;
        }

        private void testAndActivate() {
            if (hb.hovered)
                callback.accept(obj);
        }
    }

    // --------------------------------------------------------------------------------

    // Singleton pattern (private constructor -- use static accessor methods!)
    private static class ClickHelperHolder { private static final ClickHelper INSTANCE = new ClickHelper(); }
    private static ClickHelper getInstance() { return ClickHelperHolder.INSTANCE; }
    private ClickHelper() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        // this will construct our helper object
        getInstance();
    }

    // --------------------------------------------------------------------------------

    private static int globalID = 0;

    private HashMap<Integer, GenericObject> rc = new HashMap<>();
    private HashMap<Integer, HBObject> rcHB = new HashMap<>();

    private HashMap<Integer, GenericObject> lc = new HashMap<>();
    private HashMap<Integer, HBObject> lcHB = new HashMap<>();

    private boolean mouseDownRight, mouseDownLeft;

    // --------------------------------------------------------------------------------

    /**
     * Add a generic callback function to the list of events fired when a right click occurs. @see ModLib.input#watchRightClickHB to register a hitbox.
     * @param obj the target of the callback function (usually called with "this" from the creator of the callback)
     * @param callback the function called when right click occurs
     * @return an ID which can be used to remove or replace this callback at some point in the future
     */
    public static ID watchRightClick(Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        ID id = new ID(globalID++);
        instance.rc.put(id.id, new GenericObject(obj, callback));

        return id;
    }

    public static ID watchLeftClick(Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        ID id = new ID(globalID++);
        instance.lc.put(id.id, new GenericObject(obj, callback));

        return id;
    }

    /**
     * Update a generic callback function in the list of events fired when a right click occurs. Only updates preexisting objects.
     * @param id a preexisting identifier
     * @param obj the target of the callback function (usually called with "this" from the creator of the callback)
     * @param callback the function called when right click occurs
     */
    public static void updateRightClick(ID id, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        if (!instance.rc.containsKey(id.id))
            return;

        GenericObject g = instance.rc.get(id.id);
        g.obj = obj;
        g.callback = callback;
    }

    public static void updateLeftClick(ID id, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        if (!instance.lc.containsKey(id.id))
            return;

        GenericObject g = instance.lc.get(id.id);
        g.obj = obj;
        g.callback = callback;
    }

    /**
     * Add a callback function that fires when the provided hitbox is hovered while a right click occurs.
     * @param hb the hitbox to watch
     * @param obj the target of the callback function (usually called with "this" from the creator of the callback)
     * @param callback the function called when right click occurs and the hitbox is hovered
     * @return an ID which can be used to remove or replace this callback at some point in the future
     */
    public static ID watchRightClickHB(Hitbox hb, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        ID id = new ID(globalID++);
        instance.rcHB.put(id.id, new HBObject(hb, obj, callback));

        return id;
    }

    public static ID watchLeftClickHB(Hitbox hb, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        ID id = new ID(globalID++);
        instance.lcHB.put(id.id, new HBObject(hb, obj, callback));

        return id;
    }

    /**
     * Update an existing hitbox right click callback.
     * @param id a preexisting identifier
     * @param hb the hitbox to watch
     * @param obj the target of the callback function (usually called with "this" from the creator of the callback)
     * @param callback the function called when the hitbox is right clicked
     */
    public static void updateRightClickHB(ID id, Hitbox hb, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        if (!instance.rcHB.containsKey(id.id))
            return;

        HBObject h = instance.rcHB.get(id.id);
        h.hb = hb;
        h.obj = obj;
        h.callback = callback;
    }

    public static void updateLeftClickHB(ID id, Hitbox hb, Object obj, Consumer<Object> callback) {
        ClickHelper instance = getInstance();

        if (!instance.lcHB.containsKey(id.id))
            return;

        HBObject h = instance.lcHB.get(id.id);
        h.hb = hb;
        h.obj = obj;
        h.callback = callback;
    }

    /**
     * Remove the supplied callback.
     * @param id a preexisting identifier
     */
    public static void removeRightClick(ID id) {
        ClickHelper instance = getInstance();
        instance.rc.remove(id.id);
        instance.rcHB.remove(id.id);
    }

    public static void removeLeftClick(ID id) {
        ClickHelper instance = getInstance();
        instance.lc.remove(id.id);
        instance.lcHB.remove(id.id);
    }

    // --------------------------------------------------------------------------------

    private void rightClickHandler() {
        System.out.println("OJB: right click");

        for (GenericObject o : rc.values())
            o.activate();

        for (HBObject o : rcHB.values())
            o.testAndActivate();
    }

    private void leftClickHandler() {
        System.out.println("OJB: regular click");
        for (GenericObject o : lc.values())
            o.activate();

        for (HBObject o : lcHB.values())
            o.testAndActivate();
    }

    @Override
    public void receivePostUpdate() {
        // Handle right clicks
        if (InputHelper.isMouseDown_R) {
            mouseDownRight = true;
        } else {
            // We already had the mouse down, and now we released, so fire our right click event
            if (mouseDownRight) {
                rightClickHandler();
                mouseDownRight = false;
            }
        }

        if (InputHelper.isMouseDown) {
            mouseDownLeft = true;
        } else {
            // We already had the mouse down, and now we released, so fire our right click event
            if (mouseDownLeft) {
                leftClickHandler();
                mouseDownLeft = false;
            }
        }

    }

}
