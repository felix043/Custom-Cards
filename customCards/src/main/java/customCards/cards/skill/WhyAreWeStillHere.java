package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class WhyAreWeStillHere extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("WhyAreWeStillHere"),
            "WhyAreWeStillHere",
            1,
            1,
            COLOR_SEPRED,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    private static final int BLOCK = 15;
    private static final int UPGRADE_PLUS_BLOCK = 15;

    public WhyAreWeStillHere() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 4, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
