package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import java.util.Random;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class PatienceHasLimits extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("PatienceHasLimits"),
            "PatienceHasLimits",
            3,
            3,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON
    );

    private static final int TURNS = 15;
    private static int COUNTER = 0;

    public PatienceHasLimits() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
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
            upgradeBaseCost(cardInfo.upgradedCost);
            initializeDescription();
        }
    }
}
