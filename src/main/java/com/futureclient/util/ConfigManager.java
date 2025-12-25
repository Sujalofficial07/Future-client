package com.futureclient.util;

import com.futureclient.FutureClient;
import com.futureclient.module.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;

public class ConfigManager {
    private File file;
    private Gson gson;

    public ConfigManager() {
        this.file = new File(FutureClient.INSTANCE.NAME + ".json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void save() {
        JsonObject json = new JsonObject();
        for (Module m : FutureClient.INSTANCE.moduleManager.getModules()) {
            JsonObject modJson = new JsonObject();
            modJson.addProperty("enabled", m.isEnabled());
            modJson.addProperty("key", m.getKey());
            json.add(m.getName(), modJson);
        }
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(json, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!file.exists()) return;
        try (FileReader reader = new FileReader(file)) {
            JsonObject json = new JsonParser().parse(reader).getAsJsonObject();
            for (Module m : FutureClient.INSTANCE.moduleManager.getModules()) {
                if (json.has(m.getName())) {
                    JsonObject modJson = json.get(m.getName()).getAsJsonObject();
                    if (modJson.has("enabled")) m.setEnabled(modJson.get("enabled").getAsBoolean());
                    if (modJson.has("key")) m.setKey(modJson.get("key").getAsInt());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
