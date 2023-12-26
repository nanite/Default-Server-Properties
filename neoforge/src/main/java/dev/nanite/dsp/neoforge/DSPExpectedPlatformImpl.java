package dev.nanite.dsp.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class DSPExpectedPlatformImpl {
    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }
}
