package customCards.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.SEP;
import customCards.powers.TheCouncilPower;

import static customCards.DefaultMod.makeCardPath;

public class TheCouncil extends AbstractDynamicCard {

    // TEXT DECLARATION 
    public static final String ID = DefaultMod.makeID(TheCouncil.class.getSimpleName());
    public static final String IMG = makeCardPath("TheCouncil.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // STAT DECLARATION 	
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public TheCouncil() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TheCouncilPower(p, p, magicNumber), magicNumber));
        /*
        Hey do you see this "amount" and "stackAmount" up here^ (press ctrl+p inside the parentheses to see parameters)
        THIS DOES NOT MEAN APPLY 1 POWER 1 TIMES. If you put 2 in both numbers it would apply 2. NOT "2 STACKS, 2 TIMES".

        The stackAmount is for telling ApplyPowerAction what to do if a stack already exists. Which means that it will go
        "ah, I see this power has an ID ("") that matches the power I received. I will therefore instead add the stackAmount value
        to this existing power's amount" (Thank you Johnny)

        Which is why if we want to apply 2 stacks with this card every time, want 2 in both numbers -
        "Always add 2, even if the player already has this power."
        */
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}