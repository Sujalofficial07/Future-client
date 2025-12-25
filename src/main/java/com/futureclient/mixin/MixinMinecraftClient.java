package com.futureclient.mixin;

import com.futureclient.FutureClient;
import com.futureclient.event.impl.EventKey;
import com.futureclient.event.impl.EventUpdate;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (FutureClient.INSTANCE != null && FutureClient.INSTANCE.eventBus != null) {
            FutureClient.INSTANCE.eventBus.post(new EventUpdate());
        }
    }

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    public void onKey(CallbackInfo ci) {
        if (Keyboard.getEventKeyState()) {
            int key = Keyboard.getEventKey();
            if (key != Keyboard.KEY_NONE) {
                FutureClient.INSTANCE.moduleManager.onKey(key);
            }
        }
    }
}
