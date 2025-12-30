package com.futureclient.gui.screen;

import com.futureclient.FutureClient;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUIScreen extends Screen {
    public ClickGUIScreen() { super(Text.of("ClickGUI")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context); // Dark overlay
        
        int startX = 50;
        int startY = 30;
        int width = 110;
        
        // Render Columns for each Category
        for (Category category : Category.values()) {
            // Header
            context.fill(startX, startY, startX + width, startY + 20, 0xFF111111);
            context.drawCenteredTextWithShadow(textRenderer, category.name(), startX + width / 2, startY + 6, 0xFF55FFFF);

            int modY = startY + 22;
            List<Module> modules = FutureClient.moduleManager.getModulesByCategory(category);
            
            for (Module m : modules) {
                // Hover effect
                boolean hovered = mouseX >= startX && mouseX <= startX + width && mouseY >= modY && mouseY <= modY + 18;
                int color = m.isEnabled() ? 0xFF00AA00 : (hovered ? 0xFF444444 : 0xFF222222);

                context.fill(startX, modY, startX + width, modY + 18, color);
                context.drawText(textRenderer, m.getName(), startX + 5, modY + 5, -1, false);
                modY += 19;
            }
            startX += width + 20;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left Click Toggle
            int startX = 50;
            int startY = 30;
            int width = 110;
            
            for (Category category : Category.values()) {
                int modY = startY + 22;
                for (Module m : FutureClient.moduleManager.getModulesByCategory(category)) {
                    if (mouseX >= startX && mouseX <= startX + width && mouseY >= modY && mouseY <= modY + 18) {
                        m.toggle();
                        return true;
                    }
                    modY += 19;
                }
                startX += width + 20;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean shouldPause() { return false; }
}
