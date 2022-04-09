package customCards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import customCards.DefaultMod;

public class StopShootingStarAction extends AbstractGameAction {

    public StopShootingStarAction() {
    }

    @Override
    public void update() {
        DefaultMod.drawStellarUI = false;
        this.isDone = true;
    }
}
