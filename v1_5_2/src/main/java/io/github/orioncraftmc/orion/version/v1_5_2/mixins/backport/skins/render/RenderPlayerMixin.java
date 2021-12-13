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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins.render;

import io.github.orioncraftmc.orion.api.bridge.entity.EntityPlayerBridge;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.OrionModelPlayer;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.RenderPlayerDuck;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class RenderPlayerMixin implements RenderPlayerDuck {

	private final OrionModelPlayer slimArmsModel = new OrionModelPlayer(0.0f, true);
	private final ModelBiped oldSkinModel = new ModelBiped();
	@Shadow
	public ModelBiped modelBipedMain;

	@Redirect(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/client/model/ModelBiped"))
	private static ModelBiped onCreatePlayerModel(float v) {
		if (v == 0.0f) {
			return new OrionModelPlayer(v, false);
		}

		if (v == 1.0) v = 0.75f;
		return new ModelBiped(v);
	}

	@Inject(method = "renderFirstPersonArm", at = @At("RETURN"))
	private void onRenderArmEnd(CallbackInfo info) {
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	@Inject(method = "renderFirstPersonArm", at = @At("HEAD"))
	protected void onRenderArmStart(EntityPlayer par1, CallbackInfo ci) {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	@Redirect(method = {"renderPlayer", "renderSpecials", "renderFirstPersonArm"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderPlayer;modelBipedMain:Lnet/minecraft/client/model/ModelBiped;"))
	public ModelBiped onGetModelBiped(RenderPlayer instance, EntityPlayer player) {
		return getPlayerModelToRender(player);
	}

	@Redirect(method = {"setArmorModel"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderPlayer;mainModel:Lnet/minecraft/client/model/ModelBase;"))
	public ModelBase onGetMainModel(RenderPlayer instance, EntityPlayer player) {
		return getPlayerModelToRender(player);
	}

	@Redirect(method = "renderFirstPersonArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V"))
	public void onRenderFirstPersonArm(ModelRenderer instance, float v, EntityPlayer entityPlayer) {
		ModelBiped model = getPlayerModelToRender(entityPlayer);
		if (model instanceof OrionModelPlayer modelPlayer) {
			modelPlayer.renderRightArm();
		} else {
			instance.render(v);
		}
	}

	@Override
	public ModelBiped getPlayerModelToRender(EntityPlayer entityPlayer) {
		if (entityPlayer instanceof EntityPlayerBridge duck) {
			if (duck.isSlimSkinModel()) {
				return slimArmsModel;
			} else if (duck.isOldSkinModel()) {
				return oldSkinModel;
			}
		}
		return modelBipedMain;
	}
}
