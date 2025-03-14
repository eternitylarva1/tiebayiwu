package cn.candy.relic;

import basemod.abstracts.CustomRelic;
import cn.candy.config.RelicConfig;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 这个遗物会在开始的时候赋予房间所有怪物3点血量
 *
 * @author : Administrator
 * @date : 2020-08-06 16:27
 **/
@SuppressWarnings("unused")
public class Money extends CustomRelic {
    /**
     * 遗物ID 随便写 但是需要和json文件名称一致
     * 比如我这里最终是 CANDY_MOD_Money 就需要最后json文件内有 CANDY_MOD_Money 的遗物信息
     */
    public static final String ID = RelicConfig.RELIC_PRE_NAME + "Money";
    /**
     * 日志对象
     */
    private static final Logger log = LogManager.getLogger(Money.class);
    
    /**
     * 构造函数
     */
    public Money() {
        //图片使用内置的 使用破碎王冠 的图标
        //使用内置图标就不需要导入了 想自定义可以抄其他的mod或者看教程
        super(ID, "crown.png", RelicTier.COMMON, LandingSound.CLINK);
    }
    
    /**
     * 在战斗开始时触发
     */
    @Override
    public void atBattleStart() {
        super.atBattleStart();
        //定义一个金钱变量为100
        int money = 100;
        //日志打印增加了100金币
        log.info(">>>回合开始时获得{}金币。<<<", money);
        //给人物增加100金币
        AbstractDungeon.player.gainGold(money);
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
