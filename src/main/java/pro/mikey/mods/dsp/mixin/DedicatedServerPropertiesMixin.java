package pro.mikey.mods.dsp.mixin;

import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraftforge.fml.loading.FMLPaths;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pro.mikey.mods.dsp.DSPInit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(DedicatedServerProperties.class)
public abstract class DedicatedServerPropertiesMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/server/dedicated/DedicatedServerProperties;fromFile(Ljava/nio/file/Path;)Lnet/minecraft/server/dedicated/DedicatedServerProperties;", cancellable = true)
    private static DedicatedServerProperties fromFile(Path path, CallbackInfoReturnable<DedicatedServerProperties> info) {
        Path defaultSettingPath = FMLPaths.GAMEDIR.get().resolve("default-server.properties");
        if (!Files.exists(defaultSettingPath)) {
            DSPInit.LOGGER.info("No default-server.properties exist in the games root path... ignoring default injection");
            return info.getReturnValue();
        }

        Path localMarkerFile = FMLPaths.GAMEDIR.get().resolve("local/default-used.marker");
        if (Files.exists(localMarkerFile)) {
            DSPInit.LOGGER.info("Default server properties injection ignored as it has already been run. This is not an error. This is correct behaviour.");
            return info.getReturnValue();
        }

        if (Files.notExists(localMarkerFile.getParent())) {
            try {
                Files.createDirectories(localMarkerFile.getParent());
            } catch (IOException e) {
                DSPInit.LOGGER.error("Failed to create local dir in the games root path...", e);
                return info.getReturnValue();
            }
        }

        try {
            Files.writeString(localMarkerFile, "");
        } catch (IOException e) {
            DSPInit.LOGGER.error("Unable to create marker so avoiding default properties injection...", e);
            return info.getReturnValue();
        }

        // Natively defaults back to the original so if there is no default prop defined then this will just mimic the original creations of the server props file
        DedicatedServerProperties dedicatedServerProperties = new DedicatedServerProperties(DedicatedServerProperties.loadFromFile(defaultSettingPath));
        System.out.println(dedicatedServerProperties.levelName);
        DSPInit.LOGGER.info("Replacing server properties with default properties!");
        info.setReturnValue(dedicatedServerProperties);
        return dedicatedServerProperties;
    }
}
