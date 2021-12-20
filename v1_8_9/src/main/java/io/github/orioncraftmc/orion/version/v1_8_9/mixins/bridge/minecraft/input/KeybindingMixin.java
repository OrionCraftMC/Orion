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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft.input;

import io.github.orioncraftmc.orion.api.bridge.input.VanillaKeybindingBridge;
import net.minecraft.client.settings.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

@Mixin(KeyBinding.class)
public class KeybindingMixin implements VanillaKeybindingBridge {
	@Shadow
	public int keyCode;

	@Final
	@Shadow
	public String keyDescription;

	@Shadow
	public boolean pressed;

	@Shadow
	@Final
	public int keyCodeDefault;

	@Override
	public int getDefaultKeyCode() {
		return keyCodeDefault;
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
