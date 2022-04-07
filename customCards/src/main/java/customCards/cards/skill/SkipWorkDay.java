package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;
import customCards.orbs.*;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.COUNTER;
import static customCards.DefaultMod.makeCardPath;

public class SkipWorkDay extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(SkipWorkDay.class.getSimpleName());
    public static final String IMG = makeCardPath("SkipWorkDay.png");

    // STAT DECLARATION 	
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    public SkipWorkDay() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        COUNTER++;
        actionManager.addToBottom(new GainBlockAction(p, block));
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
