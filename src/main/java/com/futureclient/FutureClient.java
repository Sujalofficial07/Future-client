package com.futureclient;

import com.futureclient.core.ConfigManager;
import com.futureclient.core.EventBus;
import com.futureclient.core.ModuleManager;
import com.futureclient.events.KeyInputEvent;
import com.futureclient.gui.ClickGui;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * FutureClient - A high-performance PvP and HUD client for Minecraft 1.8.9
 * Optimized for low-end devices and mobile launchers (PojavLauncher/Mojo)
 */
public class FutureClient implements ClientModInitializer {
    
    public static final String MOD_ID = "futureclient";
    public static final String MOD_NAME = "FutureClient";
    public static final String VERSION = "1.0.0";
    
    private static FutureClient instance;
    private ModuleManager moduleManager;
    private EventBus eventBus;
    private ConfigManager configManager;
    private ClickGui clickGui;
    
    private KeyBinding clickGuiKey;
    
    @Override
    public void onInitializeClient() {
        instance = this;
        
        System.out.println("[" + MOD_NAME + "] Initializing v" + VERSION);
        
        // Initialize core systems
        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        clickGui = new ClickGui();
        
        // Register keybindings
        clickGuiKey = new KeyBinding("key.futureclient.clickgui", Keyboard.KEY_RSHIFT, "category.futureclient");
        net.minecraft.client.settings.KeyBinding.registerKeyBinding(clickGuiKey);
        
        // Load configuration
        configManager.load();
        
        System.out.println("[" + MOD_NAME + "] Initialization complete!");
    }
    
    public void onTick() {
        if (clickGuiKey.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(clickGui);
        }
        
        // Fire key input event for modules
        eventBus.post(new KeyInputEvent());
    }
    
    public static FutureClient getInstance() {
        return instance;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public EventBus getEventBus() {
        return eventBus;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public ClickGui getClickGui() {
        return clickGui;
    }
}
