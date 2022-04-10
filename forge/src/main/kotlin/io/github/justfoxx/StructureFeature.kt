package io.github.justfoxx

import io.github.justfoxx.structure.WaterStructures
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier

class StructureFeature {
    val DeferredRegistering = net.minecraftforge.registries.DeferredRegister.create<StructureFeature<*>>(ForgeRegistries.STRUCTURE_FEATURES, MODID)
    fun Register() {
        DeferredRegistering.register("water", Supplier {
            WaterStructures()
        })
    }
}
