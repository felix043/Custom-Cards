package customCards.cards;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public class CardInfo {
    public String cardId;
    public String imgName;
    public int cardCost;
    public int upgradedCost;
    public CardColor cardColor;
    public CardType cardType;
    public CardTarget cardTarget;
    public CardRarity cardRarity;

    public CardInfo(String cardId, String imgName, int cardCost, int upgradedCost, CardColor cardColor, CardType cardType, CardTarget cardTarget, CardRarity cardRarity) {
        this.cardId = cardId;
        this.imgName = imgName;
        this.cardCost = cardCost;
        this.upgradedCost = upgradedCost;
        this.cardColor = cardColor;
        this.cardType = cardType;
        this.cardTarget = cardTarget;
        this.cardRarity = cardRarity;
    }
}
