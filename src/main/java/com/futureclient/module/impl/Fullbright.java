package com.futureclient.module.impl;

import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class Fullbright extends Module {
    private double oldGamma;
    public Fullbright() { super("Fullbright", "Max brightness.", Category.RENDER); }

    @Override
    public void onEnable() {
        if(mc.options != null) {
            oldGamma = mc.options.getGamma().getValue();
            mc.options.getGamma().setValue(1000.0);
        }
    }
    @Override
    public void onDisable() {
        if(mc.options != null) mc.options.getGamma().setValue(oldGamma);
    }
}
