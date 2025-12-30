package com.futureclient.event;

import com.futureclient.FutureClient;
import com.futureclient.gui.screen.ClickGUIScreen;
import com.futureclient.module.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static KeyBinding openClickGui;

    public static void register() {
        // Default bind: Right Shift for GUI
        openClickGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.futureclient.clickgui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.futureclient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openClickGui.wasPressed()) {
                client.setScreen(new ClickGUIScreen());
            }

            // Handle Module Keybinds
            if (client.player != null && client.currentScreen == null) {
                for (Module module : FutureClient.moduleManager.getModules()) {
                    if (module.getKey() != -1 && InputUtil.isKeyPressed(client.getWindow().getHandle(), module.getKey())) {
                         // Simple toggle logic (needs debounce in production, omitted for brevity)
                         module.toggle(); 
                    }
                    
                    if (module.isEnabled()) {
                        module.onTick();
                    }
                }
            }
        });
    }
}
