package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.api.Category;
import com.futureclient.gui.components.CategoryPanel;
import com.futureclient.util.ColorUtil;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGui extends GuiScreen {
    private final List<CategoryPanel> panels = new ArrayList<CategoryPanel>();
    private int startX = 50;
    private int startY = 50;
    private final int panelSpacing = 10;
    
    public ClickGui() {
        int x = startX;
        
        for (Category category : Category.values()) {
            CategoryPanel panel = new CategoryPanel(category, x, startY);
            panels.add(panel);
            x += panel.getWidth() + panelSpacing;
        }
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw semi-transparent background
        drawRect(0, 0, width, height, ColorUtil.rgba(0, 0, 0, 100));
        
        // Draw title
        String title = "FutureClient v" + FutureClient.VERSION;
        int titleX = (width - RenderUtil.getStringWidth(title)) / 2;
        RenderUtil.drawString(title, titleX, 20, 0xFFFFFFFF);
        
        // Render all panels
        for (CategoryPanel panel : panels) {
            panel.render(mouseX, mouseY);
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
        for (CategoryPanel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, button);
        }
        
        super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int button) {
        for (CategoryPanel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    @Override
    protected void keyTyped(char character, int keyCode) throws IOException {
        if (keyCode == 1) { // ESC key
            mc.displayGuiScreen(null);
            FutureClient.getInstance().getConfigManager().save();
        }
        
        super.keyTyped(character, keyCode);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
