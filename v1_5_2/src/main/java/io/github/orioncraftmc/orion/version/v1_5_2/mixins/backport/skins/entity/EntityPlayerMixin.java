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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins.entity;

import io.github.orioncraftmc.orion.backport.hooks.PlayerTexturesHook;
import io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins.extra.LivingEntityAccessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin {

	@Inject(method = "<init>", at = @At("RETURN"))
	public void setNewSteveTexture(World par1, CallbackInfo ci) {
		((LivingEntityAccessor) this).setTexture(
				PlayerTexturesHook.INSTANCE.getPlayerDefaultSkinResourceLocation().getFullPath());
	}

}
