package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.widgets.buttons.AbstractButton;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;

import java.util.function.Consumer;

public class DropDownItem2 extends AbstractButton<DropDownItem2> {
    private DropDownController2 parent;
    private SimpleLabel label;
    private Consumer<DropDownController2> onSelect;

    private static final float HORIZONTAL_OFFSET = 8;
    private static final float VERTICAL_OFFSET = 0;
    private static final float HORIZONTAL_PADDING = 40;
    private static final float VERTICAL_PADDING = 20;

    public DropDownItem2(DropDownController2 parent, String text, Consumer<DropDownController2> onSelect) {
        this.parent = parent;
        this.onSelect = onSelect;

        this.label = new SimpleLabel(text);

        float centeredHeight = (label.getPrefHeight() * 0.5f) + VERTICAL_OFFSET;
        label.withOffsets(HORIZONTAL_OFFSET, centeredHeight);

        this.onClick = item -> { parent.select(this); };

        hb = new Hitbox(getPrefWidth(), getPrefHeight());
    }

    public void notifySelect() {
        onSelect.accept(parent);
    }


    @Override public float getPrefWidth() {
        return label.getPrefWidth() + HORIZONTAL_PADDING;
    }

    @Override public float getPrefHeight() {
        return label.getPrefHeight() + VERTICAL_PADDING;
    }

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Necessary for hitboxes to move into position
        //super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
        fixHitbox(bottomLeftX, bottomLeftY, width, height);

        if (hb != null && hb.hovered) sb.setColor(ColorHelper.VERY_DIM_MAGENTA);
        else sb.setColor(ColorHelper.VERY_DIM_GREEN);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

        label.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
        hb.render(sb);
    }
}
