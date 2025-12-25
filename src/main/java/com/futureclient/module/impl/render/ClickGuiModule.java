package com.futureclient.module.impl.render;

import com.futureclient.gui.ClickGuiScreen;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGuiModule extends Module {
    public ClickGuiModule() {
        super("ClickGUI", "Open the menu", Category.RENDER);
        this.setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        // Correct mapping for 1.8.9 Yarn is often setScreen
        mc.setScreen(new ClickGuiScreen());
        this.setEnabled(false);
    }
}
