package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.TheDefault;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeCardPath;

public class FeatureRequest extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(FeatureRequest.class.getSimpleName());
    public static final String IMG = makeCardPath("FeatureRequest.png");
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public FeatureRequest() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.EMPTY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 5), 2));
        actionManager.addToBottom(new ApplyPowerAction(p, p, new PhantasmalPower(p, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
