package com.futureclient.modules.movement;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

public class ToggleSprintModule extends Module {
    public ToggleSprintModule() {
        super("ToggleSprint", "Automatically sprint", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.thePlayer == null) return;
        
        if (mc.gameSettings.keyBindForward.isKeyDown() && 
            !mc.thePlayer.isCollidedHorizontally && 
            !mc.thePlayer.isSneaking()) {
            if (!mc.thePlayer.isSprinting() && mc.thePlayer.getFoodStats().getFoodLevel() > 6) {
                mc.thePlayer.setSprinting(true);
            }
        }
    }
}
