package com.futureclient.mixins;

import com.futureclient.Main;
import com.futureclient.modules.ParticleReducerModule;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

/**
 * Intercept particle adding to reduce particle count based on module setting.
 */
@Mixin(EffectRenderer.class)
public class MixinEffectRenderer {
    private final Random rand = new Random();

    @Redirect(method = "addEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;addEffect(Lnet/minecraft/client/particle/EntityFX;)V"))
    private void onAddEffect(EffectRenderer renderer, EntityFX fx) {
        try {
            ParticleReducerModule prm = (ParticleReducerModule) Main.getModuleManager().getModule("ParticleReducer");
            if (prm != null && prm.isEnabled()) {
                double keep = prm.getReduceAmount(); // fraction to keep (0..1)
                if (rand.nextDouble() > keep) {
                    // skip spawning this particle
                    return;
                }
            }
        } catch (Throwable ignored) {}
        // fall back to original call - use reflection to call original addEffect (since redirect replaced invocation)
        renderer.addEffect(fx);
    }
}