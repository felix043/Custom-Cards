package customCards.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.OnCardUseSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import customCards.DefaultMod;
import customCards.cards.curse.Confused;
import customCards.cards.skill.BigBrainEnergy;
import customCards.cards.skill.DistortedReality;
import customCards.cards.skill.SkipWorkDay;
import customCards.util.TextureLoader;

import java.util.Objects;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static customCards.DefaultMod.makeRelicOutlinePath;
import static customCards.DefaultMod.makeRelicPath;

public class Overworked extends CustomRelic implements CustomSavable<Integer>, OnCardUseSubscriber {

    private static int hoursOverworked;

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("Overworked");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Overworked.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OverworkedOutline.png"));

    public Overworked() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
        this.counter = 0;
        BaseMod.subscribe(this);
    }

    @Override
    public void atTurnStart() {
        if (this.counter >= 10) {
            flash();
            for (int i = 0; i < 3; i++) {
                actionManager.addToBottom(new PlayTopCardAction(getCurrRoom().monsters.getRandomMonster(null, true, cardRandomRng), true));
            }
            actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Confused(), 2, true, true));
        } else if (this.counter >= 5) {
            flash();
            AbstractDungeon.player.draw(1);
        } else if (this.counter <= -5) {
            flash();
            actionManager.addToBottom(new ExhaustAction(1, true));
        }
        setCounter(this.counter + 2);
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if (Objects.equals(abstractCard.cardID, SkipWorkDay.cardInfo.cardId)) {
            if (this.counter <= -10) {
                return;
            }
            setCounter(this.counter - 2);
        }
        if (Objects.equals(abstractCard.cardID, BigBrainEnergy.cardInfo.cardId)) {
            flash();
            setCounter(this.counter + 1);
        }
        if (Objects.equals(abstractCard.cardID, DistortedReality.cardInfo.cardId)) {
            if (this.counter <= -10) {
                return;
            }
            setCounter(this.counter - 2);
        }
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        if (inTopPanel) {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter), 0f + this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
        } else {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
        }
    }

    @Override
    public Integer onSave() {
        return hoursOverworked;
    }

    @Override
    public void onLoad(Integer integer) {
        if (integer == null) {
            return;
        }
        setCounter(hoursOverworked);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Overworked();
    }
}
