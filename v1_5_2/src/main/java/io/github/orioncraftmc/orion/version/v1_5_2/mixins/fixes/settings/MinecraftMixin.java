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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.settings;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow
	public GameSettings gameSettings;

	@Redirect(method = "startGame", at = @At(value = "NEW",
			target = "net/minecraft/client/settings/GameSettings"))
	public GameSettings onNewGameSettings(Minecraft par1Minecraft, File par2File) {
		if (gameSettings != null) {
			gameSettings.mc = par1Minecraft;
			Config.setGameSettings(gameSettings);
			return gameSettings;
		}
		return new GameSettings(par1Minecraft, par2File);
	}

}
