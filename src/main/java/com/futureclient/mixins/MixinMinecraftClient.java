package com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.futureclient.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for Minecraft client to handle tick events
 */
@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (FutureClient.getInstance() != null) {
            // Fire tick event
            FutureClient.getInstance().getEventBus().post(new TickEvent());
            
            // Call onTick for all enabled modules
            for (Module module : FutureClient.getInstance().getModuleManager().getEnabledModules()) {
                module.onTick();
            }
        }
    }
}
