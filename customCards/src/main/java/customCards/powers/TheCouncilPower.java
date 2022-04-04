package customCards.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import customCards.DefaultMod;
import customCards.util.TextureLoader;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makePowerPath;

public class TheCouncilPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = DefaultMod.makeID("TheCouncilPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("TheCouncil84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("TheCouncil32.png"));
    public AbstractCreature source;

    public TheCouncilPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), true));

    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        actionManager.addToBottom(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        int count = 0;
        for (final AbstractCard c : actionManager.cardsPlayedThisTurn) {
            ++count;
        }

        if (count > 0) {
            flash();
            for (int i = 0; i < count; ++i) {
                actionManager.addToBottom(
                        new ReducePowerAction(owner, owner, DexterityPower.POWER_ID, amount));
            }
        }

    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new TheCouncilPower(owner, source, amount);
    }
}
