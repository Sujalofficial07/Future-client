package com.futureclient.module.impl.hud;

import com.futureclient.event.Event;
import com.futureclient.event.impl.EventRender2D;
import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class InfoHUD extends Module {
    public InfoHUD() {
        super("InfoHUD", "Displays FPS, Ping, and Coords", Category.HUD);
        setEnabled(true);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender2D) {
            if (mc.options.debugEnabled) return;
            
            // Accessing FPS via the instance 'mc' inherited from Module class
            String fps = "FPS: " + mc.getCurrentFps();
            
            String xyz = "XYZ: 0 0 0";
            if (mc.player != null) {
                xyz = String.format("XYZ: %.1f %.1f %.1f", mc.player.x, mc.player.y, mc.player.z);
            }
            
            mc.textRenderer.drawWithShadow(fps, 2, 2, -1);
            mc.textRenderer.drawWithShadow(xyz, 2, 12, -1);
        }
    }
}
