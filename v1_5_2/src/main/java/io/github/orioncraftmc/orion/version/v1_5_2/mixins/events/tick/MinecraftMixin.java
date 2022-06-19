package io.github.orioncraftmc.orion.version.v1_5_2.mixins.events.tick;

import io.github.orioncraftmc.orion.api.event.EventBus;
import io.github.orioncraftmc.orion.api.event.impl.GameReadyEvent;
import io.github.orioncraftmc.orion.api.event.impl.GameTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow
	@Final
	public Profiler mcProfiler;

	@Inject(method = "runTick", at = @At("TAIL"))
	private void onStartGame(CallbackInfo ci) {
		mcProfiler.endStartSection("orion");

		EventBus.INSTANCE.callEvent(GameTickEvent.INSTANCE);

		mcProfiler.endSection();
	}

}
