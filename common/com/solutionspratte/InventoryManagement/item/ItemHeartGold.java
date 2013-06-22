package com.solutionspratte.InventoryManagement.item;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.InventoryManagement;
import com.solutionspratte.InventoryManagement.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemHeartGold extends ItemBase{

    public static final int UNITS_USED = 16;
    public static final int UNITS_USED_SNEAKING = 400;
    public static final int TOTAL_USES = 100;
    public ItemHeartGold(int id) {
        super(id);
        this.setUnlocalizedName(Strings.HEART_GOLD_NAME);
        this.setCreativeTab(InventoryManagement.tabsCSIM);
        setNoRepair();
        setMaxDamage(UNITS_USED * TOTAL_USES);
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
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        
        if(world.isRemote)
            return itemStack;

        int available = getMaxDamage() - itemStack.getItemDamage();
        
        if(entityPlayer.isSneaking() && available >= UNITS_USED_SNEAKING && entityPlayer.canEat(true))
        {
            itemStack.setItemDamage(itemStack.getItemDamage() + UNITS_USED_SNEAKING);
            entityPlayer.getFoodStats().addStats((ItemFood)Item.porkCooked);
            entityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 160, 2));
            entityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
            entityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
        }
        else if(!entityPlayer.isSneaking() && available >= UNITS_USED && entityPlayer.canEat(true))
        {
            entityPlayer.getFoodStats().addStats((ItemFood)Item.appleGold);
            
            itemStack.setItemDamage(itemStack.getItemDamage() + UNITS_USED);
            
        }
        
        return itemStack;
    };
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ) {
        int currentBlock = world.getBlockId(x, y, z);

        if(currentBlock == Block.cobblestone.blockID)
        {
            if(world.isRemote)
                return true;
            
            world.setBlock(x, y, z, Block.stone.blockID);
            
        }else if(currentBlock == Block.stairsCobblestone.blockID)
        {
            if(world.isRemote)
                return true;
            
            world.setBlock(x, y, z, Block.stairsStoneBrick.blockID);
            
        }else if(currentBlock == Block.dirt.blockID)
        {
            if(world.isRemote)
                return true;
            
            world.setBlock(x, y, z, Block.grass.blockID);
            
        }
        
        
        return false;
    }
        
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.epic;
    }
}
