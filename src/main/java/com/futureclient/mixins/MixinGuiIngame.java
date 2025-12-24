package com.futureclient.mixins;

import com.futureclient.Main;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hook into GuiIngame to render custom HUD and click GUI overlay efficiently.
 */
@Mixin(GuiIngame.class)
public class MixinGuiIngame {
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void onRenderGameOverlay(float partialTicks, CallbackInfo ci) {
        try {
            Main.onRender();
        } catch (Throwable ignored) {}
    }
}