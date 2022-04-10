import io.github.justfoxx.MODID
import io.github.justfoxx.mixin.StructureFeatureAccessor
import io.github.justfoxx.structure.WaterStructures
import net.minecraft.world.gen.GenerationStep

class Structure {
    fun registerStructureFeatures() {
        val WATER_STRUCTURE = WaterStructures()
        StructureFeatureAccessor.callRegister("${MODID}:water", WATER_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES)
    }
}