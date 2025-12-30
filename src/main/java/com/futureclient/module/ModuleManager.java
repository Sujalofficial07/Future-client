package com.futureclient.module;

import com.futureclient.module.impl.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new ToggleSprint());
        modules.add(new Fullbright());
        modules.add(new Zoom());
        modules.add(new OldAnimations());
    }

    public List<Module> getModules() { return modules; }
    public List<Module> getModulesByCategory(Category c) {
        return modules.stream().filter(m -> m.getCategory() == c).collect(Collectors.toList());
    }
    public Module getModuleByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    public boolean isModuleEnabled(Class<? extends Module> clazz) {
        return modules.stream().anyMatch(m -> m.getClass() == clazz && m.isEnabled());
    }
}
