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
import ui.widgets.dropdown.DropDownController;
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
                .withAbsoluteRows(60.0f, -1)
                //.withBalancedRows(1)
                .withBalancedCols(1);

        DropDownController dropDown = layout.setWidget(1, 0, new DropDownController()).withGrowthPolicy(GrowthPolicy.EXPANDING_X);
        layout.setWidget(0, 0, new TextButton("Cycle Position")).withOnClick( onClick -> {
            AnchorPosition next = nextAnchor();
            System.out.println("next anchor position: " + next);
            dropDown.setContentAnchorPosition(next);
        }).withGrowthPolicy(GrowthPolicy.EXPANDING_BOTH);

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
