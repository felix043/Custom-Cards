package customCards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static customCards.DefaultMod.makeID;

public class TheProtagonistVariable extends DynamicVariable {

    @Override
    public String key() {
        return makeID("THEPROGAGONIST");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return card.isDamageModified;
    }

    @Override
    public int value(AbstractCard card) {
        return card.magicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return card.baseDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card.upgraded;
    }
}