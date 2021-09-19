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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.fixes.npefix;

import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerList.class)
public class ServerListMixin {

	@Inject(method = "loadServerList", locals = LocalCapture.CAPTURE_FAILHARD,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;getTagList(Ljava/lang/String;)Lnet/minecraft/nbt/NBTTagList;"),
			cancellable = true
	)
	public void onGetTagListFromCompound(CallbackInfo ci, @Nullable NBTTagCompound serversCompound) {
		if (serversCompound == null) {
			// If the servers.dat file doesn't exist, this will be null.
			// Since there is no null check, an NPE is thrown
			ci.cancel();
		}
	}
}
