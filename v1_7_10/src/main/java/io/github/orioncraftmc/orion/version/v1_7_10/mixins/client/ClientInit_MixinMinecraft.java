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

package io.github.orioncraftmc.orion.version.v1_7_10.mixins.client;

import io.github.orioncraftmc.orion.api.OrionCraft;
import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import io.github.orioncraftmc.orion.api.meta.ClientVersion;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ClientInit_MixinMinecraft {

	@Inject(method = "startGame", at = @At("HEAD"))
	public void onStartGame(CallbackInfo ci) {
		OrionCraft.INSTANCE.startGameEntrypoint(ClientVersion.MC_1_7_10);
	}

	@ModifyArg(method = "startGame", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/Display.setTitle(Ljava/lang/String;)V"), index = 0)
	public String onSetDisplayTitle(String newTitle) {
		return OrionCraftConstants.INSTANCE.getClientTitle();
	}

}
