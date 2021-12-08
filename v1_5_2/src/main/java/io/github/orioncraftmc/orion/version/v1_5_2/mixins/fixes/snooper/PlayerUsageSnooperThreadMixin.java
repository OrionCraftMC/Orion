package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.snooper;

import net.minecraft.PlayerUsageSnooperThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerUsageSnooperThread.class)
public class PlayerUsageSnooperThreadMixin {

	@Inject(method = "run", at = @At("HEAD"), cancellable = true)
	private void onRun(CallbackInfo ci) {
		ci.cancel();
	}
}
