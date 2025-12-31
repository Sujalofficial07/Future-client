package com.futureclient.mixin;

import com.futureclient.gui.RenderUtils;
import com.mojang.blaze3d.systems.RenderSystem;
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

    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow protected int backgroundWidth;
    @Shadow protected int backgroundHeight;
    @Shadow protected java.util.List<net.minecraft.screen.slot.Slot> handler_slots; // Pseudo-code accessor

    // Asli implementation mein "handler" access karna padta hai
    // Par simplcity ke liye, hum bas main background draw kar rahe hain upar wale code mein.
    

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    // "drawBackground" method woh hai jo vanilla texture (grey box) draw karta hai.
    // Hum isse CANCEL karke apna Dark UI draw karenge.
    @Inject(method = "drawBackground", at = @At("HEAD"), cancellable = true)
    protected void onDrawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        // Vanilla texture rendering roko
        ci.cancel();

        // 1. Transparent Dark Background (Dim the world behind)
        this.renderBackground(context); 

        // 2. Main Container Box (Lunar Style Dark Grey)
        // x aur y automatic calculate hote hain har screen ke liye (center mein)
        RenderUtils.drawBorderedRect(
            context, 
            x, y, 
            backgroundWidth, backgroundHeight, 
            0xFF1E1E1E, // Inner Color (Darker than vanilla)
            0xFF333333  // Border Color
        );

        // 3. Top Header Bar (Jahan naam likha hota hai "Chest" ya "Inventory")
        // Thoda sa dark accent upar
        RenderUtils.drawRect(context, x, y, backgroundWidth, 20, 0xFF252525);
        RenderUtils.drawRect(context, x, y + 19, backgroundWidth, 1, 0xFF29B6F6); // Thin Blue Line (Lunar Accent)
    }
    
    // Text color ko fix karne ke liye (Vanilla text dark grey hota hai, dark background pe dikhega nahi)
    @Inject(method = "drawForeground", at = @At("HEAD"))
    protected void onDrawForeground(DrawContext context, int mouseX, int mouseY, CallbackInfo ci) {
        // Is method mein hum forcefully text color change nahi kar sakte easily bina complex redirects ke.
        // Lekin kyunki humara background dark hai, vanilla text thoda hard dikhega.
        // Best tarika: RenderUtils mein text draw karte waqt shadow on rakhein.
    }
}
