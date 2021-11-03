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

import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.EntityPlayerGameProfileDuck;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.OrionModelPlayer;
import net.minecraft.client.render.Render;
import net.minecraft.client.render.RenderManager;
import net.minecraft.client.render.RenderPlayer;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderManager.class)
public class RenderManagerMixin {
	@Shadow
	public static RenderManager instance;

	private RenderPlayer slimPlayerRender;

	@NotNull
	private RenderPlayer iniializeRenderIfNeeded() {
		if (slimPlayerRender != null) return slimPlayerRender;

		RenderPlayer slimRenderer = new RenderPlayer();
		slimRenderer.renderManager = instance;
		slimRenderer.mainModel = new OrionModelPlayer(0.0f, true);
		slimPlayerRender = slimRenderer;

		return slimRenderer;
	}


	@Inject(method = "getEntityRenderObject", at = @At("HEAD"), cancellable = true)
	public void onGetEntityRenderObject(Entity par1, CallbackInfoReturnable<Render> cir) {
		if (par1 instanceof EntityPlayerGameProfileDuck duck && duck.isSlimSkin()) {
			cir.setReturnValue(iniializeRenderIfNeeded());
		}
	}

}
