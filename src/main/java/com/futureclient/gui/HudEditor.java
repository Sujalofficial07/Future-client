package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.futureclient.util.ColorUtil;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.gui.screen.Screen;

import java.io.IOException;

/**
 * HUD Editor for dragging and positioning HUD elements
 * (Placeholder implementation - can be extended for full drag-and-drop)
 */
public class HudEditor extends Screen {
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, ColorUtil.rgba(0, 0, 0, 150));
        
        String title = "HUD Editor (Coming Soon)";
        int titleX = (width - RenderUtil.getStringWidth(title)) / 2;
        RenderUtil.drawString(title, titleX, 20, 0xFFFFFFFF);
        
        String info = "Press ESC to return";
        int infoX = (width - RenderUtil.getStringWidth(info)) / 2;
        RenderUtil.drawString(info, infoX, 40, 0xFFAAAAAA);
        
        // Render all enabled HUD modules for preview
        for (Module module : FutureClient.getInstance().getModuleManager().getEnabledModules()) {
            if (module.getCategory() == com.futureclient.api.Category.HUD) {
                module.onRender(partialTicks);
            }
        }
        
        super.render(mouseX, mouseY, partialTicks);
    }
    
    @Override
    protected void keyTyped(char character, int keyCode) throws IOException {
        if (keyCode == 1) { // ESC
            mc.openScreen(null);
        }
        super.keyTyped(character, keyCode);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
