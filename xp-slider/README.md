# XP Multiplier Slider (Fabric, Minecraft 26.3)

Adds an "XP Multiplier: Nx" slider (1-100) to the top-left of the Options screen.
Applies only in singleplayer. Saved in config/xp-multiplier-slider.json.

## Build

Option A - locally (needs JDK 25 + Gradle 9.6):
    gradle build
The jar is in build/libs/ (use the one WITHOUT "-sources").

Option B - no installs: push this folder to a GitHub repo, open the Actions tab,
run "build", and download the artifact.

## Install
Put the jar and Fabric API (+26.3) in .minecraft/mods. Needs Fabric Loader 0.19+ and Java 25.
