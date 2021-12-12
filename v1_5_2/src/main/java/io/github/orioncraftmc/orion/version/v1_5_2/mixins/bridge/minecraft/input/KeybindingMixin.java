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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft.input;

import io.github.orioncraftmc.orion.api.bridge.input.VanillaKeybindingBridge;
import net.minecraft.client.settings.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeybindingMixin implements VanillaKeybindingBridge {
	@Shadow
	public int keyCode;

	@Shadow
	public String keyDescription;

	@Shadow
	public boolean pressed;
	private int defaultKeyCode;

	@Inject(method = "<init>", at = @At("TAIL"))
	void onConstruction(String string, int i, CallbackInfo ci) {
		defaultKeyCode = i;
	}

	@Override
	public int getDefaultKeyCode() {
		return defaultKeyCode;
	}

	@NotNull
	@Override
	public String getDescription() {
		return keyDescription;
	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public void setKeyCode(int i) {
		keyCode = i;
	}

	@Override
	public boolean bridge$isPressed() {
		return pressed;
	}

	@Override
	public void bridge$setPressed(boolean b) {
		pressed = b;
	}
}
