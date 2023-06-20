package dev.nanite.dsp.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class DSPExpectedPlatformImpl {
    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }
}
