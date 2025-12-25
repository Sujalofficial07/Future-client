package com.futureclient.module.impl.hud;

import com.futureclient.event.Event;
import com.futureclient.event.impl.EventRender2D;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.gui.DrawableHelper;
import org.lwjgl.input.Keyboard;

import java.awt.Color;

public class Keystrokes extends Module {
    public Keystrokes() {
        super("Keystrokes", "Shows keys", Category.HUD);
        setEnabled(true);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender2D) {
            int x = 2;
            int y = 50;
            
            // W
            drawKey(x + 22, y, Keyboard.KEY_W, "W");
            // A
            drawKey(x, y + 22, Keyboard.KEY_A, "A");
            // S
            drawKey(x + 22, y + 22, Keyboard.KEY_S, "S");
            // D
            drawKey(x + 44, y + 22, Keyboard.KEY_D, "D");
        }
    }

    private void drawKey(int x, int y, int key, String name) {
        boolean pressed = Keyboard.isKeyDown(key);
        int color = pressed ? new Color(255, 255, 255, 100).getRGB() : new Color(0, 0, 0, 100).getRGB();
        
        // Use DrawableHelper.fill for rendering rectangles
        DrawableHelper.fill(x, y, x + 20, y + 20, color);
        mc.textRenderer.drawWithShadow(name, x + 8, y + 6, -1);
    }
}
