package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.actions.StartShootingStarAction;
import customCards.actions.StopShootingStarAction;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class ShootingStar extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("ShootingStar"),
            "ShootingStar",
            2,
            1,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.RARE
    );

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    public ShootingStar() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("ShootingStar"));
        actionManager.addToBottom(new WaitAction(24f));
        actionManager.addToBottom(new StartShootingStarAction());

        for (int i = 0; i < 37; i++) {
            waitAndAttack(p);
        }

        actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.stop(makeID("ShootingStar"));
            }
        });
        actionManager.addToBottom(new StopShootingStarAction());
    }

    public void waitAndAttack(AbstractPlayer p) {
        actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        actionManager.addToBottom(new WaitAction(0.48f));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(cardInfo.upgradedCost);
            initializeDescription();
        }
    }
}
