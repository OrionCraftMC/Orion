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

package io.github.orioncraftmc.orion.version.v1_5_2.bridge.input;

import io.github.orioncraftmc.orion.api.keybinding.OrionKeybinding;
import net.minecraft.client.settings.KeyBinding;
import org.jetbrains.annotations.NotNull;

public class MinecraftOrionKeybinding extends KeyBinding {
	@NotNull
	private final OrionKeybinding orionKeybinding;

	public MinecraftOrionKeybinding(OrionKeybinding orionKeybinding) {
		super(orionKeybinding.getId(), orionKeybinding.getKeyCode().getKeyCode());
		this.orionKeybinding = orionKeybinding;
	}

	@NotNull
	public OrionKeybinding getOrionKeybinding() {
		return orionKeybinding;
	}
}
