package com.futureclient.core;

import com.futureclient.modules.*;
import com.futureclient.ClientLogger;

import java.util.*;

/**
 * Registers and manages modules. Lightweight, single-threaded.
 */
public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    private final Map<String, Module> modulesMap = new HashMap<>();

    public ModuleManager() {}

    public void init() {
        // Register modules here in a simple centralized way.
        register(new KeystrokesModule());
        register(new CPSModule());
        register(new FPSModule());
        register(new PingModule());
        register(new CoordinatesModule());
        register(new ToggleSprintModule());
        register(new ZoomModule());
        register(new ParticleReducerModule());
        register(new EntityOptimizationsModule());
        ClientLogger.info("Registered " + modules.size() + " modules.");
    }

    private void register(Module m) {
        modules.add(m);
        modulesMap.put(m.getName(), m);
    }

    public List<Module> getModules() { return modules; }
    public Map<String, Module> getModulesMap() { return modulesMap; }

    public Module getModule(String name) { return modulesMap.get(name); }

    public void onUpdate() {
        // Tick update loop — iterate quickly
        for (Module m : modules) {
            if (m.isEnabled()) {
                try {
                    m.onUpdate();
                } catch (Throwable t) {
                    ClientLogger.error("Module " + m.getName() + " threw onUpdate", t);
                }
            }
        }
    }

    public void onRender() {
        // Render loop — called from render tick
        for (Module m : modules) {
            if (m.isEnabled()) {
                try {
                    m.onRender();
                } catch (Throwable t) {
                    ClientLogger.error("Module " + m.getName() + " threw onRender", t);
                }
            }
        }
    }
}