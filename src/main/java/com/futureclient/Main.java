package com.futureclient;

import com.futureclient.config.ConfigManager;
import com.futureclient.core.ModuleManager;
import com.futureclient.input.KeybindManager;
import com.futureclient.events.EventBus;

import net.minecraft.client.Minecraft;

/**
 * Main entry class. Fabric legacy / mixin entry will call this on load.
 * We keep initialization lightweight.
 */
public class Main {
    public static final String MODID = "futureclient";
    public static final String NAME = "FutureClient";
    public static final String VERSION = "1.0.0";

    private static final Minecraft mc = Minecraft.getMinecraft();

    private static ModuleManager moduleManager;
    private static KeybindManager keybindManager;
    private static EventBus eventBus;
    private static ConfigManager configManager;

    public static void init() {
        ClientLogger.info("Initializing FutureClient...");
        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        keybindManager = new KeybindManager();
        configManager = new ConfigManager();

        // Initialize modules and config
        moduleManager.init();
        configManager.load(); // loads module states
        keybindManager.loadDefaults();

        ClientLogger.info("FutureClient initialized. Modules loaded: " + moduleManager.getModules().size());
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static KeybindManager getKeybindManager() {
        return keybindManager;
    }

    public static EventBus getEventBus() {
        return eventBus;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    // Hook: called by mixin when client tick occurs or on load.
    public static void onTick() {
        if (mc == null || mc.theWorld == null) return;
        moduleManager.onUpdate();
    }

    public static void onRender() {
        moduleManager.onRender();
    }
}