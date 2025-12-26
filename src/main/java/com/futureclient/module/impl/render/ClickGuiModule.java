package com.futureclient.module.impl.render;

import com.futureclient.gui.LunarGuiScreen;
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
        mc.setScreen(new LunarGuiScreen());
        this.setEnabled(false);
    }
}
