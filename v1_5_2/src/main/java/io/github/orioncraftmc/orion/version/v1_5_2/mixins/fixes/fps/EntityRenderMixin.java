package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.fps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRenderMixin {

	@Inject(method = "performanceToFps", cancellable = true, at = @At("HEAD"))
	private static void onPerformanceToFps(int par0, CallbackInfoReturnable<Integer> cir) {
		// Make performanceToFps return the FPS limit
		// Removes the FPS lock on menus
		int fpsLimit = Minecraft.getMinecraft().gameSettings.ofLimitFramerateFine;
		if (fpsLimit <= 0) {
			fpsLimit = 10000;
		}

		cir.setReturnValue(fpsLimit);
	}
}
