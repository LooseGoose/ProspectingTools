package loosegoose.ProspectingTools.item;

import net.minecraft.item.Item;

public class ModItems {

    public static Item prospectingpick;
    public static Item oremeter;

    public static void init() {
        prospectingpick = new ItemProspectingPick();
        oremeter = new ItemOreMeter();
    }

}