package com.futureclient.gui.components;

import com.futureclient.FutureClient;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Gui;
import java.awt.Color;

public class Frame {
    public int x, y, width, height, dragX, dragY;
    public Category category;
    public boolean isDragging, open;
    private MinecraftClient mc = MinecraftClient.getInstance();

    public Frame(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 14;
        this.open = true;
    }

    public void render(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(40, 40, 40, 255).getRGB());
        mc.textRenderer.drawWithShadow(category.name(), x + 2, y + 3, -1);

        if (open) {
            int yOffset = height;
            for (Module mod : FutureClient.INSTANCE.moduleManager.getModulesByCategory(category)) {
                int color = mod.isEnabled() ? new Color(60, 180, 60).getRGB() : new Color(60, 60, 60).getRGB();
                Gui.drawRect(x, y + yOffset, x + width, y + yOffset + 14, color);
                mc.textRenderer.drawWithShadow(mod.getName(), x + 2, y + yOffset + 3, -1);
                yOffset += 14;
            }
        }
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (isDragging) {
            this.x = mouseX - dragX;
            this.y = mouseY - dragY;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isWithinBounds(mouseX, mouseY)) {
            if (button == 0) {
                isDragging = true;
                dragX = mouseX - x;
                dragY = mouseY - y;
            } else if (button == 1) {
                open = !open;
            }
            return;
        }
        
        // Handle module clicks
        if (open) {
            int yOffset = height;
            for (Module mod : FutureClient.INSTANCE.moduleManager.getModulesByCategory(category)) {
                if (mouseX >= x && mouseX <= x + width && mouseY >= y + yOffset && mouseY <= y + yOffset + 14) {
                    if (button == 0) mod.toggle();
                }
                yOffset += 14;
            }
        }
        
        isDragging = false;
    }
    
    public boolean isWithinBounds(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
