package com.solutionspratte.InventoryManagement.item;

import com.solutionspratte.InventoryManagement.lib.ItemIds;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ModItems {
    
    public static Item heartGold;
    
    public static void init()
    {
        heartGold = new ItemHeartGold(ItemIds.HEART_GOLD);
        
        GameRegistry.addRecipe(new ItemStack(heartGold), new Object[] { "w w", " s ", "w w", Character.valueOf('w'), Item.stick, Character.valueOf('s'), Block.cobblestone});

    }
}
