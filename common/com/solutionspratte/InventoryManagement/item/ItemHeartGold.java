package com.solutionspratte.InventoryManagement.item;

import java.util.HashMap;
import java.util.List;

import thermalexpansion.api.item.IChargeableItem;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.InventoryManagement;
import com.solutionspratte.InventoryManagement.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemHeartGold extends ItemBase implements IChargeableItem{

    public static final int UNITS_USED = 16;
    public static final int UNITS_USED_SNEAKING = 528;
    public static final int TOTAL_USES = 100;
    public static final int UNIT_MJ = 200;
    
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

    @Override
    public float receiveEnergy(ItemStack theItem, float energy,
            boolean doReceive) {
        
        float inTransit = 0;
        if(theItem.stackTagCompound != null)
            inTransit = theItem.stackTagCompound.getFloat("inTransitEnergyStored");
        
        float storableEnergy = Math.max(theItem.getItemDamage() * UNIT_MJ - inTransit, 0);
        
        float providedEnergy = Math.min(energy, storableEnergy);
        
        if(!doReceive)
            return providedEnergy;
        
        
        inTransit += providedEnergy;
        
        int usableUnits = (int)Math.floor(inTransit / UNIT_MJ);
        
        
        theItem.setItemDamage(Math.max(theItem.getItemDamage() - usableUnits, 0));
        
        float newTransit = inTransit % UNIT_MJ;
        

        if(newTransit != inTransit){
            if(theItem.stackTagCompound == null)
                theItem.stackTagCompound = new NBTTagCompound();
            
            theItem.stackTagCompound.setFloat("inTransitEnergyStored", newTransit);
        }
        
        
        return providedEnergy;
    }

    @Override
    public float transferEnergy(ItemStack theItem, float energy,
            boolean doTransfer) {

        //Once its in, it can't get out.
        return 0;
    }

    @Override
    public float getEnergyStored(ItemStack theItem) {
        int max = theItem.getMaxDamage() * UNIT_MJ;
        float inTransit = 0;
        if(theItem.stackTagCompound != null)
            inTransit = theItem.stackTagCompound.getFloat("inTransitEnergyStored");
        return Math.min(max - theItem.getItemDamage() * UNIT_MJ + inTransit, max);
    }

    @Override
    public float getMaxEnergyStored(ItemStack theItem) {
        return theItem.getMaxDamage() * UNIT_MJ;
    }
}
