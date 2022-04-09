package customCards.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import customCards.DefaultMod;
import customCards.characters.SEP;
import customCards.patches.AnimatedCardsPatch;

import static customCards.DefaultMod.makeID;
import static customCards.util.TextureLoader.getAnimatedCardTextures;
import static customCards.util.TextureLoader.getCardTextureString;

public abstract class BaseCard extends AbstractDefaultCard {
    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    protected CardStrings cardStrings;

    protected boolean upgradesDescription;

    protected int baseCost;

    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;

    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;

    public BaseCard(CardInfo cardInfo, boolean upgradesDescription) {
        this(cardInfo.cardName, cardInfo.cardCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, upgradesDescription);
    }

    public BaseCard(String cardName, int cost, CardType cardType, CardTarget target, CardRarity rarity, boolean upgradesDescription) {
        super(makeID(cardName), cardName, getCardTextureString(cardName), cost, "", cardType, COLOR, rarity, target);

        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);

        this.rawDescription = cardStrings.DESCRIPTION;
        this.originalName = cardStrings.NAME;
        this.name = originalName;

        this.baseCost = cost;

        this.upgradesDescription = upgradesDescription;

        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;

        initializeCard();
    }

    public void loadFrames(String cardName, int frameCount, float frameRate) {
        try {
            AnimatedCardsPatch.load(this, frameCount, frameRate, getAnimatedCardTextures(cardName));
        } catch (Exception e) {
            DefaultMod.logger.error("Failed to load animated card image for " + cardName + ".");
        }
    }


    public void initializeCard() {
        FontHelper.cardDescFont_N.getData().setScale(1.0f);
        this.initializeTitle();
        this.initializeDescription();
    }

    @Override
    public void update() {
        super.update();
    }
}