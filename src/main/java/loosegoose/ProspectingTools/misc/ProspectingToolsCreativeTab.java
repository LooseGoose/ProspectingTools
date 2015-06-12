package loosegoose.ProspectingTools.misc;

import loosegoose.ProspectingTools.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ProspectingToolsCreativeTab extends CreativeTabs {

    public static ProspectingToolsCreativeTab INSTANCE = new ProspectingToolsCreativeTab();

    public ProspectingToolsCreativeTab() {
        super(LibCommon.MOD_ID);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ModItems.prospectingpick);
    }

    @Override
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }

}
