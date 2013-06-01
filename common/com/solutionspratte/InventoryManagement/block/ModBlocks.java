package com.solutionspratte.InventoryManagement.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.solutionspratte.InventoryManagement.lib.BlockIds;
import com.solutionspratte.InventoryManagement.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Equivalent-Exchange-3
 * 
 * ModBlocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModBlocks {

    /* Mod block instances */
    public static Block charger;
   

    public static void init() {

        charger = new BlockCharger(BlockIds.CHARGER);
        
        GameRegistry.registerBlock(charger, Strings.CHARGER_NAME);
        
        initBlockRecipes();
    }

    private static void initBlockRecipes() {
        
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(charger), new Object[]{"ggg","sls","ggg", Character.valueOf('g'), Item.ingotGold, Character.valueOf('s'), "ingotSilver", Character.valueOf('l'), "ingotLead"}));
        //GameRegistry.addRecipe(new ItemStack(glassBell), new Object[] { "iii", "i i", "i i", Character.valueOf('i'), Block.glass });
        //GameRegistry.addRecipe(new ItemStack(aludelBase), new Object[] { "iii", "sis", "iii", Character.valueOf('i'), Item.ingotIron, Character.valueOf('s'), Block.stone });
    }
}
