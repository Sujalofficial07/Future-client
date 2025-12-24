package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Smooth zoom by modifying FOV through EntityRenderer injection (Mixins read targetFov)
 */
public class ZoomModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final NumberSetting fov = new NumberSetting("fov", 40.0, 10.0, 90.0, 1.0);
    private float current = 0f;

    public ZoomModule() {
        super("Zoom", Category.MISC, Keyboard.KEY_Z);
        addSetting(fov);
    }

    public double getTargetFov() {
        return fov.getValue();
    }

    @Override
    public void onRender() {
        // simple smoothing state update - no allocations
        float target = (float) (isEnabled() ? getTargetFov() : mc.gameSettings.fovSetting);
        current += (target - current) * 0.2f;
    }

    public float getCurrentFov() {
        return current;
    }
}