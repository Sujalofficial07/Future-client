package com.futureclient.mixins;

import com.futureclient.Main;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin into Minecraft main loop for initialization and ticks.
 */
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onStartGame(CallbackInfo ci) {
        // Called on game start - initialize client systems.
        try {
            Main.init();
        } catch (Throwable t) {
            // swallow to avoid blocking Minecraft
            t.printStackTrace();
        }
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    private void onRunTick(CallbackInfo ci) {
        try {
            Main.onTick();
        } catch (Throwable ignored) {}
    }
}