package io.github.justfoxx

import io.github.justfoxx.mixin.StructureFeatureAccessor
import io.github.justfoxx.structure.WaterStructures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraftforge.registries.ForgeRegistries

class StructureFeature {
    val WATER_STRUCTURE = WaterStructures()
    val DeferredRegistering = net.minecraftforge.registries.DeferredRegister.create<StructureFeature<*>>(ForgeRegistries.STRUCTURE_FEATURES, MODID)
    fun Register() {

        DeferredRegistering.register("water") {
            StructureFeatureAccessor.callRegister(
                "${MODID}:water",
                WATER_STRUCTURE,
                GenerationStep.Feature.SURFACE_STRUCTURES
            )
        }

    }
}
