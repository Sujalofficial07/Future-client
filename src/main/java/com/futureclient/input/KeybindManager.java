package com.futureclient.input;

import com.futureclient.Main;
import com.futureclient.core.ModuleManager;
import com.futureclient.core.Module;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Very small keybind manager. Maps keys to module names and checks keyboard state.
 * In practice key events should be fired from Mixins/GameSettings.
 */
public class KeybindManager {
    private final Map<Integer, String> keyToModule = new HashMap<>();

    public KeybindManager() {}

    public void loadDefaults() {
        ModuleManager mm = Main.getModuleManager();
        for (Module m : mm.getModules()) {
            if (m.getDefaultKeybind() != Keyboard.KEY_NONE) {
                keyToModule.put(m.getDefaultKeybind(), m.getName());
            }
        }
    }

    public void register(int key, String moduleName) {
        if (key == Keyboard.KEY_NONE) return;
        keyToModule.put(key, moduleName);
    }

    /**
     * Called from Mixin when a key press is detected.
     */
    public void onKeyPress(int key) {
        if (!keyToModule.containsKey(key)) return;
        String moduleName = keyToModule.get(key);
        Module m = Main.getModuleManager().getModule(moduleName);
        if (m != null) m.toggle();
    }
}