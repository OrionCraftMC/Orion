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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins;

import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.RenderGlobal;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.Entity;
import net.optifine.Config;
import net.optifine.RandomMobs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {
	@Shadow
	@Final
	public RenderEngine renderEngine;

	/**
	 * @reason Skins and Cloaks are fetched manually
	 * @author OrionCraftMc
	 */
	//@Overwrite
	public void onEntityCreateSus(Entity par1Entity) {
		par1Entity.updateCloak();
		if (par1Entity.skinUrl != null) {
			this.renderEngine.obtainImageData(par1Entity.skinUrl, new ImageBufferDownload());
		}

		if (par1Entity.cloakUrl != null) {
			this.renderEngine.obtainImageData(par1Entity.cloakUrl, new ImageBufferDownload());
		}

		if (Config.isRandomMobs()) {
			RandomMobs.entityLoaded(par1Entity);
		}

	}
}
