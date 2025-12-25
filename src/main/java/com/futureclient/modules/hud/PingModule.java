package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.network.NetworkPlayerInfo;

public class PingModule extends Module {
    private int x = 5;
    private int y = 45;
    
    public PingModule() {
        super("Ping", "Display connection latency", Category.HUD);
        setEnabled(true);
    }
    
    @Override
    public void onRender(float partialTicks) {
        if (mc.thePlayer == null || mc.getNetHandler() == null) return;
        
        NetworkPlayerInfo info = mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID());
        int ping = info != null ? info.getResponseTime() : 0;
        
        String text = "Ping: " + ping + "ms";
        int color = getPingColor(ping);
        RenderUtil.drawString(text, x, y, color);
    }
    
    private int getPingColor(int ping) {
        if (ping < 50) return 0xFF55FF55;
        else if (ping < 100) return 0xFFFFFF55;
        else if (ping < 200) return 0xFFFFAA55;
        else return 0xFFFF5555;
    }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
