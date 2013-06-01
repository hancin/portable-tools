package com.solutionspratte.InventoryManagement.tileentity;

import java.util.logging.Level;

import com.solutionspratte.InventoryManagement.core.util.LogHelper;

import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;

public class TileCharger extends TileBase implements IPowerReceptor  {


    IPowerProvider powerProvider;
    
    public TileCharger()
    {
        powerProvider = PowerFramework.currentFramework.createPowerProvider();
        powerProvider.configure(20, 25, 50, 25, 1000000); 
    }
    
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

}
