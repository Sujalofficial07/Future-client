package com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.modules.performance.ParticleReducerModule;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for reducing particle count
 */
@Mixin(ParticleManager.class)
public class MixinParticleManager {
    
    private int particleCounter = 0;
    
    @Inject(method = "addParticle(Lnet/minecraft/client/particle/Particle;)V", at = @At("HEAD"), cancellable = true)
    private void onAddParticle(Particle particle, CallbackInfo ci) {
        if (FutureClient.getInstance() != null) {
            ParticleReducerModule reducer = (ParticleReducerModule) FutureClient.getInstance()
                .getModuleManager()
                .getModuleByName("ParticleReducer");
            
            if (reducer != null && reducer.isEnabled()) {
                particleCounter++;
                
                // Only render every 3rd particle for significant FPS improvement
                if (particleCounter % 3 != 0) {
                    ci.cancel();
                }
            }
        }
    }
}
