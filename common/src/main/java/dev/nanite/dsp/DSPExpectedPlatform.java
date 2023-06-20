package dev.nanite.dsp;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class DSPExpectedPlatform {
    @ExpectPlatform
    public static Path getGameDir() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
