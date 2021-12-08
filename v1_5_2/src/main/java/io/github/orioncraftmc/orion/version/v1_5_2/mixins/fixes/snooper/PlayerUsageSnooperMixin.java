package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.snooper;

import net.minecraft.profiler.PlayerUsageSnooper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerUsageSnooper.class)
public class PlayerUsageSnooperMixin {
	@Shadow
	public boolean isRunning;

	@Inject(method = "startSnooper", at = @At("HEAD"), cancellable = true)
	private void onStartSnooper(CallbackInfo ci) {
		isRunning = true;
		ci.cancel();
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addBaseDataToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addJvmArgsToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addMemoryStatsToSnooper() {
	}

	/**
	 * NO-OP this call
	 *
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void addData(String string, Object object) {
	}
}
