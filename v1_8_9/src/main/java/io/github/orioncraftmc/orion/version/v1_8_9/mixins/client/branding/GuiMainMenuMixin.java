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

package io.github.orioncraftmc.orion.version.v1_8_9.mixins.client.branding;

import io.github.orioncraftmc.orion.screens.menu.main.MainMenuScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public abstract class GuiMainMenuMixin extends GuiScreen {

	MainMenuScreen orionMainMenu = new MainMenuScreen() {
		@Override
		public void superHandleMouseClick(int i, int i1, int i2) {
			GuiMainMenuMixin.super.mouseClicked(i, i1, i2);
		}

		@Override
		public void superDrawScreen(int i, int i1, float v) {
			GuiMainMenuMixin.super.drawScreen(i, i1, v);
		}

		@Override
		public void renderSkybox(int i, int i1, float v) {
			GuiMainMenuMixin.this.renderSkybox(i, i1, v);
		}
	};

	@Redirect(method = "<clinit>", at=@At(value = "NEW", target = "net/minecraft/util/ResourceLocation"))
	private static ResourceLocation onVanillaPanoramaCreation(String original) {
		if (original.startsWith("textures/gui/title/background/panorama_")) {
			return new ResourceLocation("orion", "panorama/panorama_" + original.substring(39));
		}
		return new ResourceLocation(original);
	}

	@Inject(method = "initGui", at = @At("TAIL"))
	public void onInitGui(CallbackInfo ci) {
		orionMainMenu.internalOnResize();
	}

	@Shadow
	public abstract void renderSkybox(int mouseX, int mouseY, float renderPartialTicks);

	/**
	 * @reason Main menu rendering is explicitly handled by API
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		orionMainMenu.drawScreen(mouseX, mouseY, renderPartialTicks);
	}

	/**
	 * @reason Main menu clicks are explicitly handled by API
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void mouseClicked(int mouseX, int mouseY, int clickedButtonId) {
		orionMainMenu.handleMouseClick(mouseX, mouseY, clickedButtonId);
	}

	@Override
	public void mouseReleased(int i, int j, int k) {
		if (k == 0) {
			orionMainMenu.handleMouseRelease(i, j);
		} else {
			orionMainMenu.handleMouseMove(i, j, k);
		}
	}
}
