package com.futureclient.modules.performance;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

/**
 * Toggle animations for better performance
 */
public class AnimationToggleModule extends Module {
    
    public AnimationToggleModule() {
        super("AnimationToggle", "Toggle animations for performance", Category.PERFORMANCE);
    }
    
    @Override
    public void onEnable() {
        System.out.println("[AnimationToggle] Animations disabled");
    }
    
    @Override
    public void onDisable() {
        System.out.println("[AnimationToggle] Animations enabled");
    }
}
