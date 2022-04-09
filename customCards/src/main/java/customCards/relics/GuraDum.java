package customCards.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import customCards.DefaultMod;
import customCards.util.TextureLoader;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static customCards.DefaultMod.makeRelicOutlinePath;
import static customCards.DefaultMod.makeRelicPath;

public class GuraDum extends CustomRelic implements ClickableRelic {

    public static final String ID = DefaultMod.makeID("GuraDum");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GuraDum.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GuraDumOutline.png"));

    private boolean usedThisTurn = false;
    private boolean isPlayerTurn = false;

    public GuraDum() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {
        if (!isObtained || usedThisTurn || !isPlayerTurn) {
            return;
        }

        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            usedThisTurn = true;
            flash();
            stopPulse();
            CardCrawlGame.sound.play("GuraDum");
            AbstractCreature creature = getRandomMonster();
            actionManager.addToBottom(new TalkAction(creature, "What?", 2.0f, 2.0f));
            actionManager.addToBottom(new TalkAction(creature, "I cannot comprehend", 2.0f, 2.0f));
            actionManager.addToBottom(new LoseHPAction(creature, player, 10, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public void atPreBattle() {
        usedThisTurn = false;
        beginLongPulse();
    }

    public void atTurnStart() {
        usedThisTurn = false;
        isPlayerTurn = true;
        beginLongPulse();
    }

    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false;
        stopPulse();
    }

    @Override
    public void onVictory() {
        stopPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
