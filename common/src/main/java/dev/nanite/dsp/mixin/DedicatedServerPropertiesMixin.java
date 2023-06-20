package dev.nanite.dsp.mixin;

import dev.nanite.dsp.DSPExpectedPlatform;
import dev.nanite.dsp.DSPMod;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(DedicatedServerProperties.class)
public abstract class DedicatedServerPropertiesMixin {
    @Inject(at = @At("HEAD"), method = "fromFile(Ljava/nio/file/Path;)Lnet/minecraft/server/dedicated/DedicatedServerProperties;", cancellable = true)
    private static void fromFile(Path path, CallbackInfoReturnable<DedicatedServerProperties> info) {
        Path defaultSettingPath = DSPExpectedPlatform.getGameDir().resolve("default-server.properties");
        if (!Files.exists(defaultSettingPath)) {
            DSPMod.LOGGER.info("No default-server.properties exist in the games root path... ignoring default injection");
            return;
        }

        Path localMarkerFile = DSPExpectedPlatform.getGameDir().resolve("local/default-used.marker");
        if (Files.exists(localMarkerFile)) {
            DSPMod.LOGGER.info("Default server properties injection ignored as it has already been run. This is not an error. This is correct behaviour.");
            return;
        }

        if (Files.notExists(localMarkerFile.getParent())) {
            try {
                Files.createDirectories(localMarkerFile.getParent());
            } catch (IOException e) {
                DSPMod.LOGGER.error("Failed to create local dir in the games root path...", e);
                return;
            }
        }

        try {
            Files.writeString(localMarkerFile, "");
        } catch (IOException e) {
            DSPMod.LOGGER.error("Unable to create marker so avoiding default properties injection...", e);
            return;
        }

        // Natively defaults back to the original so if there is no default prop defined then this will just mimic the original creations of the server props file
        DedicatedServerProperties dedicatedServerProperties = new DedicatedServerProperties(DedicatedServerProperties.loadFromFile(defaultSettingPath));
        DSPMod.LOGGER.info("Replacing server properties with default properties!");
        info.setReturnValue(dedicatedServerProperties);
    }
}
