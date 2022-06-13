/*
 * Thanks for @unascribed for code!
 * Place of original code https://git.sleeping.town/unascribed/Yttr/src/branch/1.16.5/src/main/java/com/unascribed/yttr/mixin/only_if/MixinJsonDataLoader.java
*/
package io.github.justfoxx.mixin;

import java.util.Iterator;
import java.util.Map;

import io.github.justfoxx.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

@Mixin(JsonDataLoader.class)
public class MixinJsonDataLoader {

    @Inject(at=@At("RETURN"), method="prepare")
    protected void prepare(ResourceManager resourceManager, Profiler profiler, CallbackInfoReturnable<Map<Identifier, JsonElement>> ci) {
        Iterator<JsonElement> iter = ci.getReturnValue().values().iterator();
        while (iter.hasNext()) {
            JsonElement ele = iter.next();
            if (ele instanceof JsonObject && ele.getAsJsonObject().has("flowery:only_if")) {
                String when = ele.getAsJsonObject().get("flowery:only_if").getAsString();
                boolean active = false;
                active = new IsModLoaded().loaded(when);
                if (!active) {
                    iter.remove();
                }
            }
        }
    }

}
