# FutureClient

A high-performance PvP and HUD client mod for Minecraft 1.8.9, built with Fabric. Optimized for low-end devices and mobile launchers like PojavLauncher and Mojo.

![Build Status](https://github.com/sujalofficial07/futureclient/workflows/Build%20FutureClient/badge.svg)

## Features

### 🎮 HUD Modules
- **FPS Counter** - Display current frames per second with color-coded performance indicators
- **Keystrokes** - Show WASD and mouse button presses with CPS tracking
- **Coordinates** - Display your current position (X, Y, Z)
- **Ping Display** - Show your connection latency with color indicators
- **CPS Counter** - Track clicks per second for both mouse buttons

### 🏃 Movement Enhancements
- **Toggle Sprint** - Automatically sprint when moving forward

### 👁️ Visual Modifications
- **Smooth Zoom** - Cinematic zoom with smooth transitions (Default: C key)

### ⚡ Performance Optimizations
- **Particle Reducer** - Reduce particle count by 66% for better FPS
- **Animation Toggle** - Disable animations for performance gains

## Requirements

- **Minecraft**: 1.8.9 (Java Edition)
- **Fabric Loader**: 0.14.21 or higher
- **Java**: 8 (JDK 1.8)
- **Legacy Fabric API**: 1.7.4+1.8.9 (included as dependency)

## Installation

### Desktop (Windows/Mac/Linux)

1. **Install Fabric Loader**
   - Download and run the [Fabric Installer](https://fabricmc.net/use/)
   - Select Minecraft version 1.8.9
   - Click "Install"

2. **Install Legacy Fabric API**
   - Download Legacy Fabric API from [Modrinth](https://modrinth.com/mod/legacy-fabric-api) or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/legacy-fabric-api)
   - Place the JAR file in your `.minecraft/mods` folder

3. **Install FutureClient**
   - Download the latest `futureclient-1.0.0.jar` from [Releases](https://github.com/yourusername/futureclient/releases)
   - Place it in your `.minecraft/mods` folder
   - Launch Minecraft 1.8.9 with the Fabric profile

### Mobile (PojavLauncher / Mojo)

1. **Install PojavLauncher** from Google Play Store or GitHub
2. **Set up Fabric for 1.8.9** in PojavLauncher
3. **Add mods** to your instance's mods folder:
   - Legacy Fabric API
   - FutureClient JAR
4. **Launch** and enjoy!

## Controls

| Action | Default Key | Description |
|--------|------------|-------------|
| Open Click GUI | `Right Shift` | Opens the module configuration menu |
| Zoom | `C` | Activates smooth zoom |
| Toggle Sprint | Automatic | Sprints when moving forward |

### Keybind Customization
- Open the Click GUI (Right Shift)
- Click on any module
- Press a key to bind it to that module

## Configuration

FutureClient automatically saves your settings to:
```
.minecraft/futureclient/config.json
```

Configuration includes:
- Enabled/disabled modules
- Keybindings
- Module positions (HUD elements)

## Building from Source

### Prerequisites
- JDK 8 installed and configured
- Git

### Build Steps

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/futureclient.git
cd futureclient
```

2. **Build with Gradle**
```bash
./gradlew build
```
On Windows:
```cmd
gradlew.bat build
```

3. **Find the compiled JAR**
```
build/libs/futureclient-1.0.0.jar
```

## Compatibility

### ✅ Tested and Working
- Minecraft 1.8.9 (Client)
- Windows 10/11
- macOS (Intel and Apple Silicon)
- Linux (Ubuntu, Fedora, Arch)
- PojavLauncher (Android)
- Mojo Launcher (Android/iOS)

### ⚠️ Known Limitations
- **Server-side**: This is a client-only mod and doesn't work on servers
- **Forge**: Not compatible with Forge mods (Fabric only)
- **Older Android devices**: May experience lower FPS with all modules enabled

## Performance Tips

For best performance on low-end devices:
1. Enable **Particle Reducer**
2. Enable **Animation Toggle**
3. Disable unused HUD modules
4. Lower Minecraft's render distance
5. Disable fancy graphics in video settings

## Development

### Project Structure
```
FutureClient/
├── src/main/java/com/futureclient/
│   ├── FutureClient.java          # Main entry point
│   ├── api/                        # Module system API
│   ├── core/                       # Core systems (events, config)
│   ├── events/                     # Event definitions
│   ├── gui/                        # Click GUI and HUD editor
│   ├── modules/                    # All client modules
│   ├── mixins/                     # Minecraft mixins
│   └── util/                       # Utility classes
└── src/main/resources/
    ├── fabric.mod.json             # Mod metadata
    ├── futureclient.mixins.json   # Mixin configuration
    └── assets/futureclient/        # Icons and resources
```

### Adding a New Module

```java
package com.futureclient.modules.example;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

public class ExampleModule extends Module {
    public ExampleModule() {
        super("Example", "Example module", Category.HUD);
    }
    
    @Override
    public void onEnable() {
        // Called when module is enabled
    }
    
    @Override
    public void onTick() {
        // Called every game tick
    }
    
    @Override
    public void onRender(float partialTicks) {
        // Called every render frame
    }
}
```

Then register it in `ModuleManager.java`:
```java
registerModule(new ExampleModule());
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Disclaimer

This mod is designed for singleplayer and allowed multiplayer use only. Always check server rules before using client mods. The developers are not responsible for any bans or restrictions resulting from misuse.

## Credits

- **Fabric Team** - For the modding framework
- **Legacy Fabric** - For 1.8.9 support
- **Lunar Client** - UI design inspiration

## Support

- **Issues**: [GitHub Issues](https://github.com/yourusername/futureclient/issues)
- **Discussions**: [GitHub Discussions](https://github.com/yourusername/futureclient/discussions)

## Screenshots

<!-- Add your screenshots here -->
_Coming soon!_

---

**Made with ❤️ for the Minecraft community**
