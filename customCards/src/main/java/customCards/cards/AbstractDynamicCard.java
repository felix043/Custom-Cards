package customCards.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import customCards.DefaultMod;
import customCards.patches.AnimatedCardsPatch;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static customCards.util.TextureLoader.getAnimatedCardTextures;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

    // "How come DefaultCommonAttack extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }

    public AbstractDynamicCard(final String id,
                               final int cost,
                               final String name,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, getCardTextureString(name), cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }

    public void loadFrames(String cardName, int frameCount, float frameRate) {
        try {
            AnimatedCardsPatch.load(this, frameCount, frameRate, getAnimatedCardTextures(cardName));
        } catch (Exception e) {
            DefaultMod.logger.error("Failed to load animated card image for " + cardName + ".");
        }
    }

    public static String getCardTextureString(final String cardName) {
        String textureString = DefaultMod.makeCardPath(cardName + ".png");

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = DefaultMod.makeCardPath("NothingToSeeHere.png");
        }

        return textureString;
    }
}