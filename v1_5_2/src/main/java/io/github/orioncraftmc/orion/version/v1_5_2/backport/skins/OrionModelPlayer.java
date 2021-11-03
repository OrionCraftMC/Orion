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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.skins;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class OrionModelPlayer extends ModelBiped {

	/**
	 * Copies the angles from one object to another. This is used when objects should stay aligned with each other, like the hair over a players head.
	 */
	public static void copyModelAngles(ModelRenderer bct1, ModelRenderer bct2) {
		bct2.rotateAngleX = bct1.rotateAngleX;
		bct2.rotateAngleY = bct1.rotateAngleY;
		bct2.rotateAngleZ = bct1.rotateAngleZ;
		bct2.rotationPointX = bct1.rotationPointX;
		bct2.rotationPointY = bct1.rotationPointY;
		bct2.rotationPointZ = bct1.rotationPointZ;
	}

	public ModelRenderer bipedLeftArmwear;
	public ModelRenderer bipedRightArmwear;
	public ModelRenderer bipedLeftLegwear;
	public ModelRenderer bipedRightLegwear;
	public ModelRenderer bipedBodyWear;
	public ModelRenderer bipedCape;
	public ModelRenderer bipedDeadmau5Head;
	public boolean smallArms;

	public OrionModelPlayer(float scaleFactor, boolean smallArms) {
		super(scaleFactor, 0.0f, 64, 64);
		this.smallArms = smallArms;
		this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
		this.bipedDeadmau5Head.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, scaleFactor);
		this.bipedCape = new ModelRenderer(this, 0, 0);
		this.bipedCape.setTextureSize(64, 32);
		this.bipedCape.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, scaleFactor);
		if (smallArms) {
			this.bipedLeftArm = new ModelRenderer(this, 32, 48);
			this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 3, 12, 4, scaleFactor);
			this.bipedLeftArm.setRotationPoint(5.0f, 2.5f, 0.0f);
			this.bipedRightArm = new ModelRenderer(this, 40, 16);
			this.bipedRightArm.addBox(-2.0f, -2.0f, -2.0f, 3, 12, 4, scaleFactor);
			this.bipedRightArm.setRotationPoint(-5.0f, 2.5f, 0.0f);
			this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
			this.bipedLeftArmwear.addBox(-1.0f, -2.0f, -2.0f, 3, 12, 4, scaleFactor + 0.25f);
			this.bipedLeftArmwear.setRotationPoint(5.0f, 2.5f, 0.0f);
			this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
			this.bipedRightArmwear.addBox(-2.0f, -2.0f, -2.0f, 3, 12, 4, scaleFactor + 0.25f);
			this.bipedRightArmwear.setRotationPoint(-5.0f, 2.5f, 10.0f);
		} else {
			this.bipedLeftArm = new ModelRenderer(this, 32, 48);
			this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, scaleFactor);
			this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
			this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
			this.bipedLeftArmwear.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, scaleFactor + 0.25f);
			this.bipedLeftArmwear.setRotationPoint(5.0f, 2.0f, 0.0f);
			this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
			this.bipedRightArmwear.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, scaleFactor + 0.25f);
			this.bipedRightArmwear.setRotationPoint(-5.0f, 2.0f, 10.0f);
		}
		this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
		this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scaleFactor);
		this.bipedLeftLeg.setRotationPoint(1.9f, 12.0f, 0.0f);
		this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
		this.bipedLeftLegwear.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scaleFactor + 0.25f);
		this.bipedLeftLegwear.setRotationPoint(1.9f, 12.0f, 0.0f);
		this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
		this.bipedRightLegwear.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scaleFactor + 0.25f);
		this.bipedRightLegwear.setRotationPoint(-1.9f, 12.0f, 0.0f);
		this.bipedBodyWear = new ModelRenderer(this, 16, 32);
		this.bipedBodyWear.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, scaleFactor + 0.25f);
		this.bipedBodyWear.setRotationPoint(0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(Entity pk2, float float2, float float3, float float4, float float5, float float6, float float7) {
		super.render(pk2, float2, float3, float4, float5, float6, float7);
		GL11.glPushMatrix();
		if (this.isChild) {
			float float9 = 2.0f;
			GL11.glScalef(1.0f / float9, 1.0f / float9, 1.0f / float9);
			GL11.glTranslatef(0.0f, 24.0f * float7, 0.0f);
		}
		this.bipedLeftLegwear.render(float7);
		this.bipedRightLegwear.render(float7);
		this.bipedLeftArmwear.render(float7);
		this.bipedRightArmwear.render(float7);
		this.bipedBodyWear.render(float7);
		GL11.glPopMatrix();
	}


	@Override
	public void renderEars(float float1) {
		copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
		this.bipedDeadmau5Head.rotationPointX = 0.0f;
		this.bipedDeadmau5Head.rotationPointY = 0.0f;
		this.bipedDeadmau5Head.render(float1);
	}

	@Override
	public void renderCloak(float float1) {
		this.bipedCape.render(float1);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far" arms and legs can swing at most.
	 */
	@Override
	public void setRotationAngles(float float1, float float2, float float3, float float4, float float5, float float6, Entity pk2) {
		if (pk2 instanceof EntityPlayer entityPlayer) {
			ItemStack var11 = entityPlayer.inventory.getCurrentItem();
			heldItemRight = var11 != null ? 1 : 0;
			if (var11 != null && entityPlayer.getItemInUseCount() > 0) {
				EnumAction var12 = var11.getItemUseAction();
				if (var12 == EnumAction.block) {
					heldItemRight = 3;
				} else if (var12 == EnumAction.bow) {
					aimedBow = true;
				}
			}
			isSneak = entityPlayer.isSneaking();
		}

		super.setRotationAngles(float1, float2, float3, float4, float5, float6, pk2);
		copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
		copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
		copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
		copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
		copyModelAngles(this.bipedBody, this.bipedBodyWear);
		this.bipedCape.rotationPointY = 0.0f;
	}

	public void renderRightArm() {
		this.bipedRightArm.render(0.0625f);
		this.bipedRightArmwear.render(0.0625f);
	}

	public void renderLeftArm() {
		this.bipedLeftArm.render(0.0625f);
		this.bipedLeftArmwear.render(0.0625f);
	}

	public void setInvisible(boolean boolean1) {
		this.bipedLeftArmwear.showModel = boolean1;
		this.bipedRightArmwear.showModel = boolean1;
		this.bipedLeftLegwear.showModel = boolean1;
		this.bipedRightLegwear.showModel = boolean1;
		this.bipedBodyWear.showModel = boolean1;
		this.bipedCape.showModel = boolean1;
		this.bipedDeadmau5Head.showModel = boolean1;
	}

	public void postRenderArm(float float1) {
		if (this.smallArms) {
			this.bipedRightArm.rotationPointX += 1.0f;
			this.bipedRightArm.postRender(float1);
			this.bipedRightArm.rotationPointX -= 1.0f;
		} else {
			this.bipedRightArm.postRender(float1);
		}
	}
}
