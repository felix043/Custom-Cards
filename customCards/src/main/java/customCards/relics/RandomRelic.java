package customCards.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import customCards.DefaultMod;
import customCards.util.TextureLoader;

import static customCards.DefaultMod.makeRelicOutlinePath;
import static customCards.DefaultMod.makeRelicPath;

public class RandomRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("RandomRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RandomRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RandomRelic.png"));

    public RandomRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }


    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
