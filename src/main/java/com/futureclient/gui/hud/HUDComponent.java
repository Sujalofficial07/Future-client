package com.futureclient.gui.hud;

import net.minecraft.client.gui.DrawContext;

public abstract class HUDComponent {
    protected String name;
    protected int x, y;
    protected boolean enabled = true;

    public HUDComponent(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract void render(DrawContext context, float delta);
    public boolean isEnabled() { return enabled; }
}
