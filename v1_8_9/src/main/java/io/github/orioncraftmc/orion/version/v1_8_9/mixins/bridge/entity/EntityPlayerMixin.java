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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.entity;

import io.github.orioncraftmc.orion.api.bridge.entity.EntityPlayerBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin implements EntityPlayerBridge {
	@Override
	public boolean isOldSkinModel() {
		return false;
	}

	@Override
	public void setOldSkinModel(boolean b) {

	}

	@Override
	public boolean isSlimSkinModel() {
		Object entityRenderer = getPlayerRendererForSelf();
		return entityRenderer instanceof RenderPlayer renderPlayer && renderPlayer.smallArms;
	}

	private Render<Entity> getPlayerRendererForSelf() {
		return Minecraft.getMinecraft().renderManager.getEntityRenderObject(
				(EntityPlayer) (Object) this);
	}

	@Override
	public void setSlimSkinModel(boolean b) {
		Object entityRenderer = getPlayerRendererForSelf();
		if (entityRenderer instanceof RenderPlayer renderPlayer) {
			renderPlayer.smallArms = b;
		}
	}
}
