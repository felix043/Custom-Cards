package customCards.cards.skill;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class NothingToSeeHere extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("NothingToSeeHere"),
            "NothingToSeeHere",
            0,
            0,
            COLOR_SEPRED,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    public NothingToSeeHere() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(makeID("SadTrombone"));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
