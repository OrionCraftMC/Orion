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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft.entity;

import io.github.orioncraftmc.orion.api.bridge.minecraft.entity.EntityBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class EntityMixin implements EntityBridge {
	@Shadow
	public float height;

	@Shadow
	public float width;

	@Shadow
	public double motionX;

	@Shadow
	public double motionY;

	@Shadow
	public double motionZ;

	@Shadow
	public boolean onGround;

	@Shadow
	public double posX;

	@Shadow
	public double posY;

	@Shadow
	public double posZ;

	@Shadow
	public float rotationPitch;

	@Shadow
	public float rotationYaw;

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public double getMotionX() {
		return this.motionX;
	}

	@Override
	public double getMotionY() {
		return this.motionY;
	}

	@Override
	public double getMotionZ() {
		return this.motionZ;
	}

	@Override
	public boolean getOnGround() {
		return this.onGround;
	}

	@Override
	public double getPosX() {
		return this.posX;
	}

	@Override
	public double getPosY() {
		return this.posY;
	}

	@Override
	public double getPosZ() {
		return this.posZ;
	}

	@Override
	public double getRotationPitch() {
		return this.rotationPitch;
	}

	@Override
	public double getRotationYaw() {
		return this.rotationYaw;
	}
}
