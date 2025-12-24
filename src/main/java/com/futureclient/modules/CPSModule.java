package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.gui.GuiElements;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

import java.util.LinkedList;

/**
 * Simple CPS counter tracking left-click timings.
 */
public class CPSModule extends Module {
    private final LinkedList<Long> clicks = new LinkedList<>();
    private final Minecraft mc = Minecraft.getMinecraft();

    public CPSModule() {
        super("CPSCounter", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(0)) {
            long now = System.currentTimeMillis();
            if (clicks.isEmpty() || now - clicks.getLast() > 50) {
                clicks.add(now);
            }
        }
        long cutoff = System.currentTimeMillis() - 1000;
        while (!clicks.isEmpty() && clicks.getFirst() < cutoff) clicks.removeFirst();
    }

    @Override
    public void onRender() {
        String s = "CPS: " + clicks.size();
        GuiElements.drawStringScaled(s, 6, 120, 1.0f, 0xFFFFFF);
    }
}