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

package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.DrawMode;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import net.minecraft.client.renderer.Tessellator;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Tessellator.class)
@Implements(@Interface(iface = TessellatorBridge.class, prefix = "bridge$", remap = Interface.Remap.ONLY_PREFIXED))
public abstract class TessellatorMixin {

	@Shadow
	public abstract int draw();

	@Shadow
	public abstract void setColorRGBA(int par1, int par2, int par3, int par4);

	@Shadow
	public abstract void startDrawing(int par1);

	public void bridge$draw() {
		draw();
	}

	public void bridge$setColor(int i, int i1, int i2, int i3) {
		setColorRGBA(i, i1, i2, i3);
	}

	public void bridge$start(@NotNull DrawMode mode) {
		startDrawing(mode.ordinal());
	}
}
