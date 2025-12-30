package com.futureclient.module.impl;

import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class ToggleSprint extends Module {
    public ToggleSprint() {
        super("ToggleSprint", "Auto sprint when walking.", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player != null && !mc.player.isSneaking() && mc.player.input.movementForward > 0 && !mc.player.horizontalCollision) {
            mc.player.setSprinting(true);
        }
    }
}
