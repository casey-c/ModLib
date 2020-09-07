package ui.screens;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScreenHelper {
    public static void showCustomScreen() {
        if (!CardCrawlGame.isInARun())
            return;

        // Close / hide the existing screens
        // TODO: boss relics may be bugged still
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.closeCurrentScreen();
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        } else if (!AbstractDungeon.isScreenUp) {
            // TODO: verify?
            //AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.NONE;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
            // TODO: (may need to look at map view?)
            AbstractDungeon.closeCurrentScreen();
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
            AbstractDungeon.deathScreen.hide();
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
            AbstractDungeon.bossRelicScreen.hide();
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            AbstractDungeon.closeCurrentScreen();
        }
        // TODO: ignore this for now?
//        else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedDeckButton) {
//            if (AbstractDungeon.previousScreen != null) {
//                AbstractDungeon.screenSwap = true;
//            }
//            AbstractDungeon.closeCurrentScreen();
//            AbstractDungeon.deckViewScreen.open();
//        }
        else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
            AbstractDungeon.dynamicBanner.hide();
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
            AbstractDungeon.gridSelectScreen.hide();
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
        }

        // Open our custom screen
        AbstractDungeon.dynamicBanner.hide();
        AbstractDungeon.isScreenUp = true;

        // TODO:
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        AbstractDungeon.overlayMenu.showBlackScreen();
    }

    public static void showCustomScreen(String soundID) {
        showCustomScreen();

        if (CardCrawlGame.isInARun())
            CardCrawlGame.sound.play(soundID);
    }

    // pretend to be the master deck view and let the base game handle the rest.
    public static void closeCustomScreen() {
        if (!CardCrawlGame.isInARun())
            return;

        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW;
        AbstractDungeon.closeCurrentScreen();
    }

    public static void closeCustomScreen(String soundID) {
        closeCustomScreen();

        if (CardCrawlGame.isInARun())
            CardCrawlGame.sound.play(soundID);
    }
}
