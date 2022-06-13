package io.github.justfoxx

import net.fabricmc.loader.api.FabricLoader

class isModLoadedImpl() {
    fun loaded(mod: String): Boolean{
        return FabricLoader.getInstance().isModLoaded(mod)
    }
}