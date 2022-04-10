package io.github.justfoxx

import Structure
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

val Logger = LoggerFactory.getLogger("FloweryCore")
var MODID = "flowerycore"

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
