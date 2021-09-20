package io.github.orioncraftmc.orion.version.v1_7_10.mixins.fixes.mcoremove;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {

	@Inject(method = "addSingleplayerMultiplayerButtons", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2), cancellable = true)
	public void removeMcoButton(int i, int j, CallbackInfo ci) {
		ci.cancel();
	}
}
