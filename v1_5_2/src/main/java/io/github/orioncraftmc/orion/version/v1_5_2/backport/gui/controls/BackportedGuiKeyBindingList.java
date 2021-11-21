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
import io.github.orioncraftmc.orion.version.v1_5_2.backport.gui.BackportedGuiListExtended;
import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringTranslate;

public class BackportedGuiKeyBindingList extends BackportedGuiListExtended {
    private final BackportedGuiControls guiControls;
    private final Minecraft mc;
    private final IGuiListEntry[] listEntries;
    private int maxListLabelWidth = 0;

    public static <T> T[] cloneArray(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    public BackportedGuiKeyBindingList(BackportedGuiControls guiControls, Minecraft minecraft) {
        super(minecraft, guiControls.width, guiControls.height, 20, guiControls.height - 32, 20);
        this.guiControls = guiControls;
        this.mc = minecraft;
        KeyBinding[] keyBindings = cloneArray(minecraft.gameSettings.keyBindings);
        this.listEntries = new IGuiListEntry[keyBindings.length + KeyBinding.keybindArray.size()];
        Arrays.sort(keyBindings);
        int i = 0;
        String string = null;
        int j = keyBindings.length;

        for (KeyBinding keyBinding : keyBindings) {
            String category = ((VanillaKeybindingBridge) keyBinding).getCategory();
            if (!category.equals(string)) {
                string = category;
                this.listEntries[i++] = new CategoryEntry(category);
            }

            int stringWidth = minecraft.fontRenderer.getStringWidth(StringTranslate.getInstance().translateKey(keyBinding.keyDescription));
            if (stringWidth > this.maxListLabelWidth) {
                this.maxListLabelWidth = stringWidth;
            }

            this.listEntries[i++] = new KeyEntry(keyBinding);
        }

    }

    protected int getSize() {
        return Math.toIntExact(Arrays.stream(this.listEntries).filter(Objects::nonNull).count());
    }

    public IGuiListEntry getListEntry(int i) {
        if ((i - 1) <= listEntries.length) {
            return this.listEntries[i];
        }
        return null;
    }

    protected int getScrollBarX() {
        return super.getScrollBarX() + 15;
    }

    public int getListWidth() {
        return super.getListWidth() + 32;
    }

    public class KeyEntry implements IGuiListEntry {
        private final KeyBinding keybinding;
        private final String description;
        private final GuiButton keybindingButton;
        private final GuiButton resetButton;

        private KeyEntry(KeyBinding keyBinding) {
            this.keybinding = keyBinding;
            this.description = StringTranslate.getInstance().translateKey(keyBinding.keyDescription);
            this.keybindingButton = new GuiButton(0, 0, 0, 75, 20, description);
            this.resetButton = new GuiButton(0, 0, 0, 50, 20, "Reset");
        }

        public void drawElement(int i, int j, int k, int l, int m, int n, int o, boolean bl) {
            boolean isSelecting = BackportedGuiKeyBindingList.this.guiControls.buttonId == this.keybinding;
            BackportedGuiKeyBindingList.this.mc.fontRenderer.drawString(this.description, j + 90 - BackportedGuiKeyBindingList.this.maxListLabelWidth, k + m / 2 - BackportedGuiKeyBindingList.this.mc.fontRenderer.FONT_HEIGHT / 2, 16777215);
            this.resetButton.xPosition = j + 190;
            this.resetButton.yPosition = k;
            this.resetButton.enabled = this.keybinding.keyCode != ((VanillaKeybindingBridge) this.keybinding).getDefaultKeyCode();
            this.resetButton.drawButton(BackportedGuiKeyBindingList.this.mc, n, o);
            this.keybindingButton.xPosition = j + 105;
            this.keybindingButton.yPosition = k;
            this.keybindingButton.displayString = GameSettings.getKeyDisplayString(this.keybinding.keyCode);
            boolean isConflicting = false;
            if (this.keybinding.keyCode != 0) {
                KeyBinding[] keyBindings = BackportedGuiKeyBindingList.this.mc.gameSettings.keyBindings;
                int p = keyBindings.length;

                for (KeyBinding keyBinding : keyBindings) {
                    if (keyBinding != this.keybinding && keyBinding.keyCode == ((VanillaKeybindingBridge) this.keybinding).getDefaultKeyCode()) {
                        isConflicting = true;
                        break;
                    }
                }
            }

            if (isSelecting) {
                this.keybindingButton.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.keybindingButton.displayString + EnumChatFormatting.WHITE + " <";
            } else if (isConflicting) {
                this.keybindingButton.displayString = EnumChatFormatting.RED + this.keybindingButton.displayString;
            }

            this.keybindingButton.drawButton(BackportedGuiKeyBindingList.this.mc, n, o);
        }

        public boolean onMouseClicked(int i, int j, int k, int l, int m, int n) {
            if (this.keybindingButton.mousePressed(BackportedGuiKeyBindingList.this.mc, j, k)) {
                BackportedGuiKeyBindingList.this.guiControls.buttonId = this.keybinding;
                return true;
            } else if (this.resetButton.mousePressed(BackportedGuiKeyBindingList.this.mc, j, k)) {
                List<KeyBinding> keyBindings = Arrays.stream(mc.gameSettings.keyBindings).toList();
                BackportedGuiKeyBindingList.this.mc.gameSettings.setKeyBinding(keyBindings.indexOf(this.keybinding), ((VanillaKeybindingBridge) this.keybinding).getDefaultKeyCode());
                KeyBinding.resetKeyBindingArrayAndHash();
                return true;
            } else {
                return false;
            }
        }

        public void onMouseReleased(int i, int j, int k, int l, int m, int n) {
            this.keybindingButton.mouseReleased(j, k);
            this.resetButton.mouseReleased(j, k);
        }

        public void drawSelectionBox(int i, int j, int k) {
        }
    }

    public class CategoryEntry implements IGuiListEntry {
        private final String name;
        private final int nameWidth;

        public CategoryEntry(String string) {
            this.name = string;
            this.nameWidth = BackportedGuiKeyBindingList.this.mc.fontRenderer.getStringWidth(this.name);
        }

        public void drawElement(int i, int j, int k, int l, int m, int n, int o, boolean bl) {
            BackportedGuiKeyBindingList.this.mc.fontRenderer.drawString(this.name, BackportedGuiKeyBindingList.this.mc.currentScreen.width / 2 - this.nameWidth / 2, k + m - BackportedGuiKeyBindingList.this.mc.fontRenderer.FONT_HEIGHT - 1, 16777215);
        }

        public boolean onMouseClicked(int i, int j, int k, int l, int m, int n) {
            return false;
        }

        public void onMouseReleased(int i, int j, int k, int l, int m, int n) {
        }

        public void drawSelectionBox(int i, int j, int k) {
        }
    }
}
