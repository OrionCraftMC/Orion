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

import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.RenderPlayerDuck;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.render.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderLiving.class)
public class RenderLivingMixin {
	@Redirect(method = {"doRenderLiving", "renderModel", "renderArrowsStuckInEntity"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderLiving;mainModel:Lnet/minecraft/client/model/ModelBase;"))
	public ModelBase onGetEntityModel(RenderLiving instance, EntityLiving entityLiving) {
		if (entityLiving instanceof EntityPlayer player && instance instanceof RenderPlayerDuck duck) {
			return duck.getPlayerModelToRender(player);
		}
		return instance.mainModel;
	}
}
