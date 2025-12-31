package com.futureclient.mixin;

import com.futureclient.gui.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {

    // Shadows: Hum sirf wahi variables le rahe hain jo 100% exist karte hain
    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow protected int backgroundWidth;
    @Shadow protected int backgroundHeight;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    // Vanilla Background Drawing ko Cancel karke apna Dark UI lagana
    @Inject(method = "drawBackground", at = @At("HEAD"), cancellable = true)
    protected void onDrawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        // Vanilla texture rendering roko
        ci.cancel();

        // 1. Transparent Dark Background (World ko dim karna)
        this.renderBackground(context); 

        // 2. Main Container Box (Lunar Style Dark Grey)
        RenderUtils.drawBorderedRect(
            context, 
            x, y, 
            backgroundWidth, backgroundHeight, 
            0xFF1E1E1E, // Inner Color (Dark Grey)
            0xFF333333  // Border Color
        );

        // 3. Top Header Bar
        RenderUtils.drawRect(context, x, y, backgroundWidth, 20, 0xFF252525);
        RenderUtils.drawRect(context, x, y + 19, backgroundWidth, 1, 0xFF29B6F6); // Blue Line
    }
}
