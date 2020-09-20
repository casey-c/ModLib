package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.interactivity.InteractiveWidgetManager;
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

    public CheckboxButton(InteractiveWidgetManager manager) {
        super(manager);
        this.width = TEX_BASE.getWidth();
        this.height = TEX_BASE.getHeight();
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
    public void renderBackground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        // Base
        if (isChecked) sb.setColor(baseColorChecked);
        else sb.setColor(baseColorUnchecked);
        sb.draw(TEX_BASE, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );

        // Trim
        if (isChecked) sb.setColor(trimColorChecked);
        else sb.setColor(trimColorUnchecked);
        sb.draw(TEX_TRIM, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );

        // Checkbox
        if (isChecked) {
            sb.setColor(checkColor);
            sb.draw(TEX_CHECK, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
        }

    }
}
