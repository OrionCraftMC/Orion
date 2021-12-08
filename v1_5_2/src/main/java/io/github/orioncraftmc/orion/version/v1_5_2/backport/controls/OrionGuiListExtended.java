package io.github.orioncraftmc.orion.version.v1_5_2.backport.controls;

import net.minecraft.client.Minecraft;

/*
 * Decompiled with CFR 0.0.6 (FabricMC 437c16e1).
 */
public abstract class OrionGuiListExtended
extends OrionGuiSlot {
    public OrionGuiListExtended(Minecraft ave2, int integer2, int integer3, int integer4, int integer5, int integer6) {
        super(ave2, integer2, integer3, integer4, integer5, integer6);
    }

    /**
     * The element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    @Override
    public void elementClicked(int integer1, boolean boolean2, int integer3, int integer4) {
    }

    /**
     * Returns true if the element passed in is currently selected
     */
    @Override
    public boolean isSelected(int integer) {
        return false;
    }

    @Override
    public void drawBackground() {
    }

    @Override
    public void drawSlot(int integer1, int integer2, int integer3, int integer4, int integer5, int integer6) {
        this.getListEntry(integer1).drawEntry(integer1, integer2, integer3, this.getListWidth(), integer4, integer5, integer6, this.getSlotIndexFromScreenCoords(integer5, integer6) == integer1);
    }

    @Override
    public void func_178040_a(int integer1, int integer2, int integer3) {
        this.getListEntry(integer1).setSelected(integer1, integer2, integer3);
    }

    public boolean mouseClicked(int integer1, int integer2, int integer3) {
        int integer5;
        if (this.isMouseYWithinSlotBounds(integer2) && (integer5 = this.getSlotIndexFromScreenCoords(integer1, integer2)) >= 0) {
            int integer6 = this.left + (this.width / 2 - this.getListWidth() / 2 + 2);
            int integer7 = this.top + 4 - this.getAmountScrolled() + (integer5 * this.slotHeight + this.headerPadding);
            int integer8 = integer1 - integer6;
            int integer9 = integer2 - integer7;
            if (this.getListEntry(integer5).mousePressed(integer5, integer1, integer2, integer3, integer8, integer9)) {
                this.setEnabled(false);
                return true;
            }
        }
        return false;
    }

    public boolean mouseReleased(int integer1, int integer2, int integer3) {
        for (int integer5 = 0; integer5 < this.getSize(); ++integer5) {
            int integer6 = this.left + (this.width / 2 - this.getListWidth() / 2 + 2);
            int integer7 = this.top + 4 - this.getAmountScrolled() + (integer5 * this.slotHeight + this.headerPadding);
            int integer8 = integer1 - integer6;
            int integer9 = integer2 - integer7;
            this.getListEntry(integer5).mouseReleased(integer5, integer1, integer2, integer3, integer8, integer9);
        }
        this.setEnabled(true);
        return false;
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public abstract IGuiListEntry getListEntry(int var1);

    public interface IGuiListEntry {
        void setSelected(int var1, int var2, int var3);

        void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8);

        /**
         * Returns true if the mouse has been pressed on this control.
         */
		boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6);

        /**
         * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
         */
		void mouseReleased(int index, int x, int y, int mouseEvent, int relativeX, int relativeY);
    }
}
