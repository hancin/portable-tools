package com.solutionspratte.InventoryManagement.block;
import buildcraft.api.power.IPowerReceptor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.solutionspratte.InventoryManagement.lib.Strings;
import com.solutionspratte.InventoryManagement.tileentity.TileCharger;

public class BlockCharger extends BlockBase {
    public BlockCharger(int id) {
        super(id, Material.rock);
        setUnlocalizedName(Strings.CHARGER_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return new TileCharger();
    }
}
