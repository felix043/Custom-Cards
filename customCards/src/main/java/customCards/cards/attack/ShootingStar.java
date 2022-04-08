package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.actions.AnimateAction;
import customCards.actions.StopAnimation;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeCardPath;
import static customCards.DefaultMod.makeID;

public class ShootingStar extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(ShootingStar.class.getSimpleName());
    public static final String IMG = makeCardPath("ShootingStar.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 10;

    public ShootingStar() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("ShootingStar"));
        actionManager.addToBottom(new WaitAction(24f));
        actionManager.addToBottom(new AnimateAction());
        actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        for (int i = 0; i < 36; i++) {
            waitAndAttack(p);
        }

        CardCrawlGame.sound.stop("ShootingStar");
        actionManager.addToBottom(new StopAnimation());
    }

    public void waitAndAttack(AbstractPlayer p) {
        actionManager.addToBottom(new WaitAction(0.48f));
        actionManager.addToBottom(new AnimateJumpAction(p));
        actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
