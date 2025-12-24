package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Particle reducer: modules returns a reduction fraction that Mixins use to filter particle spawns.
 */
public class ParticleReducerModule extends Module {
    private final NumberSetting reduceAmount = new NumberSetting("reduce_amount", 0.6, 0.0, 1.0, 0.01);

    public ParticleReducerModule() {
        super("ParticleReducer", Category.PERFORMANCE, Keyboard.KEY_NONE);
        addSetting(reduceAmount);
    }

    public double getReduceAmount() {
        return reduceAmount.getValue();
    }
}