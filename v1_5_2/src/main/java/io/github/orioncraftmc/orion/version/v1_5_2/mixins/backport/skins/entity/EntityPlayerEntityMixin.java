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

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {EntityPlayerSP.class, EntityOtherPlayerMP.class})
public abstract class EntityPlayerEntityMixin extends Entity {
	public EntityPlayerEntityMixin(World world) {
		super(world);
	}

	/**
	 * @reason Cloaks and Skins are handled by OrionCraft
	 * @author OrionCraftMc
	 */
	@Inject(method = "updateCloak", at = @At("HEAD"), cancellable = true)
	public void onCloakUpdate(CallbackInfo ci) {
		ci.cancel();
		this.skinUrl = "orion_skin_" + getEntityName();
		this.cloakUrl = "orion_cloak_" + getEntityName();
	}
}
