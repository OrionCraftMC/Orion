/*
 * Copyright (C) 2021 OrionCraftMC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
