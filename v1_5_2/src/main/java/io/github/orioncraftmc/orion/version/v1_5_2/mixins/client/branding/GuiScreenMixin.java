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

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.client.branding;

import io.github.orioncraftmc.orion.api.bridge.gui.GuiScreenBridge;
import io.github.orioncraftmc.orion.screens.GuiScreenOrionBrandingScreen;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.gui.OrionGuiScreen;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class GuiScreenMixin {

	private GuiScreenOrionBrandingScreen gameGuiScreen = null;

	private GuiScreenBridge bridge$getGuiScreenBridge() {
		return ((GuiScreen) (Object) this) instanceof OrionGuiScreen orionGuiScreen ? orionGuiScreen.getScreen() : (GuiScreenBridge) this;
	}

	@Inject(method = "drawWorldBackground", at = @At("RETURN"))
	public void onDrawWorldBackground(int par1, CallbackInfo ci) {
		if (gameGuiScreen == null) {
			gameGuiScreen = new GuiScreenOrionBrandingScreen(bridge$getGuiScreenBridge());
		}
		gameGuiScreen.drawScreen();
	}
}
