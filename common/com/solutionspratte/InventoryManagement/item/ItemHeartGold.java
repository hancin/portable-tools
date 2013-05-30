package com.solutionspratte.InventoryManagement.item;

import com.solutionspratte.InventoryManagement.lib.Reference;
import com.solutionspratte.InventoryManagement.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemHeartGold extends Item{

    public ItemHeartGold(int id) {
        super(id);
        this.setUnlocalizedName(Strings.HEART_GOLD_NAME);
        this.setNoRepair();
        this.setCreativeTab(CreativeTabs.tabMisc);
        
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
    
}
