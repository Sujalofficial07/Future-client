package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.gui.GuiElements;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

/**
 * Keystrokes module: draws WASD and mouse buttons.
 * Uses minimal allocations - caching text and small rendering.
 */
public class KeystrokesModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public KeystrokesModule() {
        super("Keystrokes", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onRender() {
        int x = 6;
        int y = 6;
        float scale = 1.0f;
        int color = 0xFFFFFFFF;
        // WASD
        boolean w = mc.gameSettings.keyBindForward.isKeyDown();
        boolean a = mc.gameSettings.keyBindLeft.isKeyDown();
        boolean s = mc.gameSettings.keyBindBack.isKeyDown();
        boolean d = mc.gameSettings.keyBindRight.isKeyDown();
        int bgOn = 0xFF2E2E2E;
        int bgOff = 0xFF1A1A1A;

        drawKey("W", x + 18, y, w ? bgOn : bgOff, color, scale);
        drawKey("A", x, y + 18, a ? bgOn : bgOff, color, scale);
        drawKey("S", x + 18, y + 18, s ? bgOn : bgOff, color, scale);
        drawKey("D", x + 36, y + 18, d ? bgOn : bgOff, color, scale);

        // Mouse L/R
        boolean l = Mouse.isButtonDown(0);
        boolean r = Mouse.isButtonDown(1);
        drawKey("LMB", x, y + 44, l ? bgOn : bgOff, color, 0.8f);
        drawKey("RMB", x + 44, y + 44, r ? bgOn : bgOff, color, 0.8f);
    }

    private void drawKey(String text, int x, int y, int bg, int color, float scale) {
        GuiElements.drawStringScaled(text, x + 6, y + 4, scale, color);
        // lightweight rect using Minecraft GUI
        net.minecraft.client.gui.Gui.drawRect(x, y, x + 40, y + 20, bg);
    }
}