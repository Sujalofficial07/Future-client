package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.network.PlayerListEntry;

/**
 * Displays player ping
 */
public class PingModule extends Module {
    
    private int x = 5;
    private int y = 45;
    
    public PingModule() {
        super("Ping", "Display connection latency", Category.HUD);
        setEnabled(true);
    }
    
    @Override
    public void onRender(float partialTicks) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        
        PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        int ping = entry != null ? entry.getLatency() : 0;
        
        String text = "Ping: " + ping + "ms";
        int color = getPingColor(ping);
        RenderUtil.drawString(text, x, y, color);
    }
    
    /**
     * Get color based on ping value
     */
    private int getPingColor(int ping) {
        if (ping < 50) {
            return 0xFF55FF55; // Green
        } else if (ping < 100) {
            return 0xFFFFFF55; // Yellow
        } else if (ping < 200) {
            return 0xFFFFAA55; // Orange
        } else {
            return 0xFFFF5555; // Red
        }
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
