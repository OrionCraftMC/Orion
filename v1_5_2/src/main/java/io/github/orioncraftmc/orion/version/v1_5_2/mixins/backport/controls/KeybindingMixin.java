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

import io.github.orioncraftmc.orion.api.bridge.minecraft.input.VanillaKeybindingBridge;
import io.github.orioncraftmc.orion.backport.hooks.NostalgiaKeybindingsHook;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StringTranslate;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyBinding.class)
public class KeybindingMixin implements Comparable<KeyBinding> {
	@Override
	public int compareTo(@NotNull KeyBinding other) {
		int comparisonIndex = StringTranslate.getInstance()
				.translateKey(NostalgiaKeybindingsHook.INSTANCE.getKeybindingCategory(((VanillaKeybindingBridge) this)))
				.compareTo(StringTranslate.getInstance().translateKey(
						NostalgiaKeybindingsHook.INSTANCE.getKeybindingCategory(((VanillaKeybindingBridge) other))));
		if (comparisonIndex == 0) {
			comparisonIndex = StringTranslate.getInstance()
					.translateKey(((VanillaKeybindingBridge) this).getDescription()).compareTo(
							StringTranslate.getInstance()
									.translateKey(((VanillaKeybindingBridge) other).getDescription()));
		}
		return comparisonIndex;
	}
}
