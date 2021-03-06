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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins.extra;

import io.github.orioncraftmc.orion.api.bridge.entity.EntityPlayerBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.download.ImageBufferDownloadBridge;
import net.minecraft.client.renderer.ImageBufferDownload;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ImageBufferDownload.class)
public class ImageBufferDownloadMixin implements ImageBufferDownloadBridge {
	private EntityPlayerBridge player = null;

	@Nullable
	@Override
	public EntityPlayerBridge getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(@Nullable EntityPlayerBridge entityPlayerBridge) {
		player = entityPlayerBridge;
	}
}
