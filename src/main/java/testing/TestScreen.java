package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.widgets.dropdown.DropDownMenu;
import utils.ColorHelper;
import utils.SoundHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class TestScreen extends DynamicScreen<TestScreen> {
    private GridLayout layout;

    private ArrayList<AnchorPosition> anchorList = new ArrayList<>();
    int anchorListPos = 0;

    public TestScreen(int width, int height) {
        super(width, height);

        anchorList.addAll(Arrays.asList(AnchorPosition.values()));
        setMargins(80, 40, 40, 80);

        layout = new GridLayout()
                .anchoredAt(this)
                .withSpacing(30)
                .withBalancedCols(2)
                .withAbsoluteRows(60, 60, -1);

        layout.setWidget(0, 0, new DropDownMenu(interactiveWidgetManager))
              .withChild("Choice 1", onSelect -> {})
              .withChild("Caw Caw", onSelect -> { SoundHelper.cawCaw(); })
              .withChild("Choice 3", onSelect -> {});

        layout.setWidget(1, 0, new DropDownMenu(interactiveWidgetManager))
                .withChild("Choice 1", onSelect -> {})
                .withChild("Much Longer and WOrdy choice 2", onSelect -> { SoundHelper.cawCaw(); })
                .withChild("Pre selected choice 3", true, onSelect -> {});

//        layout = new GridLayout()
//                .anchoredAt(this)
//                .withSpacing(30.0f)
//                //.withAbsoluteRows(60.0f, -1)
//                .withAbsoluteRows(80, 80, 80, 80, -1)
//                //.withBalancedRows(1)
//                .withBalancedCols(2);
//
//        DebugWidget dw = layout.setWidget(0, 3, 0, 0, new DebugWidget());
//
//        TextDropDownMenu dropDown = layout.setWidget(0, 1, new TextDropDownMenu(interactiveWidgetManager)).withGrowthPolicy(GrowthPolicy.EXPANDING_X);
//
//        for (AnchorPosition pos : AnchorPosition.values()) {
//            dropDown.addItem(pos.name(), onSelect -> {
//                dw.setContentAnchorPosition(pos);
//            });
//        }
//
//        // Start off centered
//        dropDown.selectByString("CENTER");
//
//
//        layout.setWidget(1, 1, new TextButton(interactiveWidgetManager, "Reset to Center")).withGrowthPolicy(GrowthPolicy.EXPANDING_BOTH).withOnClick(onClick -> {
//            SoundHelper.cawCaw();
//            dropDown.selectByString("CENTER");
//        });
//
//        layout.setWidget(2, 1, new CheckboxButton(interactiveWidgetManager, "Hello World"));

    }

    private AnchorPosition nextAnchor() {
        int index = anchorListPos++;
        if (index < anchorList.size())
            return anchorList.get(index);
        else {
            anchorListPos = 1;
            return anchorList.get(0);
        }
    }

    @Override public Color getTrimColor() { return ColorHelper.rainbowColor(); }

    @Override
    protected void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        super.renderForeground(sb, bottomLeftX, bottomLeftY, width, height);

        layout.render(sb);
    }

    @Override
    public void update() {
        layout.update();
    }
}
