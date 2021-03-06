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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.client;

import io.github.orioncraftmc.orion.api.OrionCraft;
import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import io.github.orioncraftmc.orion.api.meta.ClientVersion;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.OneDotFiveBridgeProvider;
import java.io.File;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "getAppDir", at = @At("HEAD"), cancellable = true)
	private static void onGetAppDir(String string, CallbackInfoReturnable<File> cir) {
		cir.setReturnValue(new File("."));
	}

	@ModifyConstant(method = "main", constant = @Constant(stringValue = "Minecraft"))
	private static String onSetFrameTitle(String original) {
		OrionCraft.INSTANCE.startGameEntrypoint(ClientVersion.MC_1_5_2);
		return OrionCraftConstants.INSTANCE.getClientTitle();
	}

	@Redirect(method = "main", at = @At(value = "INVOKE", target = "java/util/Map.put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
	private static <K, V> V onPutUsername(Map<K, V> instance, K k, V v, String[] args) {
		boolean devEnvironment = OrionCraftConstants.INSTANCE.isDevEnvironment();

		if (devEnvironment) {
			if (k.toString().equalsIgnoreCase("sessionid")) {
				instance.put(k, v == null ? (V) "-" : v);
				return v;
			}
			if (k.toString().equalsIgnoreCase("username")) {
				Optional<String> actualUsername = Arrays.stream(args).filter(it -> it != null && !it.startsWith("--"))
						.findFirst();
				instance.put((K) "username", (V) actualUsername.orElseThrow());
				return v;
			}
		}

		instance.put(k, v);
		return v;
	}

	@Inject(method = "startGame", at = @At("HEAD"))
	private void onStartGame(CallbackInfo ci) {
		OrionCraft.INSTANCE.setOrionCraftBridgesEntrypoint(new OneDotFiveBridgeProvider());
	}
}
