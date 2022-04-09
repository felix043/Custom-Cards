package customCards.cards.curse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static customCards.DefaultMod.makeID;
import static customCards.util.TextureLoader.getCardTextureString;

public class TelephoneService extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("TelephoneService"),
            "TelephoneService",
            1,
            1,
            CardColor.COLORLESS,
            CardType.STATUS,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    public TelephoneService() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
