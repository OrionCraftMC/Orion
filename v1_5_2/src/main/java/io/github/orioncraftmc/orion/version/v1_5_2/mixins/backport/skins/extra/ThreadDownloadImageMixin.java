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

import io.github.orioncraftmc.orion.backport.hooks.PlayerTexturesHook;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.EntityPlayerGameProfileDuck;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.ImageBufferDownloadDuck;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.ThreadDownloadImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.player.EntityPlayer;
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
				getPlayerSkinHandler(EntityPlayerGameProfileDuck::setIsSlimSkin),
				getPlayerSkinHandler(EntityPlayerGameProfileDuck::setIsOldSkinModel)
		);
	}

	@Nullable
	private Consumer<Boolean> getPlayerSkinHandler(BiConsumer<EntityPlayerGameProfileDuck, Boolean> consumer) {
		if (buffer instanceof ImageBufferDownloadDuck) {
			EntityPlayer player = ((ImageBufferDownloadDuck) buffer).getPlayer();
			if (player != null) return value -> consumer.accept((EntityPlayerGameProfileDuck) player, value);
		}
		return null;
	}
}
