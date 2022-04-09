package customCards.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import customCards.DefaultMod;
import customCards.characters.SEP;
import customCards.patches.AnimatedCardsPatch;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static customCards.util.TextureLoader.getAnimatedCardTextures;
import static customCards.util.TextureLoader.getCardTextureString;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

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

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }

    //
    public AbstractDynamicCard(CardInfo cardInfo, boolean upgradesDescription) {
        this(cardInfo.cardId, cardInfo.cardName, cardInfo.cardCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, upgradesDescription);
    }

    public AbstractDynamicCard(String cardId, String cardName, int cost, CardType cardType, CardTarget target, CardRarity rarity, boolean upgradesDescription) {
        super(cardId, cardName, getCardTextureString(cardName), cost, languagePack.getCardStrings(cardId).DESCRIPTION, cardType, COLOR, rarity, target);

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

    public void initializeCard() {
        FontHelper.cardDescFont_N.getData().setScale(1.0f);
        this.initializeTitle();
        this.initializeDescription();
    }

    public void loadFrames(String cardName, int frameCount, float frameRate) {
        try {
            AnimatedCardsPatch.load(this, frameCount, frameRate, getAnimatedCardTextures(cardName));
        } catch (Exception e) {
            DefaultMod.logger.error("Failed to load animated card image for " + cardName + ".");
        }
    }
}