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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class OrionGuiSlot {
	public final Minecraft mc;
	/**
	 * The height of a slot.
	 */
	public final int slotHeight;
	public int width;
	public int height;
	/**
	 * The top of the slot container. Affects the overlays and scrolling.
	 */
	public int top;
	/**
	 * The bottom of the slot container. Affects the overlays and scrolling.
	 */
	public int bottom;
	public int right;
	public int left;
	/**
	 * The buttonID of the button used to scroll up
	 */
	public int scrollUpButtonID;
	/**
	 * The buttonID of the button used to scroll down
	 */
	public int scrollDownButtonID;
	public int mouseX;
	public int mouseY;
	public boolean field_148163_i = true;
	/**
	 * Where the mouse was in the window when you first clicked to scroll
	 */
	public int initialClickY = -2;
	/**
	 * What to multiply the amount you moved your mouse by (used for slowing down scrolling when over the items and not on the scroll bar)
	 */
	public float scrollMultiplier;
	/**
	 * How far down this slot has been scrolled
	 */
	public float amountScrolled;
	/**
	 * The element in the list that was selected
	 */
	public int selectedElement = -1;
	/**
	 * The time when this button was last clicked.
	 */
	public long lastClicked;
	public boolean field_178041_q = true;
	/**
	 * Set to true if a selected element in this gui will show an outline box
	 */
	public boolean showSelectionBox = true;
	public boolean hasListHeader;
	public int headerPadding;
	public boolean enabled = true;

	public OrionGuiSlot(Minecraft ave2, int integer2, int integer3, int integer4, int integer5, int integer6) {
		this.mc = ave2;
		this.width = integer2;
		this.height = integer3;
		this.top = integer4;
		this.bottom = integer5;
		this.slotHeight = integer6;
		this.left = 0;
		this.right = integer2;
	}

	public void setDimensions(int integer1, int integer2, int integer3, int integer4) {
		this.width = integer1;
		this.height = integer2;
		this.top = integer3;
		this.bottom = integer4;
		this.left = 0;
		this.right = integer1;
	}

	public void setShowSelectionBox(boolean boolean1) {
		this.showSelectionBox = boolean1;
	}

	/**
	 * Sets hasListHeader and headerHeight. Params: hasListHeader, headerHeight. If hasListHeader is false headerHeight is set to 0.
	 */
	public void setHasListHeader(boolean boolean1, int integer) {
		this.hasListHeader = boolean1;
		this.headerPadding = integer;
		if (!boolean1) {
			this.headerPadding = 0;
		}
	}

	public abstract int getSize();

	/**
	 * The element in the slot that was clicked, boolean for whether it was double clicked or not
	 */
	public abstract void elementClicked(int var1, boolean var2, int var3, int var4);

	/**
	 * Returns true if the element passed in is currently selected
	 */
	public abstract boolean isSelected(int var1);

	/**
	 * Return the height of the content being scrolled
	 */
	public int getContentHeight() {
		return this.getSize() * this.slotHeight + this.headerPadding;
	}

	public abstract void drawBackground();

	public void func_178040_a(int integer1, int integer2, int integer3) {
	}

	public abstract void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6);

	/**
	 * Handles drawing a list's header row.
	 */
	public void drawListHeader(int integer1, int integer2, Tessellator bfx2) {
	}

	public void func_148132_a(int integer1, int integer2) {
	}

	public void func_148142_b(int integer1, int integer2) {
	}

	public int getSlotIndexFromScreenCoords(int integer1, int integer2) {
		int integer4 = this.left + this.width / 2 - this.getListWidth() / 2;
		int integer5 = this.left + this.width / 2 + this.getListWidth() / 2;
		int integer6 = integer2 - this.top - this.headerPadding + (int) this.amountScrolled - 4;
		int integer7 = integer6 / this.slotHeight;
		if (integer1 < this.getScrollBarX() && integer1 >= integer4 && integer1 <= integer5 && integer7 >= 0 && integer6 >= 0 && integer7 < this.getSize()) {
			return integer7;
		}
		return -1;
	}

	/**
	 * Registers the IDs that can be used for the scrollbar's up/down buttons.
	 */
	public void registerScrollButtons(int integer1, int integer2) {
		this.scrollUpButtonID = integer1;
		this.scrollDownButtonID = integer2;
	}

	/**
	 * Stop the thing from scrolling out of bounds
	 */
	public void bindAmountScrolled() {
		this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0f, (float) this.func_148135_f());
	}

	public int func_148135_f() {
		return Math.max(0, this.getContentHeight() - (this.bottom - this.top - 4));
	}

	/**
	 * Returns the amountScrolled field as an integer.
	 */
	public int getAmountScrolled() {
		return (int) this.amountScrolled;
	}

	public boolean isMouseYWithinSlotBounds(int integer) {
		return integer >= this.top && integer <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.right;
	}

	/**
	 * Scrolls the slot by the given amount. A positive value scrolls down, and a negative value scrolls up.
	 */
	public void scrollBy(int integer) {
		this.amountScrolled += (float) integer;
		this.bindAmountScrolled();
		this.initialClickY = -2;
	}

	public void actionPerformed(GuiButton avs2) {
		if (!avs2.enabled) {
			return;
		}
		if (avs2.id == this.scrollUpButtonID) {
			this.amountScrolled -= (float) (this.slotHeight * 2 / 3);
			this.initialClickY = -2;
			this.bindAmountScrolled();
		} else if (avs2.id == this.scrollDownButtonID) {
			this.amountScrolled += (float) (this.slotHeight * 2 / 3);
			this.initialClickY = -2;
			this.bindAmountScrolled();
		}
	}

	public void drawScreen(int integer1, int integer2, float float3) {
		if (!this.field_178041_q) {
			return;
		}
		this.mouseX = integer1;
		this.mouseY = integer2;
		this.drawBackground();
		int integer5 = this.getScrollBarX();
		int integer6 = integer5 + 6;
		this.bindAmountScrolled();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator tesselator = Tessellator.instance;
		this.mc.renderEngine.bindTexture("/gui/background.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var18 = 32.0F;
		tesselator.startDrawingQuads();
		tesselator.setColorOpaque_I(0x202020);
		tesselator.addVertexWithUV(this.left, this.bottom, 0.0, (float) this.left / var18,
				(float) (this.bottom + (int) this.amountScrolled) / var18);
		tesselator.addVertexWithUV(this.right, this.bottom, 0.0, (float) this.right / var18,
				(float) (this.bottom + (int) this.amountScrolled) / var18);
		tesselator.addVertexWithUV(this.right, this.top, 0.0, (float) this.right / var18,
				(float) (this.top + (int) this.amountScrolled) / var18);
		tesselator.addVertexWithUV(this.left, this.top, 0.0, (float) this.left / var18,
				(float) (this.top + (int) this.amountScrolled) / var18);
		tesselator.draw();
		int integer10 = this.left + (this.width / 2 - this.getListWidth() / 2 + 2);
		int integer11 = this.top + 4 - (int) this.amountScrolled;
		if (this.hasListHeader) {
			this.drawListHeader(integer10, integer11, tesselator);
		}
		this.drawSelectionBox(integer10, integer11, integer1, integer2);
		GL11.glDisable(2929);
		byte var22 = 4;
		this.overlayBackground(0, this.top, 255, 255);
		this.overlayBackground(this.bottom, this.height, 255, 255);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3008);
		GL11.glShadeModel(7425);
		GL11.glDisable(3553);
		tesselator.startDrawingQuads();
		tesselator.setColorRGBA_I(0, 0);
		tesselator.addVertexWithUV(this.left, this.top + var22, 0.0, 0.0, 1.0);
		tesselator.addVertexWithUV(this.right, this.top + var22, 0.0, 1.0, 1.0);
		tesselator.setColorRGBA_I(0, 255);
		tesselator.addVertexWithUV(this.right, this.top, 0.0, 1.0, 0.0);
		tesselator.addVertexWithUV(this.left, this.top, 0.0, 0.0, 0.0);
		tesselator.draw();
		tesselator.startDrawingQuads();
		tesselator.setColorRGBA_I(0, 255);
		tesselator.addVertexWithUV(this.left, this.bottom, 0.0, 0.0, 1.0);
		tesselator.addVertexWithUV(this.right, this.bottom, 0.0, 1.0, 1.0);
		tesselator.setColorRGBA_I(0, 0);
		tesselator.addVertexWithUV(this.right, this.bottom - var22, 0.0, 1.0, 0.0);
		tesselator.addVertexWithUV(this.left, this.bottom - var22, 0.0, 0.0, 0.0);
		tesselator.draw();
		int integer13 = this.func_148135_f();
		if (integer13 > 0) {
			int integer14 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
			int integer15 = (int) this.amountScrolled * (this.bottom - this.top - (integer14 = MathHelper.clamp_int(
					integer14, 32, this.bottom - this.top - 8))) / integer13 + this.top;
			if (integer15 < this.top) {
				integer15 = this.top;
			}

			tesselator.startDrawingQuads();
			tesselator.setColorRGBA_I(0, 255);
			tesselator.addVertexWithUV(integer5, this.bottom, 0.0, 0.0, 1.0);
			tesselator.addVertexWithUV(integer6, this.bottom, 0.0, 1.0, 1.0);
			tesselator.addVertexWithUV(integer6, this.top, 0.0, 1.0, 0.0);
			tesselator.addVertexWithUV(integer5, this.top, 0.0, 0.0, 0.0);
			tesselator.draw();
			tesselator.startDrawingQuads();
			tesselator.setColorRGBA_I(8421504, 255);
			tesselator.addVertexWithUV(integer5, integer15 + integer14, 0.0, 0.0, 1.0);
			tesselator.addVertexWithUV(integer6, integer15 + integer14, 0.0, 1.0, 1.0);
			tesselator.addVertexWithUV(integer6, integer15, 0.0, 1.0, 0.0);
			tesselator.addVertexWithUV(integer5, integer15, 0.0, 0.0, 0.0);
			tesselator.draw();
			tesselator.startDrawingQuads();
			tesselator.setColorRGBA_I(12632256, 255);
			tesselator.addVertexWithUV(integer5, integer15 + integer14 - 1, 0.0, 0.0, 1.0);
			tesselator.addVertexWithUV(integer6 - 1, integer15 + integer14 - 1, 0.0, 1.0, 1.0);
			tesselator.addVertexWithUV(integer6 - 1, integer15, 0.0, 1.0, 0.0);
			tesselator.addVertexWithUV(integer5, integer15, 0.0, 0.0, 0.0);
			tesselator.draw();
		}
		this.func_148142_b(integer1, integer2);
		GL11.glEnable(3553);
		GL11.glShadeModel(7424);
		GL11.glEnable(3008);
		GL11.glDisable(3042);
	}

	public void handleMouseInput() {
		int integer5;
		int integer4;
		int integer3;
		int integer2;
		if (!this.isMouseYWithinSlotBounds(this.mouseY)) {
			return;
		}
		if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.top && this.mouseY <= this.bottom) {
			integer2 = (this.width - this.getListWidth()) / 2;
			integer3 = (this.width + this.getListWidth()) / 2;
			integer4 = this.mouseY - this.top - this.headerPadding + (int) this.amountScrolled - 4;
			integer5 = integer4 / this.slotHeight;
			if (integer5 < this.getSize() && this.mouseX >= integer2 && this.mouseX <= integer3 && integer5 >= 0 && integer4 >= 0) {
				this.elementClicked(integer5, false, this.mouseX, this.mouseY);
				this.selectedElement = integer5;
			} else if (this.mouseX >= integer2 && this.mouseX <= integer3 && integer4 < 0) {
				this.func_148132_a(this.mouseX - integer2, this.mouseY - this.top + (int) this.amountScrolled - 4);
			}
		}
		if (Mouse.isButtonDown(0) && this.getEnabled()) {
			if (this.initialClickY == -1) {
				boolean boolean2 = true;
				if (this.mouseY >= this.top && this.mouseY <= this.bottom) {
					integer3 = (this.width - this.getListWidth()) / 2;
					integer4 = (this.width + this.getListWidth()) / 2;
					integer5 = this.mouseY - this.top - this.headerPadding + (int) this.amountScrolled - 4;
					int integer6 = integer5 / this.slotHeight;
					if (integer6 < this.getSize() && this.mouseX >= integer3 && this.mouseX <= integer4 && integer6 >= 0 && integer5 >= 0) {
						boolean boolean7 = integer6 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
						this.elementClicked(integer6, boolean7, this.mouseX, this.mouseY);
						this.selectedElement = integer6;
						this.lastClicked = Minecraft.getSystemTime();
					} else if (this.mouseX >= integer3 && this.mouseX <= integer4 && integer5 < 0) {
						this.func_148132_a(this.mouseX - integer3,
								this.mouseY - this.top + (int) this.amountScrolled - 4);
						boolean2 = false;
					}
					int integer7 = this.getScrollBarX();
					int integer8 = integer7 + 6;
					if (this.mouseX >= integer7 && this.mouseX <= integer8) {
						this.scrollMultiplier = -1.0f;
						int integer9 = this.func_148135_f();
						if (integer9 < 1) {
							integer9 = 1;
						}
						int integer10 = (int) ((float) ((this.bottom - this.top) * (this.bottom - this.top)) / (float) this.getContentHeight());
						integer10 = MathHelper.clamp_int(integer10, 32, this.bottom - this.top - 8);
						this.scrollMultiplier /= (float) (this.bottom - this.top - integer10) / (float) integer9;
					} else {
						this.scrollMultiplier = 1.0f;
					}
					this.initialClickY = boolean2 ? this.mouseY : -2;
				} else {
					this.initialClickY = -2;
				}
			} else if (this.initialClickY >= 0) {
				this.amountScrolled -= (float) (this.mouseY - this.initialClickY) * this.scrollMultiplier;
				this.initialClickY = this.mouseY;
			}
		} else {
			this.initialClickY = -1;
		}
		integer2 = Mouse.getEventDWheel();
		if (integer2 != 0) {
			if (integer2 > 0) {
				integer2 = -1;
			} else if (integer2 < 0) {
				integer2 = 1;
			}
			this.amountScrolled += (float) (integer2 * this.slotHeight / 2);
		}
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean boolean1) {
		this.enabled = boolean1;
	}

	/**
	 * Gets the width of the list
	 */
	public int getListWidth() {
		return 220;
	}

	/**
	 * Draws the selection box around the selected slot element.
	 */
	public void drawSelectionBox(int integer1, int integer2, int integer3, int integer4) {
		int integer6 = this.getSize();
		Tessellator tesselator = Tessellator.instance;
		for (int integer9 = 0; integer9 < integer6; ++integer9) {
			int integer10 = integer2 + integer9 * this.slotHeight + this.headerPadding;
			int integer11 = this.slotHeight - 4;
			if (integer10 > this.bottom || integer10 + integer11 < this.top) {
				this.func_178040_a(integer9, integer1, integer10);
			}
			if (this.showSelectionBox && this.isSelected(integer9)) {
				int integer12 = this.left + (this.width / 2 - this.getListWidth() / 2);
				int integer13 = this.left + (this.width / 2 + this.getListWidth() / 2);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(3553);
				tesselator.startDrawingQuads();
				tesselator.setColorOpaque_I(8421504);
				tesselator.addVertexWithUV(integer12, integer10 + integer11 + 2, 0.0, 0.0, 1.0);
				tesselator.addVertexWithUV(integer13, integer10 + integer11 + 2, 0.0, 1.0, 1.0);
				tesselator.addVertexWithUV(integer13, integer10 - 2, 0.0, 1.0, 0.0);
				tesselator.addVertexWithUV(integer12, integer10 - 2, 0.0, 0.0, 0.0);
				tesselator.setColorOpaque_I(0);
				tesselator.addVertexWithUV(integer12 + 1, integer10 + integer11 + 1, 0.0, 0.0, 1.0);
				tesselator.addVertexWithUV(integer13 - 1, integer10 + integer11 + 1, 0.0, 1.0, 1.0);
				tesselator.addVertexWithUV(integer13 - 1, integer10 - 1, 0.0, 1.0, 0.0);
				tesselator.addVertexWithUV(integer12 + 1, integer10 - 1, 0.0, 0.0, 0.0);
				tesselator.draw();
				GL11.glEnable(3553);
			}
			this.drawSlot(integer9, integer1, integer10, integer11, integer3, integer4);
		}
	}

	public int getScrollBarX() {
		return this.width / 2 + 124;
	}

	/**
	 * Overlays the background to hide scrolled items
	 */
	public void overlayBackground(int i, int j, int k, int l) {
		Tessellator var5 = Tessellator.instance;
		this.mc.renderEngine.bindTexture("/gui/background.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var6 = 32.0F;
		var5.startDrawingQuads();
		var5.setColorRGBA_I(4210752, l);
		var5.addVertexWithUV(0.0, j, 0.0, 0.0, (float) j / var6);
		var5.addVertexWithUV(this.width, j, 0.0, (float) this.width / var6, (float) j / var6);
		var5.setColorRGBA_I(4210752, k);
		var5.addVertexWithUV(this.width, i, 0.0, (float) this.width / var6, (float) i / var6);
		var5.addVertexWithUV(0.0, i, 0.0, 0.0, (float) i / var6);
		var5.draw();
	}


	/**
	 * Sets the left and right bounds of the slot. Param is the left bound, right is calculated as left + width.
	 */
	public void setSlotXBoundsFromLeft(int integer) {
		this.left = integer;
		this.right = integer + this.width;
	}

	public int getSlotHeight() {
		return this.slotHeight;
	}
}
