package com.futureclient.mixins;

import com.futureclient.Main;
import com.futureclient.input.KeybindManager;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hook into GameSettings key handling to detect key presses early and dispatch to KeybindManager.
 * Note: this injection is conservative and uses existing method entry points.
 */
@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Inject(method = "onKeyPress", at = @At("HEAD"), remap = false)
    private void onKeyPress(int keyCode, CallbackInfo ci) {
        try {
            KeybindManager kbm = Main.getKeybindManager();
            if (kbm != null) {
                kbm.onKeyPress(keyCode);
            }
        } catch (Throwable ignored) {}
    }
}