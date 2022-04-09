package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class Bitconnect extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("Bitconnect"),
            "BitConnecta", //c: Text and description loads, Any other name: Image loads
            3,
            3,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.RARE
    );

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    public Bitconnect() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        loadFrames(Bitconnect.class.getSimpleName(), 150, 0.04F);
        baseDamage = DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("Bitconnect"));
        for (int i = 0; i < 35; i++) {
            actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_HEAVY));
        }
        for (int i = 0; i < 35 / 2; i++) {
            actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
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
            upgradeBaseCost(cardInfo.upgradedCost);
            initializeDescription();
        }
    }
}
