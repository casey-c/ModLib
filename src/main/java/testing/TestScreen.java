package testing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ui.GrowthPolicy;
import ui.layouts.*;
import ui.screens.DynamicScreen;
import ui.widgets.SimplePadding;
import ui.widgets.buttons.ButtonFactory;
import ui.widgets.buttons.CheckboxButton;
import ui.widgets.buttons.TextButton;
import ui.widgets.dropdown.DropDownController2;
import ui.widgets.labels.SimpleLabel;
import ui.widgets.lines.HorizontalLine;
import ui.widgets.lines.VerticalLine;
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

        setMargins(80, 40, 40, 80);

        anchorList.addAll(Arrays.asList(AnchorPosition.values()));

        layout = new GridLayout()
                .anchoredAt(this)
                .withSpacing(30.0f)
                //.withAbsoluteRows(60.0f, -1)
                .withAbsoluteRows(80, 80, 80, 80, -1)
                //.withBalancedRows(1)
                .withBalancedCols(2);

        DropDownController2 dropDown = layout.setWidget(0, 1, new DropDownController2(interactiveWidgetManager)).withGrowthPolicy(GrowthPolicy.EXPANDING_X);
        dropDown.addItem("Choice 1", x->{});
        dropDown.addItem("Choice 2", x->{});
        dropDown.addItem("Caw", x->{
            //SoundHelper.cawCaw();
        });
        dropDown.addItem("Choice 3", x->{});

        layout.setWidget(0, 0, new CheckboxButton(interactiveWidgetManager)).withOnClick( checkboxButton -> {
            if (checkboxButton.isChecked())
                dropDown.setGrowthPolicy(GrowthPolicy.EXPANDING_BOTH); // TODO: expanding both has bugs! (not vertically centered)
            else
                dropDown.setGrowthPolicy(GrowthPolicy.PREFERRED_BOTH);
        });

        DropDownController2 dropDown2 = layout.setWidget(1, 1, new DropDownController2(interactiveWidgetManager)).withGrowthPolicy(GrowthPolicy.EXPANDING_Y);
        dropDown2.addItem("Choice 1", x->{});
        dropDown2.addItem("Choice 2", x->{});
        dropDown2.addItem("Caw", x->{
            //SoundHelper.cawCaw();
        });
        dropDown2.addItem("Choice 3", x->{});

        //DropDownController dropDown = layout.setWidget(1, 1, 0, 1, new DropDownController()).withGrowthPolicy(GrowthPolicy.EXPANDING_X);
//        DropDownController2 dropDown = layout.setWidget(1, 1, 0, 1, new DropDownController2()).withContentAnchorPosition(AnchorPosition.TOP_LEFT);
//        dropDown.addItem("Choice 1 ", x -> { System.out.println("Choice 1 selected"); });
//        dropDown.addItem("Choice 2 ", x -> { System.out.println("Choice 2 selected"); SoundHelper.cawCaw(); });
//        dropDown.addItem("Much longer and wordy Choice Three (the best) ", x -> { System.out.println("Choice 3 selected"); });
//
//        layout.setWidget(0, 0, new TextButton("Cycle Position")).withOnClick( onClick -> {
//            AnchorPosition next = nextAnchor();
//            System.out.println("next anchor position: " + next);
//            dropDown.setContentAnchorPosition(next);
//        }).withGrowthPolicy(GrowthPolicy.EXPANDING_BOTH);
//
//        layout.setWidget(0, 1, new CheckboxButton()).withOnClick( checkboxButton -> {
//            if (checkboxButton.isChecked())
//                dropDown.setGrowthPolicy(GrowthPolicy.EXPANDING_X);
//            else
//                dropDown.setGrowthPolicy(GrowthPolicy.PREFERRED_BOTH);
//        });

//                .withContentAnchorPosition(AnchorPosition.TOP_LEFT)
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
