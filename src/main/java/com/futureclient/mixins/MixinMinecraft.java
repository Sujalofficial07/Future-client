package com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.futureclient.events.TickEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(method = "runTick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (FutureClient.getInstance() != null) {
            // Fire tick event
            FutureClient.getInstance().getEventBus().post(new TickEvent());
            
            // Call onTick for FutureClient (keybinds)
            FutureClient.getInstance().onTick();
            
            // Call onTick for all enabled modules
            for (Module module : FutureClient.getInstance().getModuleManager().getEnabledModules()) {
                module.onTick();
            }
        }
    }
}
