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

import io.github.orioncraftmc.orion.api.ExtensionsKt;
import io.github.orioncraftmc.orion.api.backport.hooks.PlayerTexturesHook;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.minecraft.ThreadDownloadImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
	public IImageBuffer buffer;

	@Shadow
	@Final
	public ThreadDownloadImageData imageData;

	@Inject(method = "run", at = @At("HEAD"), cancellable = true)
	public void onDownloadImage(CallbackInfo ci) {
		if (!location.startsWith("orion_")) {
			ExtensionsKt.getLogger().debug("Not intercepting image from " + location);
			return;
		}
		String[] downloadData;
		try {
			downloadData = location.split("_", 3);
			ExtensionsKt.getLogger().debug(Arrays.toString(downloadData));
			if (downloadData.length != 3) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		ci.cancel();


		String part = downloadData[1];
		String name = downloadData[2];
		byte[] result = null;

		ExtensionsKt.getLogger().debug("Downloading part " + part + " for name " + name);
		switch (part) {
			case "skin" -> result = PlayerTexturesHook.INSTANCE.getPlayerSkin(name);
			case "cloak" -> result = PlayerTexturesHook.INSTANCE.getPlayerCloak(name);
		}

		if (result != null) {
			ExtensionsKt.getLogger().debug("Got image result from OrionCraft");
			try (ByteArrayInputStream byteStream = new ByteArrayInputStream(result)) {

				//if (this.buffer == null) {
				this.imageData.image = ImageIO.read(byteStream);
				ExtensionsKt.getLogger().debug("Passed skin data to imageData");
				//} else {
				//	this.imageData.image = this.buffer.parseUserSkin(ImageIO.read(byteStream));
				//}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
