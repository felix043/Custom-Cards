package customCards.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import customCards.DefaultMod;
import customCards.cards.AbstractDynamicCard;
import customCards.characters.TheDefault;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeCardPath;

public class RadiationProtection extends AbstractDynamicCard {

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(RadiationProtection.class.getSimpleName());
    public static final String IMG = makeCardPath("5GProtection.png");
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;
    private static final int BLOCK = 2;
    private static final int DEXTERITY = 2;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public RadiationProtection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
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
