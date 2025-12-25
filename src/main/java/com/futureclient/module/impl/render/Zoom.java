package com.futureclient.module.impl.render;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import org.lwjgl.input.Keyboard;

public class Zoom extends Module {
    public Zoom() {
        super("Zoom", "Zooms in like OptiFine", Category.RENDER);
        this.setKey(Keyboard.KEY_C);
    }
    // Logic handled in MixinEntityRenderer
}
