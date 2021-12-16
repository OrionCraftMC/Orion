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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameSettings.class)
public class GameSettingsMixin implements GameSettingsBridge {
	@Shadow
	public float gammaSetting;

	@Shadow
	public float guiScale;

	@Override
	public float getGammaValue() {
		return gammaSetting;
	}

	@Override
	public void setGammaValue(float v) {
		gammaSetting = v;
	}

	@Override
	public int getGuiScale() {
		return (int)guiScale;
	}
}
