package dev.nanite.dsp.platform;

import java.nio.file.Path;
import java.util.ServiceLoader;

public interface Platform {
    Platform INSTANCE = ServiceLoader.load(Platform.class).findFirst().orElseThrow();

    Path getGameDir();
}
