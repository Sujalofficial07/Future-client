package com.futureclient.mixin;

import com.futureclient.FutureClient;
import com.futureclient.event.impl.EventUpdate;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    
    // Array to track which keys were down last tick (to detect single presses)
    // 256 is enough for standard keys. If you use special media keys, increase to Keyboard.KEYBOARD_SIZE
    private boolean[] keyStates = new boolean[256]; 

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (FutureClient.INSTANCE == null) return;

        // 1. Handle Update Event (for Sprint, etc.)
        if (FutureClient.INSTANCE.eventBus != null) {
            FutureClient.INSTANCE.eventBus.post(new EventUpdate());
        }

        // 2. Handle Key Input (Safe Mode)
        // We iterate through our modules and check if their bound key is being pressed.
        // We do NOT use Keyboard.next() here, so Minecraft can still read the keys.
        if (FutureClient.INSTANCE.moduleManager != null) {
            for (Module m : FutureClient.INSTANCE.moduleManager.getModules()) {
                int key = m.getKey();
                
                // Skip unbound modules or invalid keys
                if (key <= 0 || key >= keyStates.length) continue;

                // Check live state of the key
                boolean isDown = Keyboard.isKeyDown(key);
                
                // If currently down, but wasn't down last tick -> It's a new press
                if (isDown && !keyStates[key]) {
                    // Only toggle if we are in-game (player exists) and not in a GUI (currentScreen is null)
                    // Or if it's the ClickGUI (which might need to open even if screens are null)
                    if (MinecraftClient.getInstance().currentScreen == null || m.getName().equals("ClickGUI")) {
                        m.toggle();
                    }
                }
                
                // Update state for next tick
                keyStates[key] = isDown;
            }
        }
    }
}
