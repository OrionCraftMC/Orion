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

package io.github.orioncraftmc.orion.version.common.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.*;

@Mixin(ScaledResolution.class)
@Implements(@Interface(iface = ScaledResolutionBridge.class, prefix = "bridge$"))
public class ScaledResolutionMixin {
	@Shadow
	public int scaleFactor;

	@Shadow
	public int scaledHeight;

	@Shadow
	public int scaledWidth;

	@Shadow
	public double scaledHeightD;

	@Shadow
	public double scaledWidthD;

	public int bridge$getScaleFactor() {
		return scaleFactor;
	}

	public int bridge$getScaledHeight() {
		return scaledHeight;
	}

	public int bridge$getScaledWidth() {
		return scaledWidth;
	}

	public float bridge$getScaledHeightFloat() {
		return (float) scaledHeightD;
	}

	public float bridge$getScaledWidthFloat() {
		return (float) scaledWidthD;
	}
}
