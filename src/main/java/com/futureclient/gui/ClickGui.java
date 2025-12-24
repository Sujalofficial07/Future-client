package com.futureclient.gui;

import com.futureclient.Main;
import com.futureclient.core.Category;
import com.futureclient.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Basic click GUI drawn using Minecraft's GUI routines.
 * This class is intentionally simple but demonstrates category sidebar, module list, and toggles.
 */
public class ClickGui extends GuiScreen {
    private final Minecraft mc = Minecraft.getMinecraft();
    private Category selected = Category.PVP;
    private float anim = 0f;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Background fade
        drawRect(0, 0, width, height, 0x90000000);

        // Sidebar
        int sideW = 110;
        drawRect(10, 10, 10 + sideW, height - 10, 0xFF1E1E1E);
        int y = 20;
        for (Category c : Category.values()) {
            boolean sel = c == selected;
            int bg = sel ? 0xFF2A2A2A : 0x001E1E1E;
            drawRect(12, y, 12 + sideW - 4, y + 24, bg);
            GuiElements.drawStringScaled(c.name(), 20, y + 6, 1.0f, sel ? 0xFFFFFF : 0xBBBBBB);
            y += 28;
        }

        // Modules list
        int listX = 140;
        int listY = 20;
        int listW = width - listX - 20;
        drawRect(listX, listY, listX + listW, height - 20, 0x80111111);
        List<Module> modules = Main.getModuleManager().getModules();
        int mx = listX + 6;
        int my = listY + 6;
        for (Module m : modules) {
            if (m.getCategory() != selected) continue;
            boolean enabled = m.isEnabled();
            int bg = enabled ? 0xFF1F8BFF : 0xFF2A2A2A;
            drawRect(mx, my, mx + listW - 12, my + 20, bg);
            mc.fontRendererObj.drawStringWithShadow(m.getName(), mx + 4, my + 6, enabled ? 0xFFFFFF : 0xCCCCCC);
            my += 26;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        // minimal: click to toggle modules
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }
}