package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static customCards.DefaultMod.makeCardPath;

public class LostInTranslation extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(LostInTranslation.class.getSimpleName());
    public static final String IMG = makeCardPath("LostInTranslation.png");

    // STAT DECLARATION 	
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 2;
    private static final int TIMES = 10;

    public LostInTranslation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.EMPTY);
    }

    //todo double card and no exhaust (maybe ephereal)
    //todo add bill gates card
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < TIMES; i++) {
            actionManager.addToBottom(new PlayTopCardAction(getCurrRoom().monsters.getRandomMonster(null, true, cardRandomRng), true));
        }
        actionManager.addToBottom(new DiscardAction(p, p, 10, true, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
