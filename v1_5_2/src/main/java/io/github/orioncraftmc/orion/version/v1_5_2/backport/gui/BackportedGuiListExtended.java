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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.gui;

import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.Nullable;

public abstract class BackportedGuiListExtended extends BackportedGuiSlot {
    public BackportedGuiListExtended(Minecraft minecraft, int width, int height, int top, int bottom, int slotHeight) {
        super(minecraft, width, height, top, bottom, slotHeight);
    }

    protected void elementClicked(int i, boolean bl, int j, int k) {
    }

    protected boolean isSelected(int i) {
        return false;
    }

    protected void drawBackground() {
    }

    protected void drawSlot(int i, int j, int k, int l, int m, int n) {
        IGuiListEntry listEntry = this.getListEntry(i);
        if (listEntry != null) {
            listEntry.drawElement(i, j, k, this.getListWidth(), l, m, n, this.getSlotIndexFromScreenCoords(m, n) == i);
        }
    }

    protected void drawSelectionBox(int i, int j, int k) {
        IGuiListEntry listEntry = getListEntry(i);
        if (listEntry != null) {
            listEntry.drawSelectionBox(i, j, k);
        }
    }

    public boolean mouseClicked(int i, int j, int k) {
        if (this.isMouseYWithinSlotBounds(j)) {
            int l = this.getSlotIndexFromScreenCoords(i, j);
            if (l >= 0) {
                int m = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
                int n = this.top + 4 - this.getAmountScrolled() + l * this.slotHeight + this.headerPadding;
                int o = i - m;
                int p = j - n;
                IGuiListEntry listEntry = this.getListEntry(l);
                if (listEntry != null && listEntry.onMouseClicked(l, i, j, k, o, p)) {
                    this.setEnabled(false);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean mouseReleased(int i, int j, int k) {
        for (int l = 0; l < this.getSize(); ++l) {
            int m = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int n = this.top + 4 - this.getAmountScrolled() + l * this.slotHeight + this.headerPadding;
            int o = i - m;
            int p = j - n;
            IGuiListEntry listEntry = this.getListEntry(l);
            if (listEntry != null) {
                listEntry.onMouseReleased(l, i, j, k, o, p);
            }
        }

        this.setEnabled(true);
        return false;
    }

    @Nullable
    public abstract IGuiListEntry getListEntry(int i);

    public interface IGuiListEntry {
        void drawSelectionBox(int i, int j, int k);

        void drawElement(int i, int j, int k, int l, int m, int n, int o, boolean bl);

        boolean onMouseClicked(int i, int j, int k, int l, int m, int n);

        void onMouseReleased(int i, int j, int k, int l, int m, int n);
    }
}
