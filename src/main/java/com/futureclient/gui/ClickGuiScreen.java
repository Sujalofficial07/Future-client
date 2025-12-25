package com.futureclient.gui;

import com.futureclient.gui.components.Frame;
import com.futureclient.module.Category;
import net.minecraft.client.gui.screen.Screen;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private List<Frame> frames;

    public ClickGuiScreen() {
        frames = new ArrayList<>();
        int x = 10;
        for (Category category : Category.values()) {
            frames.add(new Frame(category, x, 10));
            x += 105;
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        for (Frame frame : frames) {
            frame.render(mouseX, mouseY);
            frame.updatePosition(mouseX, mouseY);
        }
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, mouseButton);
        }
        // super.mouseClicked does not exist or throws IOException in some versions, 
        // but strictly in 1.8.9 Screen it might throw. 
        // We wrap it to be safe or omit if not needed for base logic.
        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean shouldPauseGame() {
        return false;
    }
}
