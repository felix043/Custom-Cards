package customCards.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import customCards.cards.AbstractDynamicCard;
import customCards.cards.CardInfo;

import static customCards.DefaultMod.makeID;
import static customCards.characters.SEP.Enums.COLOR_SEPRED;
import static customCards.util.TextureLoader.getCardTextureString;

public class Blob extends AbstractDynamicCard {

    private final static CardInfo cardInfo = new CardInfo(
            makeID("Blob"),
            "Blob",
            2,
            2,
            COLOR_SEPRED,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON
    );

    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DMG = 4;
    private final int TIMES = 2;

    public Blob() {
        super(cardInfo.cardId, getCardTextureString(cardInfo.imgName), cardInfo.cardCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, TIMES, false), TIMES));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, TIMES), TIMES));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, TIMES, false), TIMES));
        }
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
