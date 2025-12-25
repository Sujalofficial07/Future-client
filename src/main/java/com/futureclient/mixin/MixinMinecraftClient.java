package com.futureclient.mixin;

import com.futureclient.FutureClient;
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
        if (FutureClient.INSTANCE == null) return;

        // 1. Handle Events
        if (FutureClient.INSTANCE.eventBus != null) {
            FutureClient.INSTANCE.eventBus.post(new EventUpdate());
        }

        // 2. Handle Key Input (LWJGL 2 style)
        // We iterate pending keyboard events to prevent spamming toggle
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) { // True if pressed, False if released
                int key = Keyboard.getEventKey();
                if (key != Keyboard.KEY_NONE) {
                    FutureClient.INSTANCE.moduleManager.onKey(key);
                }
            }
        }
    }
}
