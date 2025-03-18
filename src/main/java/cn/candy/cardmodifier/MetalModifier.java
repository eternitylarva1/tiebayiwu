package cn.candy.cardmodifier;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import cn.candy.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class MetalModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makePath(MetalModifier.class.getSimpleName());
    private static final UIStrings uiStrings;
    public int cost=0;

    public MetalModifier() {
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        //rawDescription中的格挡改为金属化
        return rawDescription.replace(uiStrings.TEXT[0], uiStrings.TEXT[1]);
    }

    public boolean shouldApply(AbstractCard card) {
        return CardModifierManager.hasModifier(card, ID);
    }

    public void onInitialApplication(AbstractCard card) {

    }

    public void onRemove(AbstractCard card) {
        card.rawDescription= card.rawDescription.replace(uiStrings.TEXT[1], uiStrings.TEXT[0]);
    }

    public AbstractCardModifier makeCopy() {
        return new MetalModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    }
}

