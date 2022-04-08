package customCards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import customCards.DefaultMod;

public class AnimateAction extends AbstractGameAction {

    public AnimateAction() {
    }

    @Override
    public void update() {
        if (!DefaultMod.drawStellarUI) {
            DefaultMod.drawStellarUI = true;
            this.isDone = true;
        }
    }
}
