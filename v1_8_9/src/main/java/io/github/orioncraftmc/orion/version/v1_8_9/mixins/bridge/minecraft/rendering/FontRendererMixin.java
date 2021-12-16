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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge;
import net.minecraft.client.gui.FontRenderer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

@Mixin(FontRenderer.class)
@Implements(@Interface(iface = FontRendererBridge.class, prefix = "bridge$"))
public abstract class FontRendererMixin {
	@Shadow
	public int FONT_HEIGHT;

	@Shadow
	public abstract int drawString(String text, float x, float y, int color, boolean dropShadow);

	public void bridge$drawString(@NotNull String s, int i, int i1, int i2, boolean b) {
		drawString(s, i, i1, i2, b);
	}

	public int bridge$getFontHeight() {
		return FONT_HEIGHT;
	}
}
