package customCards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import customCards.DefaultMod;

public class StopAnimation extends AbstractGameAction {

    public StopAnimation() {
    }

    @Override
    public void update() {
        DefaultMod.drawStellarUI = false;
        this.isDone = true;

    }
}
