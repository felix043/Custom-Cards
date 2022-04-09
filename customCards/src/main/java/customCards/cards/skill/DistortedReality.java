package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;
import customCards.orbs.*;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.COUNTER;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class DistortedReality extends AbstractDynamicCard {

    public final static CardInfo cardInfo = new CardInfo(
            makeID("DistortedReality"),
            "DistortedReality",
            2,
            2,
            COLOR_SEPRED,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON
    );

    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    public DistortedReality() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        actionManager.addToBottom(new GainBlockAction(p, p, block));
        actionManager.addToBottom(new VFXAction(new BlizzardEffect(100, false)));

        COUNTER += 3;
        if (COUNTER > 5) {
            COUNTER = COUNTER % 5;
        }
        switch (COUNTER) {
            case 1:
                actionManager.addToBottom(new ChannelAction(new MondayOrb()));
                break;
            case 2:
                actionManager.addToBottom(new ChannelAction(new TuesdayOrb()));
                break;
            case 3:
                actionManager.addToBottom(new ChannelAction(new WednesdayOrb()));
                break;
            case 4:
                actionManager.addToBottom(new ChannelAction(new ThursdayOrb()));
                break;
            case 5:
                actionManager.addToBottom(new ChannelAction(new FridayOrb()));
                COUNTER = 0;
                break;
        }

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
