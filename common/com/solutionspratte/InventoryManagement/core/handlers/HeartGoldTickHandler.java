package com.solutionspratte.InventoryManagement.core.handlers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.DamageSource;

import com.solutionspratte.InventoryManagement.lib.ItemIds;
import com.solutionspratte.InventoryManagement.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class HeartGoldTickHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        
        for (TickType tickType : type) {
            if (tickType == TickType.SERVER) {
                for(Object playerObject : FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList)
                {
                    EntityPlayer player = (EntityPlayer)playerObject;
                    for (ItemStack itemStack : player.inventory.mainInventory) {
                        if(itemStack == null)
                            continue;
                        if(itemStack.itemID == ItemIds.HEART_GOLD){
                            boolean willOverflow = itemStack.getItemDamage() + 5 >= itemStack.getMaxDamage();
                            if(!willOverflow)
                                itemStack.damageItem(5, player);
                            else
                            {
                                itemStack.setItemDamage(0);
                                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 5*20, 10));
                                //player.addPotionEffect(new PotionEffect(Potion.jump.id, 5*20, 10));
                               // player.addPotionEffect(new PotionEffect(Potion..id, 5*20, 10));

                                //if(player.username.toLowerCase() == "dpratte")
                                //    player.attackEntityFrom(DamageSource.wither, 8);
                            }
                            break;
                        }
                    }
                }
                
            }
        }
        
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

}
