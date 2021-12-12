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
		// Remove the FPS lock on menus
		// TODO: Implement setting for this in the client config
		int fpsLimit = Minecraft.getMinecraft().gameSettings.ofLimitFramerateFine;
		if (fpsLimit <= 0) {
			fpsLimit = 10000;
		}

		cir.setReturnValue(fpsLimit);
	}
}
