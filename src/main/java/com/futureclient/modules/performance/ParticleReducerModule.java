package com.futureclient.modules.performance;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

/**
 * Reduces particle count for better performance
 * Note: Actual particle reduction is handled by MixinParticleManager
 */
public class ParticleReducerModule extends Module {
    
    public ParticleReducerModule() {
        super("ParticleReducer", "Reduce particle count for FPS", Category.PERFORMANCE);
    }
    
    @Override
    public void onEnable() {
        System.out.println("[ParticleReducer] Particle reduction enabled");
    }
    
    @Override
    public void onDisable() {
        System.out.println("[ParticleReducer] Particle reduction disabled");
    }
}
