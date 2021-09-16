 package io.github.orioncraftmc.orion.version.v1_7_10.mixins.fixes.fps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow
	public GameSettings gameSettings;

	@Inject(method ="getLimitFramerate", cancellable = true, at = @At("HEAD"))
	public void onGetLimitFramerate(CallbackInfoReturnable<Integer> cir) {
		// Make getLimitFramerate return the FPS limit
		// Removes the FPS lock on menus
		cir.setReturnValue(gameSettings.limitFramerate);
	}
}
