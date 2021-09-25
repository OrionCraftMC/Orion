package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.entity;

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
