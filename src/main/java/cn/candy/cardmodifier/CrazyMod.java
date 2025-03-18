package cn.candy.cardmodifier;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import basemod.abstracts.AbstractCardModifier;
import cn.candy.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class CrazyMod extends AbstractCardModifier {
    public static String ID = ModHelper.makePath(CrazyMod.class.getSimpleName());
    private static final UIStrings uiStrings;
    public int cost=0;

    public CrazyMod() {
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return String.format(uiStrings.TEXT[0], rawDescription);
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.cost = 0;
    }

    public void onRemove(AbstractCard card) {
        card.cost = cost;
    }

    public AbstractCardModifier makeCopy() {
        return new CrazyMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    }
}

