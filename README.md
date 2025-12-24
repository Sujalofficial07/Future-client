```markdown
# FutureClient (Minecraft 1.8.9, Fabric Legacy / Mixin-based)

FutureClient is a high-performance Minecraft Java Edition PvP client designed for fluid frame rates, minimal latency, and a modular architecture suitable for competitive play and future expansion. It targets Minecraft 1.8.9 and uses Mixin-based hooks for low-level integration.

Key goals:
- Fast, smooth rendering and HUD updates
- Modular system with easy-to-add modules
- Mixins for minimal runtime overhead
- Disk-backed config for module persistence
- Headless CI build via GitHub Actions

## Project layout

- src/main/java - Java source
  - com.futureclient.core - core systems (ModuleManager, Category)
  - com.futureclient.settings - setting types (boolean, number)
  - com.futureclient.modules - implemented modules (Keystrokes, CPS, FPS, Ping, etc.)
  - com.futureclient.gui - click GUI
  - com.futureclient.mixins - Mixins hooking Minecraft classes
  - com.futureclient.config - config handling (save/load JSON)
- src/main/resources
  - fabric.mod.json - mod metadata
  - mixins.futureclient.json - Mixin configuration
  - assets/futureclient/config/default_config.json - example config

## Features implemented

Core:
- Module manager with enable/disable and categories
- Keybind system (toggle modules by key)
- Simple event bus for cross-module communication

Modules:
- Keystrokes (W A S D + mouse buttons)
- CPS counter
- FPS display
- Ping display
- Coordinates HUD
- Toggle sprint (auto-sprint)
- Zoom (smooth FOV change)
- Particle reducer / disabler
- Entity render optimizations (skips distant entity rendering)

Mixins:
- MixinMinecraft: handles startup, tick injection, input
- MixinGuiIngame: custom HUD draw
- MixinEntityRenderer: FOV/zoom control and render optimizations
- MixinGameSettings: add keybind handling hook
- MixinEffectRenderer: intercept particle spawn

GUI:
- In-game Click GUI drawn using Minecraft rendering
- Sidebar categories, module list, toggles, smooth animations

Config:
- JSON-configured module states and settings
- Auto-load on startup and save on change

CI:
- GitHub Actions workflow (Java 8 + Gradle)
- Produces a single JAR artifact

## How it works (high level)

- Main initializes ModuleManager, KeybindManager, EventBus, and ConfigManager.
- Mixins inject into Minecraft's tick/render code to minimize overhead and route input/HUD drawing to FutureClient code paths.
- Modules are simple objects with onEnable/onDisable/onTick/onRender hooks.
- Settings are stored as typed objects, persisted to JSON automatically.

## Adding a new module

1. Create a new class in `com.futureclient.modules` that extends `com.futureclient.core.Module`.
2. Implement your enable/disable logic and override `onUpdate()` (tick-level) or `onRender()` for HUD.
3. Add any `Setting` fields (BooleanSetting / NumberSetting) to the module and register them in the constructor.
4. Register the module in `ModuleManager` (ModuleManager registers built-in modules in `init()`).

Example skeleton:
```java
public class MyModule extends Module {
    public MyModule() {
        super("MyModule", Category.MISC, Keyboard.KEY_NONE);
        this.addSetting(new BooleanSetting("EnabledFeature", true));
    }

    @Override
    public void onUpdate() {
        // tick logic
    }

    @Override
    public void onRender() {
        // render logic
    }
}
```

## Building locally

Requirements:
- Java 8 (target compatibility)
- Gradle wrapper (provided)

Build:
- On *nix: `./gradlew build`
- On Windows: `gradlew.bat build`

Output:
- Build artifact located in `build/libs/` (a jar containing the mod classes).

## GitHub Actions

A workflow is included: `.github/workflows/build.yml` — it uses Java 8, runs `./gradlew build`, and uploads the produced `jar` as an artifact.

## Running the client

This project produces a mod JAR that should be placed into the mods folder of a Fabric Legacy / Mixin-compatible 1.8.9 environment that supports Mixins and Fabric-Legacy loaders. For development, set up your preferred run configuration to launch Minecraft 1.8.9 with Mixins enabled and add the built JAR to the mods directory.

## Notes and future work

- This repository focuses on architecture, performance-conscious patterns, and example modules.
- Optimizations prioritize skipping heavy work when disabled and caching repeated calculations.
- Future improvements: plugin API, more GUI polish, additional PvP modules (AutoClicker, BlockUtils), better mapping abstraction for multiple MCP mappings.

License: MIT (Add a LICENSE file if you plan to publish)
```