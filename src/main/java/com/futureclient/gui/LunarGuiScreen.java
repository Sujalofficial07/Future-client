package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.input.Mouse;

import java.awt.Color;
import java.util.List;

public class LunarGuiScreen extends Screen {
    
    // FIX: Define the 'mc' variable explicitly
    private final MinecraftClient mc = MinecraftClient.getInstance();

    // Layout Constants
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 350;
    private final int SIDEBAR_WIDTH = 120;
    
    // State
    private Category selectedCategory = Category.PVP;
    private float scrollY = 0;
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // 1. Draw Dark Background Overlay
        this.renderBackground(); 
        
        // Calculate Center Position
        int centerX = this.width / 2 - WINDOW_WIDTH / 2;
        int centerY = this.height / 2 - WINDOW_HEIGHT / 2;
        
        // 2. Draw Main Window Background (Dark Grey)
        RenderUtil.drawRect(centerX, centerY, WINDOW_WIDTH, WINDOW_HEIGHT, new Color(25, 25, 25, 255).getRGB());
        
        // 3. Draw Sidebar (Lighter Grey)
        RenderUtil.drawRect(centerX, centerY, SIDEBAR_WIDTH, WINDOW_HEIGHT, new Color(35, 35, 35, 255).getRGB());
        
        // 4. Draw Logo / Title
        mc.textRenderer.drawWithShadow("FUTURE CLIENT", centerX + 15, centerY + 15, -1);
        RenderUtil.drawRect(centerX + 10, centerY + 30, SIDEBAR_WIDTH - 20, 1, new Color(60, 60, 60).getRGB());

        // 5. Render Sidebar Categories
        int catY = centerY + 40;
        for (Category cat : Category.values()) {
            boolean isSelected = (cat == selectedCategory);
            int color = isSelected ? new Color(60, 60, 60).getRGB() : new Color(35, 35, 35).getRGB();
            int textColor = isSelected ? -1 : new Color(180, 180, 180).getRGB();
            
            // Hover effect
            if (mouseX >= centerX && mouseX <= centerX + SIDEBAR_WIDTH && mouseY >= catY && mouseY < catY + 25) {
                if (!isSelected) color = new Color(45, 45, 45).getRGB();
            }

            RenderUtil.drawRect(centerX, catY, SIDEBAR_WIDTH, 25, color);
            
            // Draw colored indicator for selected
            if (isSelected) {
                RenderUtil.drawRect(centerX, catY, 2, 25, new Color(0, 255, 255).getRGB());
            }
            
            mc.textRenderer.drawWithShadow(cat.name(), centerX + 15, catY + 8, textColor);
            catY += 25;
        }

        // 6. Render Modules Area (Grid Layout)
        int moduleAreaX = centerX + SIDEBAR_WIDTH + 10;
        int moduleAreaY = centerY + 10;
        int moduleWidth = 140;
        int moduleHeight = 40;
        int gap = 10;
        
        int currentX = moduleAreaX;
        int currentY = moduleAreaY + (int)scrollY;
        
        List<Module> modules = FutureClient.INSTANCE.moduleManager.getModulesByCategory(selectedCategory);
        
        for (Module mod : modules) {
            // Simple culling to avoid drawing outside the window
            if (currentY > centerY - 50 && currentY < centerY + WINDOW_HEIGHT) {
                drawModuleCard(mod, currentX, currentY, moduleWidth, moduleHeight, mouseX, mouseY);
            }

            // Grid Logic (3 columns)
            currentX += moduleWidth + gap;
            if (currentX + moduleWidth > centerX + WINDOW_WIDTH) {
                currentX = moduleAreaX;
                currentY += moduleHeight + gap;
            }
        }
        
        // Handle Scroll (Mouse Wheel)
        int dWheel = Mouse.getDWheel();
        if (dWheel != 0) {
            scrollY += dWheel > 0 ? 20 : -20;
            if (scrollY > 0) scrollY = 0; // Don't scroll too far down
        }
        
        super.render(mouseX, mouseY, partialTicks);
    }

    private void drawModuleCard(Module mod, int x, int y, int w, int h, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
        
        // Background
        int bgColor = new Color(45, 45, 45).getRGB();
        if (hovered) bgColor = new Color(55, 55, 55).getRGB();
        if (mod.isEnabled()) bgColor = new Color(40, 60, 40).getRGB(); // Greenish tint if enabled
        
        // Border
        int borderColor = mod.isEnabled() ? new Color(0, 255, 0).getRGB() : new Color(60, 60, 60).getRGB();
        
        RenderUtil.drawBorderedRect(x, y, w, h, 1, bgColor, borderColor);
        
        // Text
        mc.textRenderer.drawWithShadow(mod.getName(), x + 5, y + 5, -1);
        
        // Description (Smaller / Grey)
        String desc = mod.getName().equals("ToggleSprint") ? "Sprint toggle" : "Settings..."; 
        mc.textRenderer.drawWithShadow(desc, x + 5, y + 20, new Color(150, 150, 150).getRGB());
        
        // Toggle Switch Visual
        int switchW = 20;
        int switchH = 10;
        int switchX = x + w - switchW - 5;
        int switchY = y + 5;
        
        int switchColor = mod.isEnabled() ? new Color(0, 255, 0).getRGB() : new Color(100, 100, 100).getRGB();
        RenderUtil.drawRect(switchX, switchY, switchW, switchH, switchColor);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int centerX = this.width / 2 - WINDOW_WIDTH / 2;
        int centerY = this.height / 2 - WINDOW_HEIGHT / 2;

        // 1. Check Category Clicks
        if (mouseX >= centerX && mouseX <= centerX + SIDEBAR_WIDTH) {
            int catY = centerY + 40;
            for (Category cat : Category.values()) {
                if (mouseY >= catY && mouseY < catY + 25) {
                    selectedCategory = cat;
                    scrollY = 0; // Reset scroll
                    return; // Handled
                }
                catY += 25;
            }
        }

        // 2. Check Module Clicks
        int moduleAreaX = centerX + SIDEBAR_WIDTH + 10;
        int moduleAreaY = centerY + 10;
        int moduleWidth = 140;
        int moduleHeight = 40;
        int gap = 10;
        
        int currentX = moduleAreaX;
        int currentY = moduleAreaY + (int)scrollY;
        
        List<Module> modules = FutureClient.INSTANCE.moduleManager.getModulesByCategory(selectedCategory);
        
        for (Module mod : modules) {
            if (mouseX >= currentX && mouseX <= currentX + moduleWidth && 
                mouseY >= currentY && mouseY <= currentY + moduleHeight) {
                
                if (mouseButton == 0) { // Left Click -> Toggle
                    mod.toggle();
                }
                return;
            }

            currentX += moduleWidth + gap;
            if (currentX + moduleWidth > centerX + WINDOW_WIDTH) {
                currentX = moduleAreaX;
                currentY += moduleHeight + gap;
            }
        }
        
        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        } catch (Exception e) {}
    }

    @Override
    public boolean shouldPauseGame() {
        return false;
    }
}
