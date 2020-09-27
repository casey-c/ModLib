package ui.widgets.radio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.SimpleButton;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;
import utils.TextureHelper;

import java.util.function.Consumer;

public class RadioButton extends SimpleButton<RadioButton> {
    private static final Texture TEX_BASE = TextureHelper.TextureItem.RADIO_BASE.get();
    private static final Texture TEX_TRIM = TextureHelper.TextureItem.RADIO_TRIM.get();
    private static final Texture TEX_CENTER = TextureHelper.TextureItem.RADIO_CENTER.get();

    private Color baseColorUnchecked = ColorHelper.BUTTON_DEFAULT_BASE;

    //private Color baseColorChecked = ColorHelper.CHECKBOX_CHECKED_BASE;
    private Color baseColorChecked = ColorHelper.BUTTON_CLICK_BASE;

    private Color trimColorUnchecked = ColorHelper.BUTTON_HOVER_BASE;
    private Color trimColorChecked = ColorHelper.BUTTON_CLICK_BASE;
    private Color centerColor = ColorHelper.BUTTON_TRIM;

    private boolean selected;

    private boolean labeled;
    private SimpleLabel label;

    private static final float BOX_LABEL_SPACING = 30;

    private RadioGroup group;

    private Consumer<RadioGroup> onChoose;


//    public RadioButton(InteractiveWidgetManager manager, RadioGroup group) {
//        super(manager);
//        this.width = TEX_BASE.getWidth();
//        this.height = TEX_BASE.getHeight();
//        this.group = group;
//    }

    // Labeled version
    public RadioButton(InteractiveWidgetManager manager, RadioGroup group, String text, Consumer<RadioGroup> onChoose) {
        super(manager);
        this.width = TEX_BASE.getWidth();
        this.height = TEX_BASE.getHeight();
        this.group = group;

        this.labeled = true;
        this.label = new SimpleLabel(text).withOffsets(0, 7);

        this.onChoose = onChoose;
        //this.label.withOffsets(0, (height - label.getPrefHeight()) * 0.5f);
    }

    @Override
    public void click() {
        if (!interactive)
            return;

        group.select(this);

        super.click();
    }

    public void setSelected(boolean val) {
        setSelected(val, true);
    }

    public void setSelected(boolean val, boolean callback) {
        if (val == selected)
            return;

        this.selected = val;

        if (callback && selected) {
            onChoose.accept(group);
        }
    }
    public boolean isSelected() { return selected; }

    @Override
    public float getPrefWidth() {
        if (labeled)
            return width + label.getPrefWidth() + BOX_LABEL_SPACING;
        else
            return width;
    }

    @Override
    public void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        float leftBox = bottomLeftX + (0.5f * w) - (width) * 0.5f;

        if (labeled)
            leftBox = leftBox - (BOX_LABEL_SPACING + label.getPrefWidth()) * 0.5f;

        float bottomBox = bottomLeftY;

        // Base
        if (selected) sb.setColor(baseColorChecked);
        else sb.setColor(baseColorUnchecked);
        //sb.draw(TEX_BASE, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
        sb.draw(TEX_BASE, leftBox, bottomBox);

        // Trim
        if (selected) sb.setColor(trimColorChecked);
        else sb.setColor(trimColorUnchecked);
        //sb.draw(TEX_TRIM, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
        sb.draw(TEX_TRIM, leftBox, bottomBox);

        // Checkbox
        if (selected) {
            sb.setColor(centerColor);
            //sb.draw(TEX_CHECK, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
            sb.draw(TEX_CENTER, leftBox, bottomBox);
        }

        // Text
        if (labeled) {
            float leftLabel = leftBox + width + BOX_LABEL_SPACING;
            //label.renderAt(sb, bottomLeftX, bottomLeftY, w, h);
            label.renderAt(sb, leftLabel, bottomLeftY, w, h);
        }

    }
}
