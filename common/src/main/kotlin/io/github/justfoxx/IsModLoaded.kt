package io.github.justfoxx

import dev.architectury.injectables.annotations.ExpectPlatform

class IsModLoaded {
    @ExpectPlatform
    fun loaded(mod: String): Boolean {
        throw AssertionError()
    }
}