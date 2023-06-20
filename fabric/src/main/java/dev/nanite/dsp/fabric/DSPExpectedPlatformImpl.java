package dev.nanite.dsp.fabric;


import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class DSPExpectedPlatformImpl {
    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
