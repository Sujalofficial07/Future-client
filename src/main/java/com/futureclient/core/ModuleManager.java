package com.futureclient.core;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.modules.hud.*;
import com.futureclient.modules.movement.ToggleSprintModule;
import com.futureclient.modules.performance.AnimationToggleModule;
import com.futureclient.modules.performance.ParticleReducerModule;
import com.futureclient.modules.render.ZoomModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages all modules in FutureClient
 */
public class ModuleManager {
    
    private final List<Module> modules = new ArrayList<>();
    
    public ModuleManager() {
        // Register all modules
        registerModule(new FPSModule());
        registerModule(new KeystrokesModule());
        registerModule(new CoordinatesModule());
        registerModule(new PingModule());
        registerModule(new CPSModule());
        registerModule(new ToggleSprintModule());
        registerModule(new ZoomModule());
        registerModule(new ParticleReducerModule());
        registerModule(new AnimationToggleModule());
        
        System.out.println("[ModuleManager] Registered " + modules.size() + " modules");
    }
    
    private void registerModule(Module module) {
        modules.add(module);
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    public Module getModuleByName(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    public List<Module> getEnabledModules() {
        return modules.stream()
            .filter(Module::isEnabled)
            .collect(Collectors.toList());
    }
}
