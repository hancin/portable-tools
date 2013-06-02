package com.solutionspratte.InventoryManagement.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.client.gui.inventory.GuiCharger;
import com.solutionspratte.InventoryManagement.inventory.ContainerCharger;
import com.solutionspratte.InventoryManagement.lib.GuiIds;
import com.solutionspratte.InventoryManagement.lib.Strings;
import com.solutionspratte.InventoryManagement.tileentity.TileCharger;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

    public void registerTileEntities() {

        GameRegistry.registerTileEntity(TileCharger.class, Strings.TE_CHARGER_NAME);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == GuiIds.CHARGER)
        {
            TileCharger tileCharger = (TileCharger) world.getBlockTileEntity(x, y, z);
            return new ContainerCharger(player.inventory, tileCharger);
            
        }
        /*if (ID == GuiIds.PORTABLE_CRAFTING)
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION)
            return new ContainerPortableTransmutation();
        else if (ID == GuiIds.CALCINATOR) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new ContainerCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST) {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG)
            // TODO Alchemical Bag inventory work is incomplete
            return new ContainerAlchemicalBag(player.inventory);
        else if (ID == GuiIds.ALUDEL) {
            TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);
            return new ContainerAludel(player.inventory, tileAludel);
        }
        else if (ID == GuiIds.GLASS_BELL) {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y, z);
            return new ContainerGlassBell(player.inventory, tileGlassBell);
        }
        */
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == GuiIds.CHARGER)
        {
            TileCharger tileCharger = (TileCharger) world.getBlockTileEntity(x, y, z);
            return new GuiCharger(player.inventory, tileCharger);
            
        }
        /*if (ID == GuiIds.PORTABLE_CRAFTING)
            return new GuiPortableCrafting(player, world, x, y, z);
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION)
            return new GuiPortableTransmutation(null);
        else if (ID == GuiIds.CALCINATOR) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new GuiCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST) {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG)
            // TODO Alchemical Bag inventory work is incomplete
            return new GuiAlchemicalBag(player.inventory);
        else if (ID == GuiIds.ALUDEL) {
            TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);
            return new GuiAludel(player.inventory, tileAludel);
        }
        else if (ID == GuiIds.GLASS_BELL) {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y, z);
            return new GuiGlassBell(player.inventory, tileGlassBell);
        }
        */
        return null;
    }

}
