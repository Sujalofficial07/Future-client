package com.futureclient.core;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages saving and loading configuration for FutureClient
 */
public class ConfigManager {
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File configFile;
    
    public ConfigManager() {
        File gameDir = Minecraft.getMinecraft().mcDataDir;
        File configDir = new File(gameDir, "futureclient");
        
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        
        this.configFile = new File(configDir, "config.json");
    }
    
    /**
     * Save current configuration to file
     */
    public void save() {
        try (FileWriter writer = new FileWriter(configFile)) {
            JsonObject root = new JsonObject();
            JsonObject modulesObj = new JsonObject();
            
            for (Module module : FutureClient.getInstance().getModuleManager().getModules()) {
                JsonObject moduleObj = new JsonObject();
                moduleObj.addProperty("enabled", module.isEnabled());
                moduleObj.addProperty("keyCode", module.getKeyCode());
                modulesObj.add(module.getName(), moduleObj);
            }
            
            root.add("modules", modulesObj);
            
            gson.toJson(root, writer);
            System.out.println("[ConfigManager] Configuration saved");
        } catch (IOException e) {
            System.err.println("[ConfigManager] Failed to save configuration: " + e.getMessage());
        }
    }
    
    /**
     * Load configuration from file
     */
    public void load() {
        if (!configFile.exists()) {
            System.out.println("[ConfigManager] No configuration file found, using defaults");
            return;
        }
        
        try (FileReader reader = new FileReader(configFile)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            
            if (root.has("modules")) {
                JsonObject modulesObj = root.getAsJsonObject("modules");
                
                for (Module module : FutureClient.getInstance().getModuleManager().getModules()) {
                    if (modulesObj.has(module.getName())) {
                        JsonObject moduleObj = modulesObj.getAsJsonObject(module.getName());
                        
                        if (moduleObj.has("enabled")) {
                            module.setEnabled(moduleObj.get("enabled").getAsBoolean());
                        }
                        
                        if (moduleObj.has("keyCode")) {
                            module.setKeyCode(moduleObj.get("keyCode").getAsInt());
                        }
                    }
                }
            }
            
            System.out.println("[ConfigManager] Configuration loaded");
        } catch (IOException e) {
            System.err.println("[ConfigManager] Failed to load configuration: " + e.getMessage());
        }
    }
}
