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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.gui.controls;

import io.github.orioncraftmc.orion.version.v1_5_2.backport.gui.controls.BackportedGuiControls;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiOptions.class)
public class GuiOptionsMixin {
	@Shadow
	@Final
	public GameSettings options;

	@Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
	public void onNewGuiControls(GuiButton guiButton, CallbackInfo ci) {
		if (guiButton.id == 100) {
			ci.cancel();
			Minecraft.getMinecraft().gameSettings.saveOptions();
			Minecraft.getMinecraft().displayGuiScreen(new BackportedGuiControls(((GuiOptions) (Object) this), this.options));
		}
	}
}
