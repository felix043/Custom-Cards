package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class FeatureRequest extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("FeatureRequest"),
            "FeatureRequest",
            0,
            0,
            COLOR_SEPRED,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    public FeatureRequest() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        this.tags.add(CardTags.EMPTY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 5), 2));
        actionManager.addToBottom(new ApplyPowerAction(p, p, new PhantasmalPower(p, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
