package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;
import utils.TextureHelper;

public class CheckboxButton extends SimpleButton<CheckboxButton> {
    private static final Texture TEX_BASE = TextureHelper.TextureItem.CHECKBOX_BASE.get();
    private static final Texture TEX_TRIM = TextureHelper.TextureItem.CHECKBOX_TRIM.get();
    private static final Texture TEX_CHECK = TextureHelper.TextureItem.CHECKBOX_CHECK.get();

    private Color baseColorUnchecked = ColorHelper.BUTTON_DEFAULT_BASE;

    //private Color baseColorChecked = ColorHelper.CHECKBOX_CHECKED_BASE;
    private Color baseColorChecked = ColorHelper.BUTTON_CLICK_BASE;

    private Color trimColorUnchecked = ColorHelper.BUTTON_HOVER_BASE;
    private Color trimColorChecked = ColorHelper.BUTTON_CLICK_BASE;
    private Color checkColor = ColorHelper.BUTTON_TRIM;

    private boolean isChecked;

    private boolean labeled;
    private SimpleLabel label;

    private static final float BOX_LABEL_SPACING = 30;


    public CheckboxButton(InteractiveWidgetManager manager) {
        super(manager);
        this.width = TEX_BASE.getWidth();
        this.height = TEX_BASE.getHeight();
    }

    // Labeled version
    public CheckboxButton(InteractiveWidgetManager manager, String text) {
        super(manager);
        this.width = TEX_BASE.getWidth();
        this.height = TEX_BASE.getHeight();

        this.labeled = true;
        this.label = new SimpleLabel(text).withOffsets(0, 7);
        //this.label.withOffsets(0, (height - label.getPrefHeight()) * 0.5f);
    }

    @Override
    public void click() {
        if (!interactive)
            return;

        isChecked = !isChecked;
        super.click();
    }

    public boolean isChecked() { return isChecked; }

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
        if (isChecked) sb.setColor(baseColorChecked);
        else sb.setColor(baseColorUnchecked);
        //sb.draw(TEX_BASE, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
        sb.draw(TEX_BASE, leftBox, bottomBox);

        // Trim
        if (isChecked) sb.setColor(trimColorChecked);
        else sb.setColor(trimColorUnchecked);
        //sb.draw(TEX_TRIM, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
        sb.draw(TEX_TRIM, leftBox, bottomBox);

        // Checkbox
        if (isChecked) {
            sb.setColor(checkColor);
            //sb.draw(TEX_CHECK, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
            sb.draw(TEX_CHECK, leftBox, bottomBox);
        }

        // Text
        if (labeled) {
            float leftLabel = leftBox + width + BOX_LABEL_SPACING;
            //label.renderAt(sb, bottomLeftX, bottomLeftY, w, h);
            label.renderAt(sb, leftLabel, bottomLeftY, w, h);
        }

    }
}
