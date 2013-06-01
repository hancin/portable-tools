package com.solutionspratte.InventoryManagement;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;

import com.solutionspratte.InventoryManagement.block.ModBlocks;
import com.solutionspratte.InventoryManagement.core.util.LogHelper;
import com.solutionspratte.InventoryManagement.core.handlers.VersionCheckTickHandler;
import com.solutionspratte.InventoryManagement.configuration.ConfigurationHandler;
import com.solutionspratte.InventoryManagement.core.util.VersionHelper;
import com.solutionspratte.InventoryManagement.core.handlers.HeartGoldTickHandler;
import com.solutionspratte.InventoryManagement.core.handlers.LocalizationHandler;
import com.solutionspratte.InventoryManagement.item.ModItems;
import com.solutionspratte.InventoryManagement.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * 
 * @author David Larochelle-Pratte
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER)
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class InventoryManagement {
    
    public static CreativeTabs tabsCSIM = CreativeTabs.tabMisc;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {

        // Initialize the log helper
        LogHelper.init();
        
        LocalizationHandler.loadLanguages();

        // Initialize the configuration
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));
        

        // Conduct the version check and log the result
        VersionHelper.execute();

        TickRegistry.registerTickHandler(new HeartGoldTickHandler(), Side.SERVER);
        
        // Initialize the Version Check Tick Handler (Client only)
        TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
        
        ModItems.init();
        
        ModBlocks.init();
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
}
