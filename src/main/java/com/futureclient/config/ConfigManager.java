package com.futureclient.config;

import com.futureclient.Main;
import com.futureclient.core.Module;
import com.futureclient.core.ModuleManager;
import com.futureclient.modules.*;
import com.futureclient.ClientLogger;
import com.futureclient.settings.BooleanSetting;
import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Simple JSON config manager to persist module states and settings.
 * Saves to config/futureclient.json inside resources-relative location (this depicts typical placement).
 */
public class ConfigManager {
    private static final File CONFIG_FILE = new File("config/futureclient.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        if (!CONFIG_FILE.getParentFile().exists()) {
            CONFIG_FILE.getParentFile().mkdirs();
        }
    }

    public void load() {
        try {
            if (!CONFIG_FILE.exists()) {
                saveDefaults();
                ClientLogger.info("No config found. Created default config.");
                return;
            }
            try (Reader r = new InputStreamReader(new FileInputStream(CONFIG_FILE), StandardCharsets.UTF_8)) {
                JsonObject root = gson.fromJson(r, JsonObject.class);
                if (root == null) return;
                JsonObject modulesObj = root.has("modules") ? root.getAsJsonObject("modules") : null;
                if (modulesObj == null) return;

                ModuleManager manager = Main.getModuleManager();
                for (Map.Entry<String, Module> e : manager.getModulesMap().entrySet()) {
                    String key = e.getKey();
                    Module module = e.getValue();
                    if (!modulesObj.has(key)) continue;
                    JsonObject mconf = modulesObj.getAsJsonObject(key);
                    if (mconf.has("enabled") && mconf.get("enabled").isJsonPrimitive()) {
                        boolean enabled = mconf.get("enabled").getAsBoolean();
                        if (enabled && !module.isEnabled()) module.enable();
                        if (!enabled && module.isEnabled()) module.disable();
                    }
                    // Load basic boolean settings by name (simple)
                    for (Object st : module.getSettings().toArray()) {
                        if (!(st instanceof BooleanSetting)) continue;
                        BooleanSetting bs = (BooleanSetting) st;
                        if (mconf.has(bs.getName())) {
                            try {
                                boolean val = mconf.get(bs.getName()).getAsBoolean();
                                bs.setValue(val);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
                ClientLogger.info("Config loaded.");
            }
        } catch (Exception e) {
            ClientLogger.error("Failed to load config", e);
        }
    }

    public void save() {
        try {
            JsonObject root = new JsonObject();
            JsonObject modulesObj = new JsonObject();
            ModuleManager manager = Main.getModuleManager();
            for (Map.Entry<String, Module> entry : manager.getModulesMap().entrySet()) {
                Module module = entry.getValue();
                JsonObject mconf = new JsonObject();
                mconf.addProperty("enabled", module.isEnabled());
                // Basic settings
                for (Object s : module.getSettings().toArray()) {
                    if (s instanceof com.futureclient.settings.BooleanSetting) {
                        com.futureclient.settings.BooleanSetting bs = (com.futureclient.settings.BooleanSetting) s;
                        mconf.addProperty(bs.getName(), bs.getValue());
                    } else if (s instanceof com.futureclient.settings.NumberSetting) {
                        com.futureclient.settings.NumberSetting ns = (com.futureclient.settings.NumberSetting) s;
                        mconf.addProperty(ns.getName(), ns.getValue());
                    }
                }
                modulesObj.add(module.getName(), mconf);
            }
            root.add("modules", modulesObj);

            try (Writer w = new OutputStreamWriter(new FileOutputStream(CONFIG_FILE), StandardCharsets.UTF_8)) {
                gson.toJson(root, w);
            }
            ClientLogger.info("Config saved.");
        } catch (Exception e) {
            ClientLogger.error("Failed to save config", e);
        }
    }

    private void saveDefaults() {
        try (InputStream in = getClass().getResourceAsStream("/assets/futureclient/config/default_config.json")) {
            if (in == null) return;
            try (OutputStream out = new FileOutputStream(CONFIG_FILE)) {
                byte[] buf = new byte[8192];
                int read;
                while ((read = in.read(buf)) > 0) out.write(buf, 0, read);
            }
        } catch (Exception e) {
            ClientLogger.error("Failed to write default config", e);
        }
    }
}