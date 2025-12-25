package com.futureclient.module;

import com.futureclient.module.impl.hud.*;
import com.futureclient.module.impl.movement.*;
import com.futureclient.module.impl.render.*;
import com.futureclient.module.impl.performance.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // HUD
        modules.add(new InfoHUD());
        modules.add(new Keystrokes());
        
        // Movement
        modules.add(new ToggleSprint());
        
        // Render
        modules.add(new Zoom());
        modules.add(new ClickGuiModule());
        
        // Performance
        modules.add(new ParticleOptimizer());
    }

    public List<Module> getModules() { return modules; }
    
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }

    public Module getModule(Class<? extends Module> clazz) {
        return modules.stream().filter(m -> m.getClass() == clazz).findFirst().orElse(null);
    }

    public void onKey(int key) {
        for (Module m : modules) {
            if (m.getKey() == key) m.toggle();
        }
    }
}
