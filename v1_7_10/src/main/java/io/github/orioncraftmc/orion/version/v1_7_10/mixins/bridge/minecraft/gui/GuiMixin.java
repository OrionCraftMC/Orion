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

package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft.gui;

import io.github.orioncraftmc.orion.api.bridge.rendering.gui.GuiBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public class GuiMixin implements GuiBridge {
	@Shadow
	public float zLevel;

	@Override
	public float getZLevel() {
		return this.zLevel;
	}

	@Override
	public void setZLevel(float v) {
		this.zLevel = v;
	}
}