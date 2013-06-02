package com.solutionspratte.InventoryManagement.block;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.InventoryManagement;
import com.solutionspratte.InventoryManagement.lib.GuiIds;
import com.solutionspratte.InventoryManagement.lib.Strings;
import com.solutionspratte.InventoryManagement.tileentity.TileCharger;

public class BlockCharger extends BlockBase {
    private Random rand = new Random();
    
    public BlockCharger(int id) {
        super(id, Material.rock);
        setUnlocalizedName(Strings.CHARGER_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCharger();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return false;
        
        if(world.isRemote)
            return true;
        
        TileCharger tileCharger = (TileCharger) world.getBlockTileEntity(x, y, z);
        
        if(tileCharger == null)
            return true;
        
        player.openGui(InventoryManagement.instance, GuiIds.CHARGER, world, x, y, z);
        
        return true;
    }
    
    private void dropInventory(World world, int x, int y, int z) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
