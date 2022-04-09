package customCards.cards;

import customCards.DefaultMod;
import customCards.characters.SEP;
import customCards.patches.AnimatedCardsPatch;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static customCards.util.TextureLoader.getAnimatedCardTextures;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

    public static final CardColor COLOR = SEP.Enums.COLOR_SEPRED;

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
    }

    public void loadFrames(String cardName, int frameCount, float frameRate) {
        try {
            AnimatedCardsPatch.load(this, frameCount, frameRate, getAnimatedCardTextures(cardName));
        } catch (Exception e) {
            DefaultMod.logger.error("Failed to load animated card image for " + cardName + ".");
        }
    }
}