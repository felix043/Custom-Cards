package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import static customCards.DefaultMod.makeCardPath;
import static customCards.DefaultMod.makeID;

public class Telephone extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(Telephone.class.getSimpleName());
    public static final String IMG = makeCardPath("Telephone.png");

    // STAT DECLARATION
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 15;

    public Telephone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("EmotionalDamage"));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, p, damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
