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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.controls;

import io.github.orioncraftmc.orion.api.bridge.minecraft.input.VanillaKeybindingBridge;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StringTranslate;

public class OrionGuiControls extends GuiScreen {
	/**
	 * A reference to the screen object that created this. Used for navigating between screens.
	 */
	public GuiScreen parentScreen;
	public String screenTitle = "Controls";
	/**
	 * Reference to the GameSettings object.
	 */
	public GameSettings options;
	/**
	 * The ID of the button that has been pressed.
	 */
	public VanillaKeybindingBridge buttonId = null;
	public GuiKeyBindingList keyBindingList;
	public GuiButton buttonReset;

	public OrionGuiControls(GuiScreen parent, GameSettings settings) {
		this.parentScreen = parent;
		this.options = settings;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the window resizes, the buttonList is cleared beforehand.
	 */
	@Override
	public void initGui() {
		this.keyBindingList = new GuiKeyBindingList(this, this.mc);
		this.buttonList.add(new GuiButton(200, this.width / 2 - 155, this.height - 29, 150, 20,
				StringTranslate.getInstance().translateKey("gui.done")));
		this.buttonReset = new GuiButton(201, this.width / 2 - 155 + 160, this.height - 29, 150, 20,
				StringTranslate.getInstance().translateKey("controls.resetAll"));
		this.buttonList.add(this.buttonReset);
		this.screenTitle = StringTranslate.getInstance().translateKey("controls.title");
	}

	/**
	 * Handles mouse input.
	 */
	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		this.keyBindingList.handleMouseInput();
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	@Override
	public void actionPerformed(GuiButton avs2) {
		if (avs2.id == 200) {
			this.mc.displayGuiScreen(this.parentScreen);
		} else if (avs2.id == 201) {
			for (KeyBinding avb6 : this.mc.gameSettings.keyBindings) {
				((VanillaKeybindingBridge) avb6).setKeyCode(((VanillaKeybindingBridge) avb6).getDefaultKeyCode());
			}
			KeyBinding.resetKeyBindingArrayAndHash();
		}
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	@Override
	public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
		if (this.buttonId != null) {
			this.options.setKeyBinding(getButtonId(), -100 + clickedButton);
			this.buttonId = null;
			KeyBinding.resetKeyBindingArrayAndHash();
		} else if (clickedButton != 0 || !this.keyBindingList.mouseClicked(mouseX, mouseY, clickedButton)) {
			super.mouseClicked(mouseX, mouseY, clickedButton);
		}
	}


	/**
	 * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	@Override
	public void keyTyped(char character, int keyCode) {
		if (this.buttonId != null) {
			this.options.setKeyBinding(getButtonId(), keyCode);
			OrionGuiListExtended.IGuiListEntry buttonEntry = keyBindingList.listEntries.stream()
					.filter(e -> e instanceof GuiKeyBindingList.KeyEntry)
					.filter(f -> ((GuiKeyBindingList.KeyEntry) f).keybinding == this.buttonId).findFirst().orElse(null);
			if (buttonEntry instanceof GuiKeyBindingList.KeyEntry) {
				((GuiKeyBindingList.KeyEntry) buttonEntry).btnChangeKeyBinding.displayString = this.options.getOptionDisplayString(
						getButtonId());
			}
			this.buttonId = null;
			KeyBinding.resetKeyBindingArrayAndHash();
		} else {
			super.keyTyped(character, keyCode);
		}
	}

	private int getButtonId() {
		List<KeyBinding> keyBindings = Arrays.stream(mc.gameSettings.keyBindings).toList();
		if (buttonId == null) return -1;
		return Math.max(0, keyBindings.indexOf(((KeyBinding) buttonId)));
	}


	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		this.drawDefaultBackground();
		this.keyBindingList.drawScreen(mouseX, mouseY, renderPartialTicks);
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 8, 0xFFFFFF);
		boolean boolean5 = true;
		for (KeyBinding avb9 : this.options.keyBindings) {
			VanillaKeybindingBridge keyBinding = ((VanillaKeybindingBridge) avb9);
			if (keyBinding.getKeyCode() == keyBinding.getDefaultKeyCode()) continue;
			boolean5 = false;
			break;
		}
		this.buttonReset.enabled = !boolean5;
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
}
