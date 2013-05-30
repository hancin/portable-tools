package com.solutionspratte.InventoryManagement;

import com.solutionspratte.InventoryManagement.item.ModItems;
import com.solutionspratte.InventoryManagement.lib.Localizations;
import com.solutionspratte.InventoryManagement.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * @author David Larochelle-Pratte
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class InventoryManagement {
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        String file = Localizations.localeFiles[0];
        LanguageRegistry.instance().loadLocalization(file, "en_US", true);
        
        ModItems.init();
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
