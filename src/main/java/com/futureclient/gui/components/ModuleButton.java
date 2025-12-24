package com.futureclient.gui.components;

import com.futureclient.api.Module;
import com.futureclient.util.ColorUtil;
import com.futureclient.util.RenderUtil;

/**
 * Button for toggling modules in the Click GUI
 */
public class ModuleButton {
    
    private final Module module;
    private int x;
    private int y;
    private final int width;
    private final int height;
    
    public ModuleButton(Module module, int x, int y, int width, int height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void render(int mouseX, int mouseY) {
        boolean hovered = isMouseOver(mouseX, mouseY);
        
        // Background color based on enabled state and hover
        int bgColor;
        if (module.isEnabled()) {
            bgColor = hovered ? ColorUtil.rgba(80, 150, 80, 200) : ColorUtil.rgba(60, 130, 60, 200);
        } else {
            bgColor = hovered ? ColorUtil.rgba(60, 60, 60, 200) : ColorUtil.rgba(40, 40, 40, 200);
        }
        
        RenderUtil.drawRect(x, y, x + width, y + height, bgColor);
        
        // Draw module name
        int textColor = module.isEnabled() ? 0xFFFFFFFF : 0xFFAAAAAA;
        RenderUtil.drawString(module.getName(), x + 5, y + 4, textColor);
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0 && isMouseOver(mouseX, mouseY)) {
            module.toggle();
        }
    }
    
    private boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
