package loosegoose.ProspectingTools.misc;

import loosegoose.ProspectingTools.item.ModItems;
// import net.minecraft.init.Items;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;

public class ModCraftingRecipes {

    public static IRecipe recipeProspectingPick;
    public static IRecipe recipeOreMeter;

    public static void init() {
      ///*
        //Prospecting Pick Recipe
        addOreDictRecipe(new ItemStack(ModItems.prospectingpick),
                "III", " SI", " S ",
                'I', "ingotIron",
                'S', "stickWood"
        );
        recipeProspectingPick = getLatestAddedRecipe();
      //*/

      /*
        //Ore Meter Recipe
        addOreDictRecipe(new ItemStack(ModItems.oremeter),
                "IGI", "RER", "III",
                'G', "ingotGold",
                'I', "ingotIron",
                'E', Items.ender_pearl,
                'R', "dustRedstone"
        );
        recipeOreMeter = getLatestAddedRecipe();
      //*/
    }

    public static void addOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
    }

    public static IRecipe getLatestAddedRecipe() {
        List<IRecipe> list = CraftingManager.getInstance().getRecipeList();
        return list.get(list.size() - 1);
    }
}