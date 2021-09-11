package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.resources;

import net.minecraft.ThreadDownloadResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadDownloadResources.class)
public abstract class ThreadDownloadResourcesMixin {

    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void onRun(CallbackInfo ci) {
        // Resources are downloaded by the launchers now
        ci.cancel();
    }

}
