package com.futureclient.mixin;

import com.futureclient.FutureClient;
import com.futureclient.event.impl.EventRender2D;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class MixinInGameHud {
    @Inject(method = "render", at = @At("RETURN"))
    public void onRender(float tickDelta, CallbackInfo ci) {
        FutureClient.INSTANCE.eventBus.post(new EventRender2D(tickDelta));
    }
}
