package com.futureclient.mixin;

import com.futureclient.FutureClient;
import com.futureclient.module.impl.render.Zoom;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinEntityRenderer {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    public void onGetFov(float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        Zoom zoom = (Zoom) FutureClient.INSTANCE.moduleManager.getModule(Zoom.class);
        if (zoom != null && zoom.isEnabled()) {
            cir.setReturnValue(cir.getReturnValue() / 4.0f);
        }
    }
}
