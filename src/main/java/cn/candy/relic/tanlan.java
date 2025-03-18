package cn.candy.relic;

import basemod.abstracts.CustomRelic;
import cn.candy.helpers.ModHelper;
import cn.candy.utils.Invoker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.RestRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * 这个遗物会在开始的时候赋予房间所有怪物3点血量
 *
 * @author : Administrator
 * @date : 2020-08-06 16:27
 **/
@SuppressWarnings("unused")
public class tanlan extends CustomRelic  {
    /**
     * 遗物ID 随便写 但是需要和json文件名称一致
     * 比如我这里最终是 CANDY_MOD_Money 就需要最后json文件内有 CANDY_MOD_Money 的遗物信息
     */
    public static final String ID = ModHelper.makePath(tanlan.class.getSimpleName());
    /**
     * 日志对象
     */
    private static final Logger log = LogManager.getLogger(tanlan.class);

    /**
     * 构造函数
     */
    public tanlan() {
        //图片使用内置的 使用破碎王冠 的图标
        //使用内置图标就不需要导入了 想自定义可以抄其他的mod或者看教程

        super(ID, new Texture(Gdx.files.internal("images/relics/aoman.png")), RelicTier.BOSS, LandingSound.CLINK);
    }



    @Override
    public void onEnterRoom(AbstractRoom room) {
        this.grayscale = false;
        this.usedUp = false;
        this.description = this.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    public void onSpendGold() {
        if (!this.usedUp) {
            flash();
           this.usedUp();
           AbstractDungeon.shopScreen.coloredCards.clear();
           AbstractDungeon.shopScreen.colorlessCards.clear();
            ArrayList<?> relics = Invoker.getField(AbstractDungeon.shopScreen, "relics");
// 调用clear方法清空集合
            Invoker.invoke(relics, "clear");
            ArrayList<?> potions = Invoker.getField(AbstractDungeon.shopScreen, "potions");
// 调用clear方法清空集合
            Invoker.invoke(potions, "clear");
        }
    }
    /**
     * 重写遗物的描述内容 可以不用管
     *
     * @return 字符串内容
     */
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


}
