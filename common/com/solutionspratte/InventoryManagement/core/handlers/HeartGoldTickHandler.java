package com.solutionspratte.InventoryManagement.core.handlers;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

import com.solutionspratte.InventoryManagement.core.util.NBTHelper;
import com.solutionspratte.InventoryManagement.lib.ItemIds;
import com.solutionspratte.InventoryManagement.lib.Reference;
import com.solutionspratte.InventoryManagement.lib.Strings;

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
                            int chargeLevel = NBTHelper.getInt(itemStack, Strings.NBT_HOG_CHARGE_LEVEL);
                            boolean willOverflow = chargeLevel + 5 >= 1000;
                            if(!willOverflow)
                                NBTHelper.setInteger(itemStack, Strings.NBT_HOG_CHARGE_LEVEL, chargeLevel+5);
                            else
                            {
                                NBTHelper.setInteger(itemStack, Strings.NBT_HOG_CHARGE_LEVEL, 0);
                                
                                //player.
                                Random q = new Random();
                                Potion pot = null;
                                while(pot == null)
                                    pot = Potion.potionTypes[q.nextInt(Potion.potionTypes.length)];
                                if(pot.id == Potion.harm.id)
                                    break;
                                player.addPotionEffect(new PotionEffect(pot.id, 2*20, 1));
                                FoodStats food = player.getFoodStats();
                                food.addStats(1, 0.6F);
                                //player.addPotionEffect(new PotionEffect(Potion.jump.id, 5*20, 10));
                               // player.addPotionEffect(new PotionEffect(Potion..id, 5*20, 10));
                                
                                if(player.username.toLowerCase().contains("nosrac49"))
                                    player.attackEntityFrom(DamageSource.generic, 3);
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
