package com.futureclient.event;

import com.futureclient.module.Module;
import com.futureclient.FutureClient;
import java.util.ArrayList;
import java.util.List;

public class EventBus {
    // Simple event bus implementation for 1.8.9
    public void post(Event event) {
        if (FutureClient.INSTANCE.moduleManager == null) return;
        
        for (Module module : FutureClient.INSTANCE.moduleManager.getModules()) {
            if (module.isEnabled()) {
                module.onEvent(event);
            }
        }
    }
}
