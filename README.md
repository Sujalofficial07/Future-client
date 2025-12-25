# FutureClient (1.8.9 Fabric)

A high-performance, open-source PvP client for Minecraft 1.8.9 built on the Legacy Fabric toolchain.

## Features
- **Modules**: ToggleSprint, Zoom, HUD (FPS, XYZ), Keystrokes.
- **Performance**: Particle optimization, Mixin-based rendering hooks.
- **GUI**: Custom ClickGUI (Right Shift).
- **Config**: JSON-based configuration system.

## Installation
1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for 1.8.9.
2. Place the built jar into `.minecraft/mods`.
3. Launch Minecraft.

## Building
1. Clone repository.
2. Run `./gradlew build`.
3. Artifacts located in `build/libs/`.

## Architecture
- **Mixins**: Used to hook into `MinecraftClient` and `EntityRenderer`.
- **Events**: Custom EventBus system (`EventUpdate`, `EventRender2D`).
- **Render**: Uses standard GL11 and Minecraft 1.8.9 rendering methods.
