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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.controls;

import io.github.orioncraftmc.orion.api.bridge.input.VanillaKeybindingBridge;
import io.github.orioncraftmc.orion.backport.hooks.NostalgiaKeybindingsHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow
	public GameSettings gameSettings;

	@Redirect(method = "runTick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 13))
	private int noOpOriginalTogglePerspective() {
		return 0;
	}

	@Redirect(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;onTick(I)V"))
	private void onTickKeybinding(int i) {
		KeyBinding.onTick(i);
		checkTickTogglePerspectiveKey(i);
	}

	private void checkTickTogglePerspectiveKey(int i) {
		VanillaKeybindingBridge newKeybinding = NostalgiaKeybindingsHook.INSTANCE.getTogglePerspectiveKeybinding();
		if (newKeybinding != null && newKeybinding.getKeyCode() == i) {
			++this.gameSettings.thirdPersonView;
			if (this.gameSettings.thirdPersonView > 2) {
				this.gameSettings.thirdPersonView = 0;
			}
		}
	}

}
