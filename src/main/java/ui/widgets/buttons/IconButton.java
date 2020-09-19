package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import utils.ColorHelper;

public class IconButton extends SimpleButton<IconButton> {
    private Texture icon;
    private Color iconColor;

    public IconButton(Texture icon) {
        this(icon, Color.WHITE);
    }

    public IconButton(Texture icon, Color iconColor) {
        this(icon, iconColor, ColorHelper.BUTTON_DEFAULT_BASE, ColorHelper.BUTTON_HOVER_BASE, ColorHelper.BUTTON_CLICK_BASE);
    }

    public IconButton(Texture icon, Color iconColor, Color baseColor, Color hoverColor, Color clickColor) {
        super(baseColor, hoverColor, clickColor);

        this.icon = icon;
        this.iconColor = iconColor;

        this.width = icon.getWidth();
        this.height = icon.getHeight();
        this.hb = new Hitbox(width, height);

        this.baseColor = baseColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    @Override
    public void renderForeground(SpriteBatch sb, float bottomLeftX, float bottomLeftY, float w, float h) {
        sb.setColor(iconColor);
        sb.draw(icon, bottomLeftX + (0.5f * w) - (width * 0.5f), bottomLeftY + (0.5f * h) - (height * 0.5f) );
    }
}
