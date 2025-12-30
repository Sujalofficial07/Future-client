package com.futureclient.gui.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class HUDManager {
    private final List<HUDComponent> components = new ArrayList<>();
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public HUDManager() {
        // Register HUD elements
        components.add(new HUDComponent("FPS", 5, 5) {
            @Override
            public void render(DrawContext context, float delta) {
                context.drawText(mc.textRenderer, "[FPS " + mc.getCurrentFps() + "]", x, y, -1, true);
            }
        });
        
        components.add(new HUDComponent("Keystrokes", 5, 20) {
            @Override
            public void render(DrawContext context, float delta) {
                 // Simplified W A S D visual
                 String keys = (mc.options.forwardKey.isPressed() ? "W " : "- ") + 
                               (mc.options.leftKey.isPressed() ? "A " : "- ") + 
                               (mc.options.backKey.isPressed() ? "S " : "- ") + 
                               (mc.options.rightKey.isPressed() ? "D" : "-");
                 context.drawText(mc.textRenderer, keys, x, y, 0xFFFFFF, true);
            }
        });
    }

    public void render(DrawContext context, float tickDelta) {
        if (mc.options.hudHidden) return;
        for (HUDComponent c : components) {
            if (c.isEnabled()) c.render(context, tickDelta);
        }
    }
}
