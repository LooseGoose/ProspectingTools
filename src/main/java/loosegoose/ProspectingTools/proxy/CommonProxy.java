package loosegoose.ProspectingTools.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import loosegoose.ProspectingTools.misc.ModCraftingRecipes;
import loosegoose.ProspectingTools.item.ModItems;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        ModCraftingRecipes.init();
    }

}
