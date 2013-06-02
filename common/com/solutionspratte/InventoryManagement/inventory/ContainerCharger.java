package com.solutionspratte.InventoryManagement.inventory;

import com.solutionspratte.InventoryManagement.tileentity.TileCharger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCharger extends Container {

    private TileCharger tileCharger;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;
    
    
    public ContainerCharger(InventoryPlayer inventoryPlayer, TileCharger charger) {
        this.tileCharger = charger;
        tileCharger.openChest();

        this.addSlotToContainer(new Slot(tileCharger, TileCharger.INVENTORY_BATTERY_SLOT, 80, 22));
        
     // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 58 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 116));
        }
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < TileCharger.INVENTORY_SIZE) {
                
                if (!this.mergeItemStack(slotItemStack, 1, inventorySlots.size(), true)) {
                    return null;
                }
            }
            else {
                if (!this.mergeItemStack(slotItemStack, 0, TileCharger.INVENTORY_SIZE, false)) {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
        
    }
    
    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onCraftGuiClosed(EntityPlayer entityPlayer) {

        super.onCraftGuiClosed(entityPlayer);
        tileCharger.closeChest();
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
    
}
