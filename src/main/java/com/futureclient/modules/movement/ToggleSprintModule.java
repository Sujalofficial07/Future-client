package com.futureclient.modules.movement;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

/**
 * Automatically sprint when moving forward
 */
public class ToggleSprintModule extends Module {
    
    public ToggleSprintModule() {
        super("ToggleSprint", "Automatically sprint", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        // Auto sprint when moving forward
        if (mc.options.keyForward.isPressed() && !mc.player.horizontalCollision && !mc.player.isSneaking()) {
            if (!mc.player.isSprinting() && mc.player.getHungerManager().getFoodLevel() > 6) {
                mc.player.setSprinting(true);
            }
        }
    }
}
