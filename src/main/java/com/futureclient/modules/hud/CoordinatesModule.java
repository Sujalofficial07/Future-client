package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;

public class CoordinatesModule extends Module {
    private int x = 5;
    private int y = 25;
    
    public CoordinatesModule() {
        super("Coordinates", "Display player position", Category.HUD);
        setEnabled(true);
    }
    
    @Override
    public void onRender(float partialTicks) {
        if (mc.thePlayer == null) return;
        
        EntityPlayer player = mc.thePlayer;
        int posX = (int) player.posX;
        int posY = (int) player.posY;
        int posZ = (int) player.posZ;
        
        String text = String.format("XYZ: %d, %d, %d", posX, posY, posZ);
        RenderUtil.drawString(text, x, y, 0xFFFFFFFF);
    }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
