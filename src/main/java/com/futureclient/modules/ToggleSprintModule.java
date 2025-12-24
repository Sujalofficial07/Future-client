package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Toggle-sprint: toggles player sprinting when enabled and moving.
 */
public class ToggleSprintModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public ToggleSprintModule() {
        super("ToggleSprint", Category.PVP, Keyboard.KEY_NONE);
    }

    @Override
    public void onUpdate() {
        if (mc.thePlayer == null) return;
        if (mc.thePlayer.moveForward > 0.05f || mc.thePlayer.moveStrafing != 0) {
            mc.thePlayer.setSprinting(true);
        } else {
            mc.thePlayer.setSprinting(false);
        }
    }
}