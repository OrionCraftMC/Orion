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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.events;

import io.github.orioncraftmc.orion.api.bridge.rendering.gui.GuiIngameBridge;
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
	@Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", shift = Shift.AFTER, target = "Lnet/minecraft/scoreboard/Scoreboard;func_96539_a(I)Lnet/minecraft/scoreboard/ScoreObjective;", ordinal = 1))
	public void onRender(float f, boolean bl, int i, int j, CallbackInfo ci) {
		EventBus.INSTANCE.callEvent(new HudRenderEvent(f, (GuiIngameBridge) this));
	}
}
