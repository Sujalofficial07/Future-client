# FutureClient

<p align="center">
  <img src="icon.png" alt="FutureClient Logo" width="180"/>
</p>

<p align="center">
  <strong>High-performance PvP client for Minecraft Java Edition</strong><br>
  Fabric Legacy (Mixin-based) • Minecraft 1.8.9
</p>

  <!-- Latest Release -->
<p align="center">
  <!-- Build -->
  <a href="https://github.com/Sujalofficial07/Future-Client/actions">
    <img src="https://img.shields.io/github/actions/workflow/status/Sujalofficial07/Future-Client/build.yml?branch=main&style=flat-square&logo=github&logoColor=white">
  </a>

  <!-- Release -->
  <a href="https://github.com/Sujalofficial07/Future-Client/releases">
    <img src="https://img.shields.io/github/v/release/Sujalofficial07/Future-Client?style=flat-square&logo=github">
  </a>

  <!-- Java -->
  <img src="https://img.shields.io/badge/Java-8-red?style=flat-square&logo=java&logoColor=white">

  <!-- Minecraft -->
  <img src="https://img.shields.io/badge/Minecraft-1.8.9-62B47A?style=flat-square&logo=minecraft&logoColor=white">

  <!-- Fabric -->
  <img src="https://img.shields.io/badge/Fabric-Legacy-1E90FF?style=flat-square">

  <!-- Platform -->
  <img src="https://img.shields.io/badge/Platform-Windows%20|%20Linux%20|%20macOS-lightgrey?style=flat-square">

  <!-- License -->
  <a href="https://github.com/Sujalofficial07/FutureClient/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/Sujalofficial07/FutureClient?style=flat-square">
  </a>
</p>

---

## 🚀 Overview

**FutureClient** is a fast, modular, and highly optimized PvP client for  
**Minecraft Java Edition 1.8.9**, built using **Fabric Legacy** and **Mixin-based optimizations**.

The client is designed for **competitive gameplay**, delivering smooth visuals, low latency, and clean modular features comparable to top PvP clients.

---

## 🧠 Technology Stack

- **Language:** Java 8  
- **Build Tool:** Gradle  
- **Mod Loader:** Fabric Legacy (Mixin-based, no Forge)  
- **Rendering:** LWJGL (Minecraft native renderer)  
- **CI/CD:** GitHub Actions (headless builds)

---

## 📦 Modules

### HUD
- **Keystrokes** (W A S D + mouse buttons)
- **CPS Counter**
- **FPS Display**
- **Ping Display**
- **Coordinates HUD**

### PvP
- **Toggle Sprint**
- **Zoom** (smooth FOV transitions)

### Performance
- **Particle reducer / disabler**
- **Basic entity render optimizations**

All modules are toggleable and optimized for minimal performance overhead.

---

## 🧬 Mixins & Optimizations

FutureClient uses **Mixins** to:

- Improve rendering performance
- Reduce unnecessary calculations
- Optimize HUD drawing
- Handle key input efficiently
- Reduce or disable costly particle effects

These optimizations result in **higher and more stable FPS**, especially during PvP.

---

## 🖱 Click GUI

- Fully custom in-game GUI
- Drawn using Minecraft rendering (no Swing / JavaFX)
- Sidebar category navigation
- Module list with toggle buttons
- Smooth animations:
  - Fade
  - Scale
  - Slide

---

## ⚡ Performance Philosophy

FutureClient focuses on **real performance gains**:

- Reduced particle rendering
- Skipped unnecessary entity renders
- Cached repeated calculations
- Optimized HUD rendering
- Minimal memory allocation

Built for **low-end systems and competitive play**.

---

## 💾 Configuration System

- Module states and settings saved to **JSON**
- Automatically loaded on startup
- Easily extendable for future modules

---

## 🛠 Building the Client

### Requirements
- Java 8 JDK
- Internet connection (for dependencies)

### Build
```bash
./gradlew build
```
## ⭐ Credits

- Minecraft Java Edition (Mojang)
- Fabric & Fabric Legacy contributors
- SpongePowered Mixin
