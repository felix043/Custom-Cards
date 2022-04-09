package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class RadiationProtection extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("RadiationProtection"),
            "5GProtection",
            1,
            1,
            COLOR_SEPRED,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON
    );

    private static final int BLOCK = 2;
    private static final int DEXTERITY = 2;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public RadiationProtection() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseBlock = BLOCK;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, DEXTERITY), DEXTERITY));
        actionManager.addToBottom(new GainBlockAction(p, p, block));
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
