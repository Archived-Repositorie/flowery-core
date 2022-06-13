package io.github.justfoxx

import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod

@Mod(MODID)
object Core {
    init {
        StructureFeature().Register()
        for(mod in supported) {
            if (ModList.get().isLoaded(mod.key) && !ModList.get().isLoaded(mod.value)) {
                Logger.warn("Mod ${mod.key} is loaded but ${mod.key} support mod is not loaded")
            } else if(ModList.get().isLoaded(mod.value)) {
                Logger.info("Support mod ${mod.value} has been loaded")
            }
        }
    }
}