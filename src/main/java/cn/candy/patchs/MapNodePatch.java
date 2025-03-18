package cn.candy.patchs;

;
import cn.candy.relic.kuangwang;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.map.RoomTypeAssigner;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import javassist.CtBehavior;
import jdk.internal.classfile.components.ClassPrinter;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class MapNodePatch {

/*

    @SpirePatch(
            clz = RoomTypeAssigner.class,
            method = "assignRowAsRoomType"
    )
    public static class MapPatch {
        @SpireInsertPatch(
                locator = PreLocator.class,localvars = {"n"}
        )
        public static void MapPatchInsert(ArrayList<MapRoomNode> row, Class<? extends AbstractRoom> c,  MapRoomNode n) {
            if(AbstractDungeon.player.hasRelic(kuangwang.ID)&&c.getName().equals(RestRoom.class.getName())){
            n.setRoom(new MonsterRoomElite());
            n.hasEmeraldKey=true;
            }


        }
    }
    /*
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "generateRoomTypes"
    )
    public static class generateRoomTypesPatch {
        @SpireInsertPatch(
                rloc=37
        )
        public static void MapPatchInsert(ArrayList<AbstractRoom> roomList, int availableRoomCount) {
            if(AbstractDungeon.player.hasRelic(kuangwang.ID)){
                //把最新加入的room替换掉
              roomList.set(roomList.size()-1,new MonsterRoomElite());
            }


        }
    }*/
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "nextRoomTransition",
            paramtypez = {SaveFile.class}
    )
    public static class assignRoomsToNodesPatch {
        @SpireInsertPatch(
                rloc=0
        )
        public static void MapPatchInsert(AbstractDungeon __instance, SaveFile saveFile) {
            if(player.hasRelic(kuangwang.ID)){
              if (nextRoom.room instanceof  RestRoom){
                  nextRoom.room =new MonsterRoomElite();
                  nextRoom.hasEmeraldKey=true;
                  scene.nextRoom(currMapNode.room);
                  rs=RenderScene.NORMAL;
              }
            }


        }

    }
    private static class PreLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(MapRoomNode.class, "setRoom");
            int[] positions= LineFinder.findInOrder(ctBehavior, (Matcher)matcher);
            for (int i = 0; i < positions.length; i++) {
                positions[i]++;
            }
            return LineFinder.findInOrder(ctBehavior, (Matcher)matcher);
        }
    }
}



