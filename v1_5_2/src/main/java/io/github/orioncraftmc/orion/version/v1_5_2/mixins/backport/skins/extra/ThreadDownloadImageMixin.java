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
import io.github.orioncraftmc.orion.backport.hooks.PlayerTexturesHook;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.ThreadDownloadImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadDownloadImage.class)
public class ThreadDownloadImageMixin {

	@Shadow
	@Final
	public String location;

	@Shadow
	@Final
	public ThreadDownloadImageData imageData;

	@Shadow
	@Final
	public IImageBuffer buffer;

	@Inject(method = "run", at = @At("HEAD"), cancellable = true)
	public void onDownloadImage(CallbackInfo ci) {
		PlayerTexturesHook.INSTANCE.fetchPlayerTexture(
				location,
				ci::cancel,
				bufferedImage -> imageData.image = bufferedImage,
				getPlayerSkinHandler(EntityPlayerBridge::setSlimSkinModel),
				getPlayerSkinHandler(EntityPlayerBridge::setOldSkinModel)
		);
	}

	@Nullable
	private Consumer<Boolean> getPlayerSkinHandler(BiConsumer<EntityPlayerBridge, Boolean> consumer) {
		if (buffer instanceof ImageBufferDownloadBridge) {
			EntityPlayerBridge player = ((ImageBufferDownloadBridge) buffer).getPlayer();
			if (player != null) return value -> consumer.accept(player, value);
		}
		return null;
	}
}
