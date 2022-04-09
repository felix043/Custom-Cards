package customCards.characters;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.abstracts.CustomSavableRaw;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import customCards.DefaultMod;
import customCards.cards.tutorial.*;
import customCards.orbs.*;
import customCards.relics.DefaultClickableRelic;
import customCards.relics.Overworked;
import customCards.relics.PlaceholderRelic;
import customCards.relics.PlaceholderRelic2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

import static customCards.DefaultMod.*;

public class SEP extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    public static class Enums {
        @SpireEnum
        public static PlayerClass SEP;
        @SpireEnum(name = "SEP_RED") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOR_SEPRED;
        @SpireEnum(name = "SEP_RED")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_SEPRED;
    }

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 69;
    public static final int MAX_HP = 69;
    public static final int STARTING_GOLD = 420;
    public static final int CARD_DRAW = 8;
    public static final int ORB_SLOTS = 1;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("SEP");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "customCardsResources/images/char/SEP/orb/layer1.png",
            "customCardsResources/images/char/SEP/orb/layer2.png",
            "customCardsResources/images/char/SEP/orb/layer3.png",
            "customCardsResources/images/char/SEP/orb/layer4.png",
            "customCardsResources/images/char/SEP/orb/layer5.png",
            "customCardsResources/images/char/SEP/orb/layer6.png",
            "customCardsResources/images/char/SEP/orb/layer1d.png",
            "customCardsResources/images/char/SEP/orb/layer2d.png",
            "customCardsResources/images/char/SEP/orb/layer3d.png",
            "customCardsResources/images/char/SEP/orb/layer4d.png",
            "customCardsResources/images/char/SEP/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================
    public SEP(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures, "customCardsResources/images/char/SEP/orb/vfx.png", (String) null, null);

        // =============== TEXTURES, ENERGY, LOADOUT =================

        initializeClass("customCardsResources/images/char/SEP/SEPcharacter.png", // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                SEP_SHOULDER, // campfire pose
                SEP_SHOULDER, // another campfire pose
                SEP_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================

        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public void applyStartOfTurnPreDrawCards() {
        COUNTER++;
        rotateDay();
    }

    @Override
    public void preBattlePrep() {
        super.preBattlePrep();
        if (COUNTER == 0) {
            COUNTER = 1;
        }
        rotateDay();
    }

    public void rotateDay() {
        Map<String, CustomSavableRaw> saveFields = BaseMod.getSaveFields();
        CustomSavableRaw count = saveFields.get("COUNT");
        switch (COUNTER) {
            case 1:
                channelOrb(new MondayOrb());
                break;
            case 2:
                channelOrb(new TuesdayOrb());
                break;
            case 3:
                channelOrb(new WednesdayOrb());
                break;
            case 4:
                channelOrb(new ThursdayOrb());
                break;
            case 5:
                channelOrb(new FridayOrb());
                COUNTER = 0;
                break;
        }
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(DefaultCommonAttack.ID);
        retVal.add(DefaultUncommonAttack.ID);
        retVal.add(DefaultRareAttack.ID);

        retVal.add(DefaultCommonSkill.ID);
        retVal.add(DefaultUncommonSkill.ID);
        retVal.add(DefaultRareSkill.ID);

        retVal.add(DefaultCommonPower.ID);
        retVal.add(DefaultUncommonPower.ID);
        retVal.add(DefaultRarePower.ID);

        retVal.add(DefaultAttackWithVariable.ID);
        retVal.add(DefaultSecondMagicNumberSkill.ID);
        retVal.add(OrbSkill.ID);
        return retVal;
    }

    // Starting Relics
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(Overworked.ID);

        // Mark relics as seen - makes it visible in the compendium immediately
        // If you don't have this it won't be visible in the compendium until you see them in game
        UnlockTracker.markRelicAsSeen(PlaceholderRelic.ID);
        UnlockTracker.markRelicAsSeen(PlaceholderRelic2.ID);
        UnlockTracker.markRelicAsSeen(DefaultClickableRelic.ID);
        UnlockTracker.markRelicAsSeen(Overworked.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.play(makeID("WasupWasup"));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.LONG, false);
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_SEPRED;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return DefaultMod.DEFAULT_GRAY;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new SEP(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return DefaultMod.DEFAULT_GRAY;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return DefaultMod.DEFAULT_GRAY;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
