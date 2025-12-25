package com.futureclient;

import com.futureclient.event.EventBus;
import com.futureclient.module.ModuleManager;
import com.futureclient.util.ConfigManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FutureClient implements ModInitializer {
    public static final String NAME = "FutureClient";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static FutureClient INSTANCE;
    public ModuleManager moduleManager;
    public EventBus eventBus;
    public ConfigManager configManager;

    @Override
    public void onInitialize() {
        INSTANCE = this;
        LOGGER.info("Initializing " + NAME + " v" + VERSION);
        
        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        
        configManager.load();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.save()));
    }
}
