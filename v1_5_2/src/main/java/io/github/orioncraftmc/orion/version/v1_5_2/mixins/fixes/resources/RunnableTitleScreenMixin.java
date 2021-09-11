package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.resources;

import net.minecraft.RunnableTitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RunnableTitleScreen.class)
public class RunnableTitleScreenMixin {

	@Inject(method = "run", at = @At("HEAD"), cancellable = true)
	public void onRun(CallbackInfo ci) {
		// Stop attempting to check if 1.6 has released. It has, but we don't care
		ci.cancel();
	}

}
