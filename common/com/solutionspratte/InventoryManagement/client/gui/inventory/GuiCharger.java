package com.solutionspratte.InventoryManagement.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.solutionspratte.InventoryManagement.inventory.ContainerCharger;
import com.solutionspratte.InventoryManagement.lib.Strings;
import com.solutionspratte.InventoryManagement.lib.Textures;
import com.solutionspratte.InventoryManagement.tileentity.TileCharger;

public class GuiCharger extends GuiContainer {

    private TileCharger tileCharger;
    public GuiCharger(InventoryPlayer inventoryPlayer, TileCharger charger){
        super(new ContainerCharger(inventoryPlayer, charger));

        tileCharger = charger;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        String containerName = tileCharger.isInvNameLocalized() ? tileCharger.getInvName() : StatCollector.translateToLocal(tileCharger.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 8, ySize - 93, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_CHARGER);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
    
}
