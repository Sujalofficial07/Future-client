package com.futureclient.gui.components;

import com.futureclient.FutureClient;
import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.ColorUtil;
import com.futureclient.util.RenderUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private final Category category;
    private int x;
    private int y;
    private final int width = 120;
    private final int headerHeight = 20;
    private final int moduleHeight = 16;
    private boolean extended = true;
    private boolean dragging = false;
    private int dragX;
    private int dragY;
    
    private final List<ModuleButton> moduleButtons = new ArrayList<ModuleButton>();
    
    public CategoryPanel(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        
        // Create module buttons
        List<Module> modules = FutureClient.getInstance().getModuleManager().getModulesByCategory(category);
        for (int i = 0; i < modules.size(); i++) {
            moduleButtons.add(new ModuleButton(modules.get(i), x, y + headerHeight + (i * moduleHeight), width, moduleHeight));
        }
    }
    
    public void render(int mouseX, int mouseY) {
        // Update positions if dragging
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
            updateModulePositions();
        }
        
        // Draw header
        int headerColor = isMouseOverHeader(mouseX, mouseY) ? ColorUtil.rgba(60, 60, 60, 200) : ColorUtil.rgba(40, 40, 40, 200);
        RenderUtil.drawRoundedRect(x, y, width, headerHeight, 2, headerColor);
        
        String title = category.getName() + (extended ? " -" : " +");
        RenderUtil.drawString(title, x + 5, y + 6, 0xFFFFFFFF);
        
        // Draw modules if extended
        if (extended) {
            int totalHeight = headerHeight + (moduleButtons.size() * moduleHeight);
            RenderUtil.drawRect(x, y + headerHeight, x + width, y + totalHeight, ColorUtil.rgba(30, 30, 30, 200));
            
            for (ModuleButton button : moduleButtons) {
                button.render(mouseX, mouseY);
            }
        }
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0) { // Left click
            if (isMouseOverHeader(mouseX, mouseY)) {
                dragging = true;
                dragX = mouseX - x;
                dragY = mouseY - y;
            }
        } else if (button == 1) { // Right click
            if (isMouseOverHeader(mouseX, mouseY)) {
                extended = !extended;
            }
        }
        
        if (extended) {
            for (ModuleButton moduleButton : moduleButtons) {
                moduleButton.mouseClicked(mouseX, mouseY, button);
            }
        }
    }
    
    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
    }
    
    private boolean isMouseOverHeader(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + headerHeight;
    }
    
    private void updateModulePositions() {
        for (int i = 0; i < moduleButtons.size(); i++) {
            ModuleButton button = moduleButtons.get(i);
            button.setX(x);
            button.setY(y + headerHeight + (i * moduleHeight));
        }
    }
    
    public int getWidth() {
        return width;
    }
}
