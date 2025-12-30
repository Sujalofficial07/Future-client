package com.futureclient.gui.screen;

import com.futureclient.FutureClient;
import com.futureclient.gui.RenderUtils;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUIScreen extends Screen {
    
    private Category selectedCategory = Category.COMBAT; // Default category
    private final int GUI_WIDTH = 350;
    private final int GUI_HEIGHT = 250;

    public ClickGUIScreen() { super(Text.of("Lunar ClickGUI")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Dark Overlay Background
        this.renderBackground(context);

        // Center Calculations
        int startX = (width - GUI_WIDTH) / 2;
        int startY = (height - GUI_HEIGHT) / 2;

        // 2. Main GUI Background (Dark Grey)
        RenderUtils.drawRect(context, startX, startY, GUI_WIDTH, GUI_HEIGHT, RenderUtils.COLOR_BACKGROUND);
        
        // 3. Left Sidebar (Lighter Grey)
        int sidebarWidth = 80;
        RenderUtils.drawRect(context, startX, startY, sidebarWidth, GUI_HEIGHT, RenderUtils.COLOR_SECONDARY);

        // 4. Render Categories (Sidebar)
        int catY = startY + 20;
        for (Category cat : Category.values()) {
            boolean isSelected = (cat == selectedCategory);
            int textColor = isSelected ? RenderUtils.COLOR_ACCENT : 0xFFAAAAAA;
            
            // Indicator Bar for selected
            if (isSelected) {
                RenderUtils.drawRect(context, startX, catY - 4, 3, 16, RenderUtils.COLOR_ACCENT);
            }

            context.drawText(textRenderer, cat.name(), startX + 10, catY, textColor, false);
            catY += 30; // Spacing
        }

        // 5. Render Modules (Grid Layout in Right Panel)
        int modX = startX + sidebarWidth + 10;
        int modY = startY + 10;
        List<Module> modules = FutureClient.moduleManager.getModulesByCategory(selectedCategory);
        
        for (Module m : modules) {
            // Module Box Dimensions
            int boxW = 120;
            int boxH = 25;

            // Hover Check
            boolean hovered = mouseX >= modX && mouseX <= modX + boxW && mouseY >= modY && mouseY <= modY + boxH;
            
            // Logic for Color
            int boxColor = m.isEnabled() ? 0xFF28A5F5 : (hovered ? 0xFF353535 : 0xFF2A2A2A); // Blue if On, Grey if Off
            int borderColor = 0xFF444444;

            // Draw Module Box
            RenderUtils.drawBorderedRect(context, modX, modY, boxW, boxH, boxColor, borderColor);
            
            // Draw Module Name
            context.drawText(textRenderer, m.getName(), modX + 5, modY + 8, -1, true);

            // Toggle State Circle (Visual only)
            int circleColor = m.isEnabled() ? 0xFFFFFFFF : 0xFF888888;
            context.fill(modX + boxW - 15, modY + 8, modX + boxW - 7, modY + 16, circleColor);

            modY += 30; // Next row (Simple vertical list for now, can be grid)
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int startX = (width - GUI_WIDTH) / 2;
        int startY = (height - GUI_HEIGHT) / 2;
        
        // Handle Category Clicks
        int sidebarWidth = 80;
        if (mouseX >= startX && mouseX <= startX + sidebarWidth) {
             int catY = startY + 20;
             for (Category cat : Category.values()) {
                 if (mouseY >= catY - 5 && mouseY <= catY + 15) {
                     selectedCategory = cat;
                     return true;
                 }
                 catY += 30;
             }
        }

        // Handle Module Clicks
        int modX = startX + sidebarWidth + 10;
        int modY = startY + 10;
        List<Module> modules = FutureClient.moduleManager.getModulesByCategory(selectedCategory);
        
        for (Module m : modules) {
            int boxW = 120;
            int boxH = 25;
            if (mouseX >= modX && mouseX <= modX + boxW && mouseY >= modY && mouseY <= modY + boxH) {
                m.toggle();
                // Play sound
                client.getSoundManager().play(net.minecraft.client.sound.PositionedSoundInstance.master(net.minecraft.sound.SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
            modY += 30;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean shouldPause() { return false; }
}
