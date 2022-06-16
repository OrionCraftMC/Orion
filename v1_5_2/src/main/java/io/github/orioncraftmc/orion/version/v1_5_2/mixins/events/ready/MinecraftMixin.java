package io.github.orioncraftmc.orion.version.v1_5_2.mixins.events.ready;

import io.github.orioncraftmc.orion.api.event.EventBus;
import io.github.orioncraftmc.orion.api.event.impl.GameReadyEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "startGame", at = @At("TAIL"))
	private void onStartGame(CallbackInfo ci) {
		EventBus.INSTANCE.callEvent(new GameReadyEvent());
	}

}
