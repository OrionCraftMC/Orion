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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.gui.controls;

import io.github.orioncraftmc.orion.api.bridge.minecraft.input.VanillaKeybindingBridge;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StringTranslate;

public class BackportedGuiControls extends GuiScreen {
    private final GuiScreen parentScreen;
    protected String screenTitle = "Controls";
    private final GameSettings options;
    public KeyBinding buttonId = null;
    public long time;
    private BackportedGuiKeyBindingList keyBindingList;
    private GuiButton buttonReset;

    public BackportedGuiControls(GuiScreen guiScreen, GameSettings gameSettings) {
        this.parentScreen = guiScreen;
        this.options = gameSettings;
    }

    @SuppressWarnings("unchecked")
    public void initGui() {
        this.keyBindingList = new BackportedGuiKeyBindingList(this, this.mc);
        this.buttonList.add(new GuiButton(200, this.width / 2 - 155, this.height - 29, 150, 20, "Done"));
        this.buttonList.add(this.buttonReset = new GuiButton(201, this.width / 2 - 155 + 160, this.height - 29, 150, 20, "Reset All"));
        this.screenTitle = StringTranslate.getInstance().translateKey("controls.title");
    }

    public void handleMouseInput() {
        super.handleMouseInput();
        this.keyBindingList.handleMouseInput();
    }

    public void actionPerformed(GuiButton guiButton) {
        if (guiButton.id == 200) {
            this.mc.displayGuiScreen(this.parentScreen);
        } else if (guiButton.id == 201) {
            KeyBinding[] keyBindings = this.mc.gameSettings.keyBindings;
            int i = keyBindings.length;

            for(int j = 0; j < i; ++j) {
                KeyBinding keyBinding = keyBindings[j];
                if (keyBinding instanceof VanillaKeybindingBridge) {
                    mc.gameSettings.setKeyBinding(j, ((VanillaKeybindingBridge) keyBinding).getDefaultKeyCode());
                }
            }

            KeyBinding.resetKeyBindingArrayAndHash();
        }
    }

    public void mouseClicked(int i, int j, int k) {
        if (buttonId != null) {
            this.options.setKeyBinding(getButtonId(), -100 + k);
            this.buttonId = null;
            KeyBinding.resetKeyBindingArrayAndHash();
        } else if (k != 0 || !this.keyBindingList.mouseClicked(i, j, k)) {
            super.mouseClicked(i, j, k);
        }
    }

    private int getButtonId() {
        List<KeyBinding> keyBindings = Arrays.stream(mc.gameSettings.keyBindings).toList();
        if (buttonId == null) return -1;
        return Math.max(0, keyBindings.indexOf(buttonId));
    }

    protected void mouseReleased(int i, int j, int k) {
        if (k == 0) {
            this.keyBindingList.mouseReleased(i, j, k);
        }
    }

    public void keyTyped(char c, int i) {
        if (buttonId != null) {
            if (i == 1) {
                this.options.setKeyBinding(getButtonId(), 0);
            } else if (i != 0) {
                this.options.setKeyBinding(getButtonId(), i);
            } else if (c > 0) {
                this.options.setKeyBinding(getButtonId(), c + 256);
            }

            this.buttonId = null;
            this.time = Minecraft.getSystemTime();
            KeyBinding.resetKeyBindingArrayAndHash();
        } else {
            super.keyTyped(c, i);
        }

    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        this.keyBindingList.drawScreen(i, j, f);
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 8, 16777215);
        boolean bl = true;
        KeyBinding[] keyBindings = this.options.keyBindings;
        int k = keyBindings.length;

        for (KeyBinding keyBinding : keyBindings) {
            if (keyBinding.keyCode != ((VanillaKeybindingBridge) keyBinding).getDefaultKeyCode()) {
                bl = false;
                break;
            }
        }

        this.buttonReset.enabled = !bl;
        super.drawScreen(i, j, f);
    }
}
