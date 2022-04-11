package io.github.justfoxx

import io.github.justfoxx.mixin.StructureFeatureAccessor
import io.github.justfoxx.structure.AirStructure
import io.github.justfoxx.structure.LandStructure
import io.github.justfoxx.structure.WaterStructure
import net.minecraft.world.gen.GenerationStep

class Structure {
    val WATER_STRUCTURE = WaterStructure()
    val AIR_STRUCTURE = AirStructure()
    val LAND_STRUCTUE = LandStructure()

    fun registerStructureFeatures() {
        StructureFeatureAccessor.callRegister("${MODID}:water", WATER_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES)
        StructureFeatureAccessor.callRegister("${MODID}:air", AIR_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES)
        StructureFeatureAccessor.callRegister("${MODID}:land", LAND_STRUCTUE, GenerationStep.Feature.SURFACE_STRUCTURES)
    }
}