package com.futureclient.mixin;

import com.futureclient.gui.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {

    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow protected int backgroundWidth;
    @Shadow protected int backgroundHeight;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    // Fix: @Inject ki jagah @Redirect use kiya hai
    // Hum 'render' method ke andar jo 'drawBackground' call hota hai, usse intercept kar rahe hain.
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V"))
    private void onDrawBackground(HandledScreen<?> instance, DrawContext context, float delta, int mouseX, int mouseY) {
        // Vanilla texture draw nahi hoga.
        // Humara Custom Dark Theme draw hoga:
        
        // 1. Main Box
        RenderUtils.drawBorderedRect(
            context, 
            x, y, 
            backgroundWidth, backgroundHeight, 
            0xFF1E1E1E, // Inner Dark Color
            0xFF333333  // Border Color
        );

        // 2. Header Bar
        RenderUtils.drawRect(context, x, y, backgroundWidth, 20, 0xFF252525);
        
        // 3. Blue Line (Lunar Style Accent)
        RenderUtils.drawRect(context, x, y + 19, backgroundWidth, 1, 0xFF29B6F6);
    }
}
