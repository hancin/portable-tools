package com.solutionspratte.InventoryManagement.tileentity;

import java.util.logging.Level;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;

import com.solutionspratte.InventoryManagement.core.util.LogHelper;
import com.solutionspratte.InventoryManagement.lib.Strings;

public class TileCharger extends TileBase implements IPowerReceptor, IInventory  {

    private ItemStack[] inventory;
    private IPowerProvider powerProvider;
    
    public static final int INVENTORY_BATTERY_SLOT = 0;
    public static final int INVENTORY_SIZE = 1;
    
    public TileCharger()
    {
        super();
        inventory = new ItemStack[INVENTORY_SIZE];
        
        powerProvider = PowerFramework.currentFramework.createPowerProvider();
        powerProvider.configure(20, 25, 50, 25, 1000000); 
    }
    
    //#region IPowerReceptor
    
    @Override
    public void setPowerProvider(IPowerProvider provider) {
        this.powerProvider = provider;
    }

    @Override
    public IPowerProvider getPowerProvider() {
        return powerProvider;
    }
    
    @Override
    public void updateEntity() {
        if(powerProvider.getEnergyStored() >= 25)
        {
            doWork();
        }
    };

    @Override
    public void doWork() {
        if(worldObj.isRemote)
            return;
        
        LogHelper.log(Level.INFO, "I'm using "+powerProvider.useEnergy(25, 25, true)+" MJ."); 
    }

    @Override
    public int powerRequest(ForgeDirection from) {
        return powerProvider.getMaxEnergyReceived();
    }

    //#endregion
    //#region IInventory
    @Override
    public int getSizeInventory() {
        // TODO Auto-generated method stub
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        // TODO Auto-generated method stub
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        ItemStack itemStack = getStackInSlot(slot);
        if(itemStack != null)
        {
            if(itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemStack = itemStack.splitStack(amount);
                if(itemStack.stackSize == 0){
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        
        if(inventory[slot] != null) {
            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            return itemStack;
        }
       
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        
        inventory[slot] = itemStack;
        
        if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        
        this.onInventoryChanged();
    }

    @Override
    public String getInvName() {
        // TODO Auto-generated method stub
        return this.hasCustomName() ? this.getCustomName() : Strings.CONTAINER_CHARGER_NAME;
    }

    @Override
    public boolean isInvNameLocalized() {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }
    
    //#endregion
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);

    }

}
