package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.mcoremove;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.StringTranslate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {

	@Inject(method = "addRealmsButton", at = @At("HEAD"), cancellable = true)
	public void removeMcoButton(StringTranslate i, int j, int par3, CallbackInfo ci) {
		ci.cancel();
	}
}
