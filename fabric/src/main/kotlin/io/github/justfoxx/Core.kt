package io.github.justfoxx

import net.fabricmc.loader.api.FabricLoader

fun isModLoaded(mod: String): Boolean {
    return FabricLoader.getInstance().isModLoaded(mod)
}

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
