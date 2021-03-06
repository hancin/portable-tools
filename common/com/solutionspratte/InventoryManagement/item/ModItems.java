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
        
        ItemStack setupStack = new ItemStack(heartGold);
        setupStack.setItemDamage(heartGold.getMaxDamage());
        GameRegistry.addRecipe(setupStack, new Object[] { "drd", "rgr", "drd", Character.valueOf('d'), Item.diamond, Character.valueOf('g'), Block.blockGold, Character.valueOf('r'), Item.redstone});

    }
}
