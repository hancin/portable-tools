package com.solutionspratte.InventoryManagement.tileentity;

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
    public void doWork() {
         powerProvider.useEnergy(25, 25, true);
    }

    @Override
    public int powerRequest(ForgeDirection from) {
        return 100;
    }

}
