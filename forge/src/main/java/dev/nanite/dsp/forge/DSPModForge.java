package dev.nanite.dsp.forge;

import dev.nanite.dsp.DSPMod;
import net.minecraftforge.fml.common.Mod;

@Mod(DSPMod.MOD_ID)
public class DSPModForge {
    public DSPModForge() {
        DSPMod.init();
    }
}
