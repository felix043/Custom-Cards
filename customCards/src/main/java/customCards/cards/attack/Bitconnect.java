package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeCardPath;
import static customCards.DefaultMod.makeID;

public class Bitconnect extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String NAME = Bitconnect.class.getSimpleName();
    public static final String ID = DefaultMod.makeID(NAME);
    public static final String IMG = makeCardPath("Bitconnect.png");
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;
    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    public Bitconnect() {
        super(ID, COST, NAME, TYPE, COLOR, RARITY, TARGET);
        loadFrames(NAME, 150, 0.04F);
        baseDamage = DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("Bitconnect"));
        for (int i = 0; i < 35; i++) {
            actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        for (int i = 0; i < 35 / 2; i++) {
            actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public void triggerWhenDrawn() {
        CardCrawlGame.sound.play(makeID("HeHeHey"));
        super.triggerWhenDrawn();
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
