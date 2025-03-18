package cn.candy.powers;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import cn.candy.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class HalfBlockMetalPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("NoBlockPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justApplied = false;

    public HalfBlockMetalPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("noBlock");
        if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
            this.justApplied = true;
        }

        this.type = PowerType.BUFF;

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public float modifyBlockLast(float blockAmount) {
        if (blockAmount > 0) {
          this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MetallicizePower(this.owner, (int) (blockAmount/2)), (int) (blockAmount/2), true));
        }
        return 0.0F;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
