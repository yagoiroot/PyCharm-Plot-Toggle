# Plot Toggle — PyCharm Plugin

A simple PyCharm plugin that adds a toolbar toggle button to control the **Show plots in tool window** setting (Settings > Tools > Python Plots) without opening the settings dialog.

## What It Does

When working with matplotlib in PyCharm Professional, plots can either appear inline in the console or in a dedicated tool window. Switching between these modes normally requires navigating through Settings > Tools > Python Plots. This plugin adds a one-click toggle to the main toolbar and the Tools menu.

- **Button ON** (checked): Plots appear in the PyCharm Plots tool window.
- **Button OFF** (unchecked): Plots appear inline in the Python console.

## Requirements

- **PyCharm Professional** 2024.3+ (build 243+)
- The Python Scientific mode / matplotlib support must be available.

## Installation

### From JetBrains Marketplace

Search for **Plot Toggle** in Settings > Plugins > Marketplace.

### Manual Install

1. Download the latest `.zip` from the [Releases](../../releases) page.
2. In PyCharm, go to Settings > Plugins > gear icon > Install Plugin from Disk.
3. Select the downloaded `.zip` and restart PyCharm.

## Usage

After installation, the toggle appears in two places:

1. **Main Toolbar** (right side) — a toggle icon.
2. **Tools menu** — "Plots in Tool Window" entry at the bottom.

Click to toggle. The button is only visible/enabled when the Python Scientific module is available.

## Building from Source

```bash
./gradlew buildPlugin
```

The built plugin `.zip` will be in `build/distributions/`.

## License

[MIT](LICENSE)
