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

import io.github.orioncraftmc.orion.api.bridge.entity.EntityLivingBridge;
import net.minecraft.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityLiving.class)
public abstract class EntityLivingMixin implements EntityLivingBridge {
	@Shadow
	public String texture;

	@NotNull
	@Override
	public String bridge$getTexture() {
		return texture;
	}

	@Override
	public void bridge$setTexture(@NotNull String s) {
		texture = s;
	}
}
