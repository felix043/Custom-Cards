package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import java.util.Random;

import static customCards.DefaultMod.makeCardPath;

public class PatienceHasLimits extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(PatienceHasLimits.class.getSimpleName());
    public static final String IMG = makeCardPath("PatienceHasLimits.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int TURNS = 15;
    private static int COUNTER = 0;

    public PatienceHasLimits() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (COUNTER >= TURNS) {
            if (new Random().nextInt(100) + 1 == 100) {
                AbstractDungeon.actionManager.addToBottom(new InstantKillAction(p));
            }
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new InstantKillAction(mo));
            }
        }
    }

    @Override
    public void atTurnStart() {
        COUNTER++;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
