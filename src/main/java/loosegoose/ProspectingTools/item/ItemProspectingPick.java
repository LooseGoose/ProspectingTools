package loosegoose.ProspectingTools.item;

import loosegoose.ProspectingTools.misc.LibCommon;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemProspectingPick extends ItemMod {

    HashMap<String, ProspectResult> results = new HashMap();
    Random rand = null;

    public ItemProspectingPick() {
        super();
        setMaxStackSize(1);
        setMaxDamage(250);
        setUnlocalizedName(LibCommon.PROSPECTINGPICK);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {
        Block blockProspected = world.getBlock(x, y, z);
        int prospectedMetadata = world.getBlockMetadata(x, y, z);
        ItemStack blockStack = new ItemStack(blockProspected, 1, prospectedMetadata);
        this.rand = new Random(x * z + y);

        if (!world.isRemote) {
            for (int id : OreDictionary.getOreIDs(blockStack)) {
                String blockName = OreDictionary.getOreName(id);
                if (blockName.matches("^ore[A-Z].+")) {
                    FoundOre(player, blockStack);
                    return true;
                }
            }
            this.results.clear();

            for (int i = -12; i < 12; i++) {
                for (int j = -12; j < 12; j++) {
                    for (int k = -12; k < 12; k++) {
                        blockProspected = world.getBlock((x+i), (y+j), (z+k));
                        prospectedMetadata = world.getBlockMetadata((x+i), (y+j), (z+k));
                        blockStack = new ItemStack(blockProspected, 1, prospectedMetadata);
                        ItemStack ore = null;
                        for (int id : OreDictionary.getOreIDs(blockStack)) {
                            String blockName = OreDictionary.getOreName(id);
                            if (blockName.matches("^ore[A-Z].+")) {
                                ore = new ItemStack(blockProspected, 1, prospectedMetadata);
                            }
                        }
                        if (ore != null) {
                            String oreName = ore.getDisplayName();
                            if (this.results.containsKey(oreName)) {
                                ((ProspectResult)this.results.get(oreName)).count += 1;
                            } else {
                                this.results.put(oreName, new ProspectResult(ore, 1));
                            }
                        }
                        ore = null;
                    }
                }
            }
            if (this.results.isEmpty()) {
                FoundNothing(player);
            } else {
                FoundMultipleOres(player);
            }
        }

        this.results.clear();
        this.rand = null;

        stack.damageItem(1, player);
        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            player.destroyCurrentEquippedItem();
        }
        return true;
    }

    private void FoundNothing(EntityPlayer player) {
        player.addChatMessage(new ChatComponentTranslation("gui.ProspectingPick.FoundNothing", new Object[0]));
    }

    private void FoundOre(EntityPlayer player, ItemStack ore) {
        String oreName = ore.getUnlocalizedName() + ".name";
        player.addChatMessage(new ChatComponentTranslation("gui.ProspectingPick.Found", new Object[0]).appendText(" ").appendSibling(new ChatComponentTranslation(oreName, new Object[0])));
    }

    private void FoundMultipleOres(EntityPlayer player) {
        int index = this.rand.nextInt(this.results.size());
        ProspectResult result = ((ProspectResult[])this.results.values().toArray(new ProspectResult[0]))[index];
        String oreName = result.stack.getUnlocalizedName() + ".name";
        String quantity;

        if (result.count <= 8) {
            quantity = "gui.ProspectingPick.FoundTraces";
        } else if (result.count <= 24) {
            quantity = "gui.ProspectingPick.FoundSmall";
        } else if (result.count <= 48) {
            quantity = "gui.ProspectingPick.FoundMedium";
        } else if (result.count <= 80) {
            quantity = "gui.ProspectingPick.FoundLarge";
        } else {
            quantity = "gui.ProspectingPick.FoundHuge";
        }
        player.addChatMessage(new ChatComponentTranslation(quantity, new Object[0]).appendText(" ").appendSibling(new ChatComponentTranslation(oreName, new Object[0])));

        oreName = null;
        result = null;
    }

    private class ProspectResult {
        public ItemStack stack;
        public int count;

        public ProspectResult(ItemStack par1ItemStack, int par2Count) {
            this.stack = par1ItemStack;
            this.count = par2Count;
        }
    }
}