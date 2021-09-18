package io.github.orioncraftmc.orion.version.v1_5_2.bridge.gui;

import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import net.minecraft.client.gui.GuiScreen;

public class OrionGuiScreen extends GuiScreen {
	private final OrionScreen screen;

	@Override
	public void initGui() {
		screen.internalOnResize();
	}

	public OrionGuiScreen(OrionScreen screen) {
		this.screen = screen;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		screen.drawScreen(i, j, f);
	}

	@Override
	public void mouseClicked(int i, int j, int k) {
		screen.handleMouseClick(i, j, k);
	}
}
