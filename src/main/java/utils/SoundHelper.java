package utils;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class SoundHelper {
    public static void playScreenOpen() {
        CardCrawlGame.sound.play("DECK_OPEN");
    }

    public static void playScreenClose() {
        CardCrawlGame.sound.play("DECK_CLOSE");
    }

    public static void playUIHover() {
        CardCrawlGame.sound.play("UI_HOVER");
    }

    public static void playUIClick() {
        CardCrawlGame.sound.play("UI_CLICK_1");

        // honestly, i don't like the inconsistency here
//        if (MathUtils.randomBoolean()) CardCrawlGame.sound.play("UI_CLICK_1");
//        else CardCrawlGame.sound.play("UI_CLICK_2");
    }

    public static void cawCaw() {
        CardCrawlGame.sound.play("VO_CULTIST_1A");
    }
}
