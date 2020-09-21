package ui.widgets.dropdown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import ui.interactivity.InteractiveWidgetManager;
import ui.widgets.buttons.AbstractButton;
import ui.widgets.labels.SimpleLabel;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

import java.util.function.Consumer;

public class TextDropDownMenuItem extends AbstractButton<TextDropDownMenuItem> {
    private TextDropDownMenu parent;
    private SimpleLabel label;
    private Consumer<TextDropDownMenu> onSelect;

    private static final float HORIZONTAL_OFFSET = 26;
    private static final float VERTICAL_OFFSET = 5;
    //private static final float HORIZONTAL_PADDING = 40;
    private static final float HORIZONTAL_PADDING = 144;
    private static final float VERTICAL_PADDING = 30;

    private static final Texture TEX_CORNER_BASE = TextureHelper.TextureItem.DROPDOWN_CORNER_BASE.get();
    private static final Texture TEX_CORNER_TRIM = TextureHelper.TextureItem.DROPDOWN_CORNER_TRIM.get();
    private static final Texture TEX_EDGE_TRIM = TextureHelper.TextureItem.DROPDOWN_EDGE_TRIM.get();
    private static final Texture TEX_ICON = TextureHelper.TextureItem.DROPDOWN_ICON.get();
    private final int CORNER_SIZE = 16;

    public TextDropDownMenuItem(InteractiveWidgetManager manager, TextDropDownMenu parent, String text, Consumer<TextDropDownMenu> onSelect) {
        super(manager);

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

    public String getText() { return label.getText(); }

    @Override public float getPrefWidth() {
        return label.getPrefWidth() + HORIZONTAL_PADDING + HORIZONTAL_OFFSET;
    }

    @Override public float getPrefHeight() {
        return label.getPrefHeight() + VERTICAL_PADDING;
    }

    public Color getBaseColor() {
        return (hb != null && hb.hovered) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.BUTTON_DEFAULT_BASE;
    }

    public Color getTrimColor() {
        return ColorHelper.BUTTON_TRIM;
    }

    private static final float SHRINK_AMT = 1.0f;

    @Override
    public void renderAt(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float width, float height) {
        // Necessary for hitboxes to move into position
        //super.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
        fixHitbox(bottomLeftX, bottomLeftY, width, height);

        // render background
        if (parent.isShowingDropDown()) {
            // TODO
            sb.setColor(ColorHelper.BUTTON_TRIM_2);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY, width, height);

            sb.setColor(getBaseColor());
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, bottomLeftX, bottomLeftY + SHRINK_AMT, width, height - SHRINK_AMT - SHRINK_AMT);
            //RenderingHelper.renderDynamicBase(sb, TEX_CORNER_BASE, (int)bottomLeftX, (int)bottomLeftY, (int)width, (int)height, CORNER_SIZE, getBaseColor());
            //RenderingHelper.renderDynamicTrim(sb, TEX_CORNER_TRIM, TEX_EDGE_TRIM, (int)bottomLeftX, (int)bottomLeftY, (int)width, (int)height, CORNER_SIZE, getTrimColor());
        }
        else {
            RenderingHelper.renderDynamicBase(sb, TEX_CORNER_BASE, (int)bottomLeftX, (int)bottomLeftY, (int)width, (int)height, CORNER_SIZE, getBaseColor());
            RenderingHelper.renderDynamicTrim(sb, TEX_CORNER_TRIM, TEX_EDGE_TRIM, (int)bottomLeftX, (int)bottomLeftY, (int)width, (int)height, CORNER_SIZE, getTrimColor());

            // Icon
            sb.setColor(ColorHelper.FADED_CREAM);
            sb.draw(TEX_ICON, bottomLeftX + width - HORIZONTAL_OFFSET - TEX_ICON.getWidth(), bottomLeftY + (height - TEX_ICON.getHeight()) * 0.5f);
        }

        label.renderAt(sb, bottomLeftX, bottomLeftY, width, height);
        hb.render(sb);
    }
}
