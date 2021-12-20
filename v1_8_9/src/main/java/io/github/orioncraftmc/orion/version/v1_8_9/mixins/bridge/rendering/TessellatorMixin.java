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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.enums.DrawMode;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.*;

@Mixin(Tessellator.class)
@Implements(@Interface(iface = TessellatorBridge.class, prefix = "bridge$"))
public abstract class TessellatorMixin {

	@Shadow
	public WorldRenderer worldRenderer;

	public void bridge$addVertex(double v, double v1, double v2) {
		worldRenderer.pos(v, v1, v2).endVertex();
	}

	public void bridge$addVertexWithUV(double x, double y, double z, double u, double v) {
		worldRenderer.pos(x, y, z).tex(u, v).endVertex();
	}

	public void bridge$setTesselatorColor(int i, int i1, int i2, int i3) {
		worldRenderer.color(i, i1, i2, i3);
	}

	public void bridge$setColor(int i, int i1, int i2, int i3) {
		GL11.glColor4f(i / 255f, i1 / 255f, i2 / 255f, i3 / 255f);
	}

	public void bridge$start(@NotNull DrawMode mode) {
		worldRenderer.begin(mode.ordinal(), DefaultVertexFormats.POSITION);
	}
}
