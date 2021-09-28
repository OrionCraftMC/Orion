package io.github.orioncraftmc.orion.version.v1_7_10.mixins.client.branding;

import io.github.orioncraftmc.orion.api.gui.screens.impl.GuiScreenOrionBrandingScreen;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class GuiScreenMixin {

	private final GuiScreenOrionBrandingScreen gameGuiScreen = new GuiScreenOrionBrandingScreen();

	@Inject(method = "drawWorldBackground", at = @At("RETURN"))
	public void onDrawWorldBackground(int par1, CallbackInfo ci) {
		gameGuiScreen.drawScreen();
	}
}
