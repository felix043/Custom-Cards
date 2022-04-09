package customCards.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;
import customCards.cards.power.impl.TheCouncilPower;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class TheCouncil extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("TheCouncil"),
            "TheCouncil",
            2,
            2,
            COLOR_SEPRED,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    public static String UPGRADE_DESCRIPTION;

    // STAT DECLARATION 	
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public TheCouncil() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        magicNumber = baseMagicNumber = MAGIC;
        UPGRADE_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(cardInfo.cardId).UPGRADE_DESCRIPTION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TheCouncilPower(p, p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}