package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Entity render optimizations: provides a max distance threshold for rendering entities.
 */
public class EntityOptimizationsModule extends Module {
    private final NumberSetting maxDistance = new NumberSetting("max_distance", 100.0, 16.0, 512.0, 1.0);

    public EntityOptimizationsModule() {
        super("EntityOptimizations", Category.PERFORMANCE, Keyboard.KEY_NONE);
        addSetting(maxDistance);
    }

    public double getMaxRenderDistance() {
        return maxDistance.getValue();
    }
}