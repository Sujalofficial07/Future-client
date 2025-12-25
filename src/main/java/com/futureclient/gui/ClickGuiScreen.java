package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.gui.components.Frame;
import com.futureclient.module.Category;
import net.minecraft.client.gui.screen.Screen;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

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
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
