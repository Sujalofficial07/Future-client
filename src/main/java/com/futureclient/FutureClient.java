package com.futureclient;

import com.futureclient.config.ConfigManager;
import com.futureclient.event.KeyInputHandler;
import com.futureclient.gui.hud.HUDManager;
import com.futureclient.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureClient implements ClientModInitializer {
    public static final String MOD_ID = "futureclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    // Singleton instances for Managers
    public static ModuleManager moduleManager;
    public static HUDManager hudManager;
    public static ConfigManager configManager;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Starting FutureClient [Fabric 1.20.1]...");

        // Initialize Core Systems
        moduleManager = new ModuleManager();
        hudManager = new HUDManager();
        configManager = new ConfigManager();
        
        // Load settings
        configManager.loadConfig();

        // Register Keybindings
        KeyInputHandler.register();
    }
}
