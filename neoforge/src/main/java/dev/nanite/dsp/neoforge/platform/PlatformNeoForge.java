package dev.nanite.dsp.neoforge.platform;

import dev.nanite.dsp.platform.Platform;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlatformNeoForge implements Platform {
    @Override
    public Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }
}
