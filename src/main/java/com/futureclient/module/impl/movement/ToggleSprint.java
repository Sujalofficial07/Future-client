package com.futureclient.module.impl.movement;

import com.futureclient.event.Event;
import com.futureclient.event.impl.EventUpdate;
import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class ToggleSprint extends Module {
    public ToggleSprint() {
        super("ToggleSprint", "Automatically sprints when moving", Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            // In 1.8.9 Yarn, 'movementInput' is mapped to 'input'
            if (mc.player != null && mc.player.input.movementForward > 0 && !mc.player.isSneaking() && !mc.player.horizontalCollision) {
                mc.player.setSprinting(true);
            }
        }
    }
}
