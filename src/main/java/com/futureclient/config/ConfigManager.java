package com.futureclient.config;

import com.futureclient.FutureClient;
import com.futureclient.module.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("futureclient.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void saveConfig() {
        List<ModuleConfig> configs = new ArrayList<>();
        for (Module m : FutureClient.moduleManager.getModules()) {
            configs.add(new ModuleConfig(m.getName(), m.isEnabled(), m.getKey()));
        }
        try (Writer w = new FileWriter(CONFIG_FILE)) { GSON.toJson(configs, w); } 
        catch (IOException e) { e.printStackTrace(); }
    }

    public void loadConfig() {
        if (!CONFIG_FILE.exists()) return;
        try (Reader r = new FileReader(CONFIG_FILE)) {
            List<ModuleConfig> configs = GSON.fromJson(r, new TypeToken<List<ModuleConfig>>(){}.getType());
            if (configs != null) {
                for (ModuleConfig c : configs) {
                    Module m = FutureClient.moduleManager.getModuleByName(c.name);
                    if (m != null) { m.setEnabled(c.enabled); m.setKey(c.key); }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
