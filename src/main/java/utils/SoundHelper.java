package utils;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class SoundHelper {
    public static void playScreenOpen() {
        CardCrawlGame.sound.play("DECK_OPEN");
    }

    public static void playScreenClose() {
        CardCrawlGame.sound.play("DECK_CLOSE");
    }
}
