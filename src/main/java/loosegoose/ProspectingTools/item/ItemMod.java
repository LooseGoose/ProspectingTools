package loosegoose.ProspectingTools.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import loosegoose.ProspectingTools.misc.ProspectingToolsCreativeTab;
import loosegoose.ProspectingTools.misc.LibCommon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMod extends Item {

    public ItemMod() {
        super();
        setCreativeTab(ProspectingToolsCreativeTab.INSTANCE);
    }

    public ItemMod(boolean NoCreativeTab) {
        super();
    }

    @Override
    public Item setUnlocalizedName(String Parameter1String) {
        GameRegistry.registerItem(this, Parameter1String);
        return super.setUnlocalizedName(Parameter1String);
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack Parameter1ItemStack) {
        return super.getUnlocalizedNameInefficiently(Parameter1ItemStack).replaceAll("item\\.", ("item." + LibCommon.MOD_PREFIX));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister Parameter1IIconRegister) {
        itemIcon = Parameter1IIconRegister.registerIcon(LibCommon.MOD_PREFIX + this.getUnlocalizedName().replaceAll("item\\.", ""));
    }

}
