package com.futureclient.mixin;

import com.futureclient.gui.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {

    // Background Color Change (White -> Dark Grey)
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Poori screen ko Dark Grey fill kar do (Overwriting vanilla white/red)
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        context.fill(0, 0, width, height, RenderUtils.COLOR_BACKGROUND);
    }

    // Optional: Agar aapko progress bar ka color bhi change karna hai, 
    // toh uske liye alag se method redirect karna padta hai, 
    // but upar wala code background ko Lunar jaisa dark bana dega.
}
