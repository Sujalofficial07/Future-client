package com.futureclient.gui.screen;

import com.futureclient.gui.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {

    public MainMenuScreen() {
        super(Text.of("Future Main Menu"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Background (Lunar Gradient Style - Placeholder Dark Grey)
        this.renderBackground(context); 
        context.fill(0, 0, width, height, 0xFF111111); // Solid Dark Background

        // 2. Center Logo
        context.drawCenteredTextWithShadow(textRenderer, "FUTURE CLIENT", width / 2, height / 4, RenderUtils.COLOR_ACCENT);
        context.drawCenteredTextWithShadow(textRenderer, "Fabric 1.20.1 Edition", width / 2, height / 4 + 15, 0xFFAAAAAA);

        // 3. Draw Buttons (Centered)
        int buttonWidth = 140;
        int buttonHeight = 24;
        int centerX = width / 2 - buttonWidth / 2;
        int startY = height / 2 - 20;

        // Button 1: Singleplayer
        drawLunarButton(context, "SINGLEPLAYER", centerX, startY, buttonWidth, buttonHeight, mouseX, mouseY);

        // Button 2: Multiplayer
        drawLunarButton(context, "MULTIPLAYER", centerX, startY + 30, buttonWidth, buttonHeight, mouseX, mouseY);

        // Button 3: Options
        drawLunarButton(context, "OPTIONS", centerX, startY + 60, buttonWidth, buttonHeight, mouseX, mouseY);

        // Button 4: Quit
        drawLunarButton(context, "QUIT GAME", centerX, startY + 90, buttonWidth, buttonHeight, mouseX, mouseY);
        
        // Footer
        context.drawText(textRenderer, "Copyright Mojang AB. Do not distribute!", 5, height - 15, 0xFF555555, false);
    }

    private void drawLunarButton(DrawContext context, String text, int x, int y, int w, int h, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
        
        // Button Background (Changes on hover)
        int color = hovered ? 0xFF333333 : 0xFF202020;
        int borderColor = hovered ? RenderUtils.COLOR_ACCENT : 0xFF444444;

        RenderUtils.drawBorderedRect(context, x, y, w, h, color, borderColor);
        
        // Centered Text
        context.drawCenteredTextWithShadow(textRenderer, text, x + w / 2, y + (h - 8) / 2, -1);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int buttonWidth = 140;
        int buttonHeight = 24;
        int centerX = width / 2 - buttonWidth / 2;
        int startY = height / 2 - 20;

        // Simple Click Logic based on Y position
        if (isHovered(mouseX, mouseY, centerX, startY, buttonWidth, buttonHeight)) {
            client.setScreen(new SelectWorldScreen(this));
        } else if (isHovered(mouseX, mouseY, centerX, startY + 30, buttonWidth, buttonHeight)) {
            client.setScreen(new MultiplayerScreen(this));
        } else if (isHovered(mouseX, mouseY, centerX, startY + 60, buttonWidth, buttonHeight)) {
            client.setScreen(new OptionsScreen(this, client.options));
        } else if (isHovered(mouseX, mouseY, centerX, startY + 90, buttonWidth, buttonHeight)) {
            client.scheduleStop();
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isHovered(double mx, double my, int x, int y, int w, int h) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }
}
