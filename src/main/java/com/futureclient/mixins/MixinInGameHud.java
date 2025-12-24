package com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.events.RenderEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for rendering HUD modules
 */
@Mixin(InGameHud.class)
public class MixinInGameHud {
    
    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(float partialTicks, CallbackInfo ci) {
        if (FutureClient.getInstance() != null) {
            // Fire render event
            FutureClient.getInstance().getEventBus().post(new RenderEvent(partialTicks));
            
            // Render all enabled modules
            for (Module module : FutureClient.getInstance().getModuleManager().getEnabledModules()) {
                module.onRender(partialTicks);
            }
        }
    }
}
