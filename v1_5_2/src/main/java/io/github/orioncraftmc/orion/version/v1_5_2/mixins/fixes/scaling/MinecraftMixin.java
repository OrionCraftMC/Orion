package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.scaling;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "main", at = @At("HEAD"))
	private static void onMain(CallbackInfo ci) {
		// Fix scaling on Hi-DPI screens
		if (!System.getProperty("java.specification.version").startsWith("1.")) {
			System.setProperty("sun.java2d.uiScale", "1.0");
			System.setProperty("prism.allowhidpi", "false");
		}
	}

}
