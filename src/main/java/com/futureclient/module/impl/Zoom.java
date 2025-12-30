package com.futureclient.module.impl;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import org.lwjgl.glfw.GLFW;

public class Zoom extends Module {
    public Zoom() {
        super("Zoom", "Optifine style zoom.", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_C);
    }
    // Logic inside GameRendererMixin
}
