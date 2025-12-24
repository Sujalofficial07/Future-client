package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.gui.GuiElements;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * FPS display using Minecraft built-in framerate value
 */
public class FPSModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public FPSModule() {
        super("FPSDisplay", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onRender() {
        int fps = mc.getDebugFPS();
        GuiElements.drawStringScaled("FPS: " + fps, 6, 100, 1.0f, 0xFFFFFF);
    }
}