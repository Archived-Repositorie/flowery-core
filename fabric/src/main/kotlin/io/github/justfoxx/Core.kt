package io.github.justfoxx

import io.github.justfoxx.Structure
import net.fabricmc.loader.api.FabricLoader

@Suppress("unused")
fun init() {
    Structure().registerStructureFeatures()
    for(mod in supported) {
        if (FabricLoader.getInstance().isModLoaded(mod.key) && !FabricLoader.getInstance().isModLoaded(mod.value)) {
            Logger.warn("Mod ${mod.key} is loaded but ${mod.key} support mod is not loaded")
        } else if(FabricLoader.getInstance().isModLoaded(mod.value)) {
            Logger.info("Support mod ${mod.value} has been loaded")
        }
    }
}
