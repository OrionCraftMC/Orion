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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.entity;

import io.github.orioncraftmc.orion.api.bridge.entity.EntityPlayerBridge;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin implements EntityPlayerBridge {
	private boolean isSlimSkinModel = false;
	private boolean isOldSkinModel = false;

	@Override
	public boolean isOldSkinModel() {
		return isOldSkinModel;
	}

	@Override
	public void setOldSkinModel(boolean b) {
		isOldSkinModel = b;
	}

	@Override
	public boolean isSlimSkinModel() {
		return isSlimSkinModel;
	}

	@Override
	public void setSlimSkinModel(boolean b) {
		isSlimSkinModel = b;
	}
}
