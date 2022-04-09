package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class LowPolygon extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("LowPolygon"),
            "LowPolygon",
            2,
            1,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON
    );

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    public LowPolygon() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        actionManager.addToBottom(new TextAboveCreatureAction(p, "Shaders compiled: 0/6000"));
        actionManager.addToBottom(new WaitAction(1F));
        actionManager.addToBottom(new TextAboveCreatureAction(m, "Guess we'll wait"));
        actionManager.addToBottom(new SkipEnemiesTurnAction());
        actionManager.addToBottom(new PressEndTurnButtonAction());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(cardInfo.upgradedCost);
            initializeDescription();
        }
    }
}
