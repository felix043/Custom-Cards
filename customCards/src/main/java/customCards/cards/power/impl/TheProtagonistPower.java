package customCards.cards.power.impl;

import basemod.BaseMod;
import basemod.interfaces.CloneablePowerInterface;
import basemod.interfaces.OnPlayerDamagedSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import customCards.DefaultMod;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static customCards.DefaultMod.makePowerPath;
import static customCards.util.TextureLoader.getTexture;

//Gain 1 dex for the turn for each card played.

public class TheProtagonistPower extends AbstractPower implements CloneablePowerInterface, OnPlayerDamagedSubscriber {
    public static final String POWER_ID = DefaultMod.makeID("TheProtagonistPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = getTexture(makePowerPath("TheProtagonist84.png"));
    private static final Texture tex32 = getTexture(makePowerPath("TheProtagonist32.png"));
    public AbstractCreature source;
    private static boolean isActive;

    public TheProtagonistPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        isActive = true;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
        BaseMod.subscribe(this);
    }

    @Override
    public int receiveOnPlayerDamaged(int enemyDamage, DamageInfo damageInfo) {
        if (isActive && player.currentHealth + player.currentBlock <= enemyDamage) {
            isActive = false;
            actionManager.addToBottom(new ReducePowerAction(player, player, this, 10));
            return player.currentHealth + player.currentBlock - 1;
        }
        return enemyDamage;
    }

    // Note: If you want to apply an effect when a power is being applied you have 3 options:
    //onInitialApplication is "When THIS power is first applied for the very first time only."
    //onApplyPower is "When the owner applies a power to something else (only used by Sadistic Nature)."

    //onReceivePowerPower from StSlib is "When any (including this) power is applied to the owner."

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TheProtagonistPower(owner, source, amount);
    }
}
