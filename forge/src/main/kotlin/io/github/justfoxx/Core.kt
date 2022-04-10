package io.github.justfoxx

import io.github.justfoxx.Structure
import io.github.justfoxx.structure.WaterStructures
import net.minecraft.block.Block
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier

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