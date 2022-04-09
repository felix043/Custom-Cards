package customCards.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import customCards.DefaultMod;
import customCards.util.TextureLoader;

import static customCards.DefaultMod.makeRelicOutlinePath;
import static customCards.DefaultMod.makeRelicPath;

public class BiMirLaufts extends CustomRelic implements ClickableRelic {

    public static final String ID = DefaultMod.makeID("BiMirLaufts");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BiMirLaufts.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BiMirLauftsOutline.png"));

    private boolean usedThisTurn = false;
    private boolean isPlayerTurn = false;

    public BiMirLaufts() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {
        if (!isObtained || usedThisTurn || !isPlayerTurn) {
            return;
        }

        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) { // Only if you're in combat
            usedThisTurn = true; // Set relic as "Used this turn"
            flash(); // Flash
            stopPulse();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
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
