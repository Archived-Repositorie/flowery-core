package io.github.justfoxx

import io.github.justfoxx.structure.AirStructure
import io.github.justfoxx.structure.LandStructure
import io.github.justfoxx.structure.WaterStructure
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import java.util.function.Supplier

class StructureFeature {
    val DEFERRED_REGISTRY_STRUCTURE: DeferredRegister<StructureFeature<*>> = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID)
    val Water = DEFERRED_REGISTRY_STRUCTURE.register<StructureFeature<*>>(
        "water",
        Supplier {
            WaterStructure()
        }
    )
    val Land = DEFERRED_REGISTRY_STRUCTURE.register<StructureFeature<*>>(
        "land",
        Supplier {
            LandStructure()
        }
    )
    val Air = DEFERRED_REGISTRY_STRUCTURE.register<StructureFeature<*>>(
        "air",
        Supplier {
            AirStructure()
        }
    )
    fun Register() {
        DEFERRED_REGISTRY_STRUCTURE.register(KotlinModLoadingContext.get().getKEventBus())
    }
}
