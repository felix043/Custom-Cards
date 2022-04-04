package customCards.cards.skill;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;

import static customCards.DefaultMod.makeCardPath;
import static customCards.DefaultMod.makeID;

public class NothingToSeeHere extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(NothingToSeeHere.class.getSimpleName());
    public static final String IMG = makeCardPath("NothingToSeeHere.png");

    // STAT DECLARATION 	
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 0;

    public NothingToSeeHere() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
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
