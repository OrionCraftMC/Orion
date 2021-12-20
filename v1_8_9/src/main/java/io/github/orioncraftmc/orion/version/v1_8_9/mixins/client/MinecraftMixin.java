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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.client;

import io.github.orioncraftmc.orion.api.OrionCraft;
import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import io.github.orioncraftmc.orion.api.meta.ClientVersion;
import io.github.orioncraftmc.orion.version.v1_8_9.bridge.OneDotEightBridgeProvider;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
	private String onSetFrameTitle(String original) {
		return OrionCraftConstants.INSTANCE.getClientTitle();
	}

	@Inject(method = "startGame", at = @At("RETURN"))
	private void onStartGame(CallbackInfo ci) {
		OrionCraft.INSTANCE.startGameEntrypoint(ClientVersion.MC_1_8_9);
	}

	@Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;serverName:Ljava/lang/String;"))
	private void onFinishStartGame(CallbackInfo ci) {
		OrionCraft.INSTANCE.setOrionCraftBridgesEntrypoint(new OneDotEightBridgeProvider());
	}
}
