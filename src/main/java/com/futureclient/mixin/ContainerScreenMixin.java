package com.futureclient.mixin;

import com.futureclient.gui.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Sirf "GenericContainerScreen" (Chests, Double Chests, Ender Chests) ko target karega
@Mixin(GenericContainerScreen.class)
public abstract class ContainerScreenMixin extends HandledScreen<GenericContainerScreenHandler> {

    public ContainerScreenMixin(GenericContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Background dimming
        this.renderBackground(context);
    }

    @Inject(method = "drawBackground", at = @At("HEAD"), cancellable = true)
    protected void onDrawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        // Vanilla gray texture ko cancel karein
        ci.cancel();

        // Custom Dark Chest UI
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        RenderUtils.drawBorderedRect(
            context, 
            x, y, 
            this.backgroundWidth, this.backgroundHeight, 
            0xFF1E1E1E, // Dark Grey
            0xFF333333  // Border
        );

        // Header Blue Line
        RenderUtils.drawRect(context, x, y + 19, this.backgroundWidth, 1, 0xFF29B6F6);
    }
}
