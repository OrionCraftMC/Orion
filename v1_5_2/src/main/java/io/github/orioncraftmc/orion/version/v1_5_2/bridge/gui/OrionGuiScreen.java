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

package io.github.orioncraftmc.orion.version.v1_5_2.bridge.gui;

import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import net.minecraft.client.gui.GuiScreen;

public class OrionGuiScreen extends GuiScreen {
	private final OrionScreen screen;

	public OrionGuiScreen(OrionScreen screen) {
		this.screen = screen;
	}

	@Override
	public void initGui() {
		screen.internalOnResize();
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		screen.drawScreen(i, j, f);
	}

	@Override
	public void mouseClicked(int i, int j, int k) {
		screen.handleMouseClick(i, j, k);
	}

	@Override
	public void mouseReleased(int i, int j, int k) {
		if (k == 0) {
			screen.handleMouseRelease(i, j);
		} else {
			screen.handleMouseMove(i, j, k);
		}
	}

	public OrionScreen getScreen() {
		return screen;
	}

	@Override
	public void onGuiClosed() {
		screen.onClose();
	}
}
