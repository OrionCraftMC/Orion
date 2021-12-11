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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge;
import net.minecraft.client.render.RenderEngine;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RenderEngine.class)
public class RenderEngineMixin implements RenderEngineBridge {

	@Override
	public void bindTexture(@NotNull ResourceLocationBridge resourceLocationBridge) {
		((RenderEngine) (Object) this).bindTexture(resourceLocationBridge.getFullPath());
	}
}
