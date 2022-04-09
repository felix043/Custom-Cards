package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class Telephone extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("Telephone"),
            "Telephone",
            2,
            1,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE
    );

    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 15;

    public Telephone() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
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
            upgradeBaseCost(cardInfo.upgradedCost);
            initializeDescription();
        }
    }
}
