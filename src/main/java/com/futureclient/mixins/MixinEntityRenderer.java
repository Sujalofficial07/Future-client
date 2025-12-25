package com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.modules.render.ZoomModule;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
    
    @Inject(method = "getFOVModifier", at = @At("RETURN"), cancellable = true)
    private void onGetFOVModifier(float partialTicks, boolean useFovSetting, CallbackInfoReturnable<Float> cir) {
        if (FutureClient.getInstance() != null) {
            ZoomModule zoom = (ZoomModule) FutureClient.getInstance()
                .getModuleManager()
                .getModuleByName("Zoom");
            
            if (zoom != null && zoom.isEnabled()) {
                // Zoom module handles FOV modification in its onTick method
                // This mixin ensures compatibility with other FOV modifications
            }
        }
    }
}
