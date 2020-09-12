package ui.widgets.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import utils.ColorHelper;
import utils.RenderingHelper;
import utils.TextureHelper;

public class TextButton extends AbstractButton<TextButton> {
    private String text;
    private static final BitmapFont font = FontHelper.tipBodyFont;
    private static final int CORNER_SIZE = 20;


    private static final Texture TEX_CORNER_BASE = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_BASE);
    private static final Texture TEX_CORNER_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_CORNER_TRIM);
    private static final Texture TEX_EDGE_TRIM = TextureHelper.getTexture(TextureHelper.TextureItem.BUTTON_EDGE_TRIM);

    private int centerWidth, centerHeight;
    private float textWidth, textHeight;

    // TODO: tweak these experimentally
    private static final int TEXT_VERTICAL_OFFSET = 7;

    private static final float TEXT_HORIZONTAL_PADDING = 4.0f;
    private static final float TEXT_VERTICAL_PADDING = 4.0f;

    public TextButton(String text) {
        this.text = text;

        // TODO: look over this & think about it more (don't think it's correct as is)
        this.textWidth = FontHelper.getSmartWidth(font, text, 10000.0f, 10.0f);
        this.textHeight = font.getLineHeight();

        // TODO: this needs to be redone with the setPrefWH overrides as setting a fixed width (e.g. from a layout)
        //   will not adjust properly. (i'm too tired at the moment to think it through)

        // TODO: note that the hitboxes are also not updated properly either.
        //   will need to think about layout stuff as well (maybe have the actual button width/height as below, but
        //   allow prefWH to be unlinked from this render stuff -- i.e. we can then place it around like layouts and
        //   simple labels are? -- only if set pref width / pref height is done manually (e.g. by a layout) otherwise,
        //   it should just be linked as usual i think. honestly not sure. need to reconsider layouts on this render()
        //   definitely when i'm more awake

        this.centerWidth = (int)(textWidth + 2 * TEXT_HORIZONTAL_PADDING);
        this.centerHeight = (int)(textHeight + 2 * TEXT_VERTICAL_PADDING);

        setPrefWidth(centerWidth + 2 * CORNER_SIZE);
        setPrefHeight(centerHeight + 2 * CORNER_SIZE);

        hb.width = getPrefWidth();
        hb.height = getPrefHeight();
    }

    // TODO
    @Override
    public void setPrefWidth(float w) {
        super.setPrefWidth(w);
        this.centerWidth = (int)(w - (2 * CORNER_SIZE));
    }

    // TODO
    @Override
    public void setPrefHeight(float h) {
        super.setPrefHeight(h);
        this.centerHeight = (int)(h - (2 * CORNER_SIZE));
    }

    public Color getBaseColor() {
        if (hb.hovered)
            //return (InputHelper.isMouseDown) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.BUTTON_HOVER_BASE;
            return (hb.clickStarted) ? ColorHelper.BUTTON_CLICK_BASE : ColorHelper.BUTTON_HOVER_BASE;
        else
            return ColorHelper.BUTTON_DEFAULT_BASE;
    }

    public Color getTrimColor() {
        return ColorHelper.BUTTON_TRIM;
    }

    @Override
    public void render(SpriteBatch sb) {
        // TODO: improve obviously
        float left = getLeft();
        float bottom = getBottom();

        RenderingHelper.renderDynamicPieces(sb,
                TEX_CORNER_BASE,
                TEX_CORNER_TRIM,
                TEX_EDGE_TRIM,
                left,
                bottom,
                left + CORNER_SIZE,
                left + CORNER_SIZE + centerWidth,
                bottom + CORNER_SIZE,
                bottom + CORNER_SIZE + centerHeight,
                centerWidth,
                centerHeight,
                getBaseColor(),
                getTrimColor(),
                CORNER_SIZE);


//        if (hb.hovered)
//            sb.setColor(Color.WHITE);
//        else
//            sb.setColor(Color.ORANGE);
//
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getLeft(), getBottom(), getPrefWidth(), getPrefHeight());

        // Text
        sb.setColor(Color.WHITE);
        FontHelper.renderFontLeftDownAligned(sb, font, text,
                //getLeft() + CORNER_SIZE + TEXT_HORIZONTAL_OFFSET,
                getCenterX() - (textWidth / 2.0f),
                getCenterY() - (textHeight / 2.0f) + TEXT_VERTICAL_OFFSET,
                Settings.CREAM_COLOR);

        hb.render(sb);
    }

//    @Override
//    public void update() {
//        super.update();
////        System.out.println("OJB: text button updated");
//        System.out.println("hb: " + hb.x + ", " + hb.y + " | wh: " + hb.width + ", " + hb.height);
////        print();
////        System.out.println();
////        System.out.println();
//    }
}
