package com.solutionspratte.InventoryManagement.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemHeartGold extends ItemBase{

    public ItemHeartGold(int id) {
        super(id);
        this.setUnlocalizedName(Strings.HEART_GOLD_NAME);
        this.setCreativeTab(CreativeTabs.tabMisc);
        setNoRepair();
        
        
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List informationLines, boolean par4)
    {
        informationLines.add("Alex, what have you done?");
    }
    
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ) {
        int currentBlock = world.getBlockId(x, y, z);
        
        if(currentBlock == Block.cobblestone.blockID)
        {
            if(world.isRemote)
                return true;
            
            world.setBlock(x, y, z, Block.stone.blockID);
            
        }else if(currentBlock == Block.stone.blockID)
        {
            if(world.isRemote)
                return true;
            
            world.setBlock(x, y, z, Block.cobblestone.blockID);
            
            world.playAuxSFXAtEntity((EntityPlayer)null, 1017, x, y, z, 0);
        }
        return false;
    }
        
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.epic;
    }
}
