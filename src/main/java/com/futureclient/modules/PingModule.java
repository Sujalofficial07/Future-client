package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.gui.GuiElements;
import net.minecraft.client.Minecraft;
import net.minecraft.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import org.lwjgl.input.Keyboard;

/**
 * Displays last known ping using NetHandlerPlayClient.
 */
public class PingModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public PingModule() {
        super("PingDisplay", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onRender() {
        try {
            NetHandlerPlayClient nh = mc.getNetHandler();
            if (nh == null) return;
            int ping = nh.getPlayerInfo(mc.thePlayer.getUniqueID()) != null ?
                    nh.getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() : 0;
            GuiElements.drawStringScaled("Ping: " + ping + "ms", 6, 80, 1.0f, 0xFFFFFF);
        } catch (Exception e) {
            // safe: network internals vary, keep silent
        }
    }
}