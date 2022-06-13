package io.github.justfoxx.forge

import net.minecraftforge.fml.ModList

class isModLoadedImpl() {
    fun loaded(mod: String): Boolean{
        return ModList.get().isLoaded(mod)
    }
}