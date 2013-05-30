package com.solutionspratte.InventoryManagement.item;

import net.minecraft.creativetab.CreativeTabs;

import com.solutionspratte.InventoryManagement.lib.Strings;


public class ItemHeartGold extends ItemBase{

    public ItemHeartGold(int id) {
        super(id);
        this.setUnlocalizedName(Strings.HEART_GOLD_NAME);
        this.setCreativeTab(CreativeTabs.tabMisc);
        
    }
    
}
