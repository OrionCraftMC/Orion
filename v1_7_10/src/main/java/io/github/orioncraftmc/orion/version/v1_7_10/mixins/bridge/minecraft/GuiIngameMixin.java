package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.event.EventBus;
import io.github.orioncraftmc.orion.api.event.impl.HudRenderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.GuiIngame;

@Mixin(GuiIngame.class)
public class GuiIngameMixin {
	@Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", shift = Shift.AFTER, target = "Lnet/minecraft/scoreboard/Scoreboard;getObjectiveInDisplaySlot(I)Lnet/minecraft/scoreboard/ScoreObjective;", ordinal = 1))
	public void onRender(float f, boolean bl, int i, int j, CallbackInfo ci) {
		EventBus.INSTANCE.callEvent(new HudRenderEvent(f));
	}
}
