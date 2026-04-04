package dev.nanite.dsp.fabric.platform;

import dev.nanite.dsp.platform.Platform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PlatformFabric implements Platform {
    @Override
    public Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
