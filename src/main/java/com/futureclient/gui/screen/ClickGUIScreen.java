package com.futureclient.gui.screen;

import com.futureclient.FutureClient;
import com.futureclient.gui.RenderUtils;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class ClickGUIScreen extends Screen {

    private Category selectedCategory = Category.COMBAT;
    
    // GUI Dimensions
    private final int GUI_WIDTH = 500;
    private final int GUI_HEIGHT = 350;
    private int startX, startY;

    public ClickGUIScreen() { super(Text.of("Mod Menu")); }

    @Override
    protected void init() {
        // Calculate center position
        this.startX = (width - GUI_WIDTH) / 2;
        this.startY = (height - GUI_HEIGHT) / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Dark Background Overlay (Blur effect)
        this.renderBackground(context);

        // 2. Main Container (Rounded Box look)
        RenderUtils.drawRect(context, startX, startY, GUI_WIDTH, GUI_HEIGHT, RenderUtils.BG_DARK);

        // 3. Sidebar (Left Side)
        int sidebarWidth = 120;
        RenderUtils.drawRect(context, startX, startY, sidebarWidth, GUI_HEIGHT, RenderUtils.BG_SIDEBAR);

        // 4. Logo / Title
        context.drawCenteredTextWithShadow(textRenderer, "FUTURE", startX + sidebarWidth / 2, startY + 20, RenderUtils.ACCENT);
        
        // 5. Render Categories (Sidebar)
        int catY = startY + 60;
        for (Category cat : Category.values()) {
            boolean isSelected = (cat == selectedCategory);
            int textColor = isSelected ? 0xFFFFFFFF : 0xFF888888;
            
            // Selection Indicator (Blue line on left)
            if (isSelected) {
                RenderUtils.drawRect(context, startX, catY - 5, 2, 20, RenderUtils.ACCENT);
            }

            context.drawText(textRenderer, cat.name(), startX + 20, catY, textColor, false);
            catY += 40; // Spacing
        }

        // 6. Top Bar (Search & Info)
        int mainContentX = startX + sidebarWidth;
        RenderUtils.drawRect(context, mainContentX, startY, GUI_WIDTH - sidebarWidth, 50, 0xFF222222); // Header bar
        context.drawText(textRenderer, "Mods (" + selectedCategory.name() + ")", mainContentX + 20, startY + 20, -1, true);

        // 7. Render Modules (Grid Layout)
        int modX = mainContentX + 20;
        int modY = startY + 70;
        int gap = 15;
        int cardWidth = 160;
        int cardHeight = 40;
        
        List<Module> modules = FutureClient.moduleManager.getModulesByCategory(selectedCategory);
        
        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            
            // Grid Logic (2 Columns)
            int col = i % 2; 
            int row = i / 2;
            int currentX = modX + (col * (cardWidth + gap));
            int currentY = modY + (row * (cardHeight + gap));

            // Hover Check
            boolean hovered = mouseX >= currentX && mouseX <= currentX + cardWidth && mouseY >= currentY && mouseY <= currentY + cardHeight;

            // Card Background
            int cardColor = hovered ? 0xFF353535 : RenderUtils.MOD_OFF;
            RenderUtils.drawRect(context, currentX, currentY, cardWidth, cardHeight, cardColor);

            // Module Name
            context.drawText(textRenderer, m.getName(), currentX + 10, currentY + 10, -1, true);
            
            // Module Description (Small grey text)
            // context.drawText(textRenderer, "Settings >", currentX + 10, currentY + 25, 0xFF888888, false);

            // Toggle Switch
            RenderUtils.drawToggle(context, currentX + cardWidth - 30, currentY + 14, m.isEnabled());
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Handle Sidebar Clicks
        int sidebarWidth = 120;
        if (mouseX >= startX && mouseX <= startX + sidebarWidth) {
             int catY = startY + 60;
             for (Category cat : Category.values()) {
                 if (mouseY >= catY - 10 && mouseY <= catY + 25) {
                     selectedCategory = cat;
                     // Sound
                     client.getSoundManager().play(net.minecraft.client.sound.PositionedSoundInstance.master(net.minecraft.sound.SoundEvents.UI_BUTTON_CLICK, 1.0F));
                     return true;
                 }
                 catY += 40;
             }
        }

        // Handle Module Clicks
        int mainContentX = startX + sidebarWidth;
        int modX = mainContentX + 20;
        int modY = startY + 70;
        int gap = 15;
        int cardWidth = 160;
        int cardHeight = 40;
        
        List<Module> modules = FutureClient.moduleManager.getModulesByCategory(selectedCategory);
        
        for (int i = 0; i < modules.size(); i++) {
            int col = i % 2; 
            int row = i / 2;
            int currentX = modX + (col * (cardWidth + gap));
            int currentY = modY + (row * (cardHeight + gap));

            if (mouseX >= currentX && mouseX <= currentX + cardWidth && mouseY >= currentY && mouseY <= currentY + cardHeight) {
                modules.get(i).toggle();
                client.getSoundManager().play(net.minecraft.client.sound.PositionedSoundInstance.master(net.minecraft.sound.SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean shouldPause() { return false; }
}
