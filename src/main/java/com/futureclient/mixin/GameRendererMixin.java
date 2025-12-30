package com.futureclient.mixin;

import com.futureclient.FutureClient;
import com.futureclient.module.impl.Zoom;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void getFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> info) {
        if (FutureClient.moduleManager.isModuleEnabled(Zoom.class)) {
            info.setReturnValue(info.getReturnValue() * 0.30); // Smooth zoom factor
        }
    }
}
