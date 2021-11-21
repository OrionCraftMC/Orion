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

import io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.*;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex.BackportedDefaultVertexFormats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;

public abstract class BackportedGuiSlot {
    protected final Minecraft mc;
    protected int width;
    protected int height;
    protected int top;
    protected int bottom;
    protected int right;
    protected int left;
    protected final int slotHeight;
    private int scrollUpButtonID;
    private int scrollDownButtonID;
    protected int mouseX;
    protected int mouseY;
    protected boolean field_148163_i = true;
    protected int initialClickY = -2;
    protected float scrollMultiplier;
    protected float amountScrolled;
    protected int selectedElement = -1;
    protected long lastClicked;
    protected boolean field_178041_q = true;
    protected boolean showSelectionBox = true;
    protected boolean hasListHeader;
    protected int headerPadding;
    private boolean enabled = true;

    public BackportedGuiSlot(Minecraft minecraft, int width, int height, int top, int bottom, int slotHeight) {
        this.mc = minecraft;
        this.width = width;
        this.height = height;
        this.top = top;
        this.bottom = bottom;
        this.slotHeight = slotHeight;
        this.left = 0;
        this.right = width;
    }

    public void setDimensions(int i, int j, int k, int l) {
        this.width = i;
        this.height = j;
        this.top = k;
        this.bottom = l;
        this.left = 0;
        this.right = i;
    }

    public void setShowSelectionBox(boolean bl) {
        this.showSelectionBox = bl;
    }

    protected void setHasListHeader(boolean bl, int i) {
        this.hasListHeader = bl;
        this.headerPadding = i;
        if (!bl) {
            this.headerPadding = 0;
        }

    }

    protected abstract int getSize();

    protected abstract void elementClicked(int i, boolean bl, int j, int k);

    protected abstract boolean isSelected(int i);

    protected int getContentHeight() {
        return this.getSize() * this.slotHeight + this.headerPadding;
    }

    protected abstract void drawBackground();

    protected void drawSelectionBox(int i, int j, int k) {
    }

    protected abstract void drawSlot(int i, int j, int k, int l, int m, int n);

    protected void drawListHeader(int i, int j, BackportedTessellator tessellator) {
    }

    protected void func_148132_a(int i, int j) {
    }

    protected void func_148142_b(int i, int j) {
    }

    public int getSlotIndexFromScreenCoords(int i, int j) {
        int k = this.left + this.width / 2 - this.getListWidth() / 2;
        int l = this.left + this.width / 2 + this.getListWidth() / 2;
        int m = j - this.top - this.headerPadding + (int)this.amountScrolled - 4;
        int n = m / this.slotHeight;
        return i < this.getScrollBarX() && i >= k && i <= l && n >= 0 && m >= 0 && n < this.getSize() ? n : -1;
    }

    public void registerScrollButtons(int i, int j) {
        this.scrollUpButtonID = i;
        this.scrollDownButtonID = j;
    }

    protected void bindAmountScrolled() {
        this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0F, (float)this.func_148135_f());
    }

    public int func_148135_f() {
        return Math.max(0, this.getContentHeight() - (this.bottom - this.top - 4));
    }

    public int getAmountScrolled() {
        return (int)this.amountScrolled;
    }

    public boolean isMouseYWithinSlotBounds(int i) {
        return i >= this.top && i <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.right;
    }

    public void scrollBy(int i) {
        this.amountScrolled += (float)i;
        this.bindAmountScrolled();
        this.initialClickY = -2;
    }

    public void actionPerformed(GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == this.scrollUpButtonID) {
                this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2;
                this.bindAmountScrolled();
            } else if (guiButton.id == this.scrollDownButtonID) {
                this.amountScrolled += (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2;
                this.bindAmountScrolled();
            }

        }
    }

    public void drawScreen(int i, int j, float f) {
        if (this.field_178041_q) {
            this.mouseX = i;
            this.mouseY = j;
            this.drawBackground();
            int k = this.getScrollBarX();
            int l = k + 6;
            this.bindAmountScrolled();
            BackportedGlStateManager.disableLighting();
            BackportedGlStateManager.disableFog();
            BackportedTessellator tessellator = BackportedTessellator.getInstance();
            BackportedWorldRenderer worldRenderer = tessellator.getWorldRenderer();
            this.mc.renderEngine.bindTexture("/gui/background.png");
            BackportedGlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            float g = 32.0F;
            worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos(this.left, this.bottom, 0.0D).tex((float)this.left / g, (float)(this.bottom + (int)this.amountScrolled) / g).color(32, 32, 32, 255).endVertex();
            worldRenderer.pos(this.right, this.bottom, 0.0D).tex((float)this.right / g, (float)(this.bottom + (int)this.amountScrolled) / g).color(32, 32, 32, 255).endVertex();
            worldRenderer.pos(this.right, this.top, 0.0D).tex((float)this.right / g, (float)(this.top + (int)this.amountScrolled) / g).color(32, 32, 32, 255).endVertex();
            worldRenderer.pos(this.left, this.top, 0.0D).tex((float)this.left / g, (float)(this.top + (int)this.amountScrolled) / g).color(32, 32, 32, 255).endVertex();
            tessellator.draw();
            int m = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int n = this.top + 4 - (int)this.amountScrolled;
            if (this.hasListHeader) {
                this.drawListHeader(m, n, tessellator);
            }

            this.drawSelectionBox(m, n, i, j);
            BackportedGlStateManager.disableDepth();
            int o = 4;
            this.overlayBackground(0, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.height, 255, 255);
            BackportedGlStateManager.enableBlend();
            BackportedGlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            BackportedGlStateManager.disableAlpha();
            BackportedGlStateManager.shadeModel(7425);
            BackportedGlStateManager.disableTexture2D();
            worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos(this.left, this.top + o, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            worldRenderer.pos(this.right, this.top + o, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            worldRenderer.pos(this.right, this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(this.left, this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
            worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos(this.left, this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(this.right, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(this.right, this.bottom - o, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            worldRenderer.pos(this.left, this.bottom - o, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            tessellator.draw();
            int p = this.func_148135_f();
            if (p > 0) {
                int q = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                q = MathHelper.clamp_int(q, 32, this.bottom - this.top - 8);
                int r = (int)this.amountScrolled * (this.bottom - this.top - q) / p + this.top;
                if (r < this.top) {
                    r = this.top;
                }

                worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
                worldRenderer.pos(k, this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(l, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(l, this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(k, this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
                worldRenderer.pos(k, r + q, 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(l, r + q, 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(l, r, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(k, r, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                tessellator.draw();
                worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
                worldRenderer.pos(k, r + q - 1, 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 255).endVertex();
                worldRenderer.pos(l - 1, r + q - 1, 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 255).endVertex();
                worldRenderer.pos(l - 1, r, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 255).endVertex();
                worldRenderer.pos(k, r, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 255).endVertex();
                tessellator.draw();
            }

            this.func_148142_b(i, j);
            BackportedGlStateManager.enableTexture2D();
            BackportedGlStateManager.shadeModel(7424);
            BackportedGlStateManager.enableAlpha();
            BackportedGlStateManager.disableBlend();
        }
    }

    public void handleMouseInput() {
        if (this.isMouseYWithinSlotBounds(this.mouseY)) {
            int u;
            int m;
            int n;
            int o;
            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.top && this.mouseY <= this.bottom) {
                u = (this.width - this.getListWidth()) / 2;
                m = (this.width + this.getListWidth()) / 2;
                n = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                o = n / this.slotHeight;
                if (o < this.getSize() && this.mouseX >= u && this.mouseX <= m && o >= 0 && n >= 0) {
                    this.elementClicked(o, false, this.mouseX, this.mouseY);
                    this.selectedElement = o;
                } else if (this.mouseX >= u && this.mouseX <= m && n < 0) {
                    this.func_148132_a(this.mouseX - u, this.mouseY - this.top + (int)this.amountScrolled - 4);
                }
            }

            if (Mouse.isButtonDown(0) && this.getEnabled()) {
                if (this.initialClickY == -1) {
                    boolean bl = true;
                    if (this.mouseY >= this.top && this.mouseY <= this.bottom) {
                        m = (this.width - this.getListWidth()) / 2;
                        n = (this.width + this.getListWidth()) / 2;
                        o = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                        int p = o / this.slotHeight;
                        if (p < this.getSize() && this.mouseX >= m && this.mouseX <= n && p >= 0 && o >= 0) {
                            boolean bl2 = p == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                            this.elementClicked(p, bl2, this.mouseX, this.mouseY);
                            this.selectedElement = p;
                            this.lastClicked = Minecraft.getSystemTime();
                        } else if (this.mouseX >= m && this.mouseX <= n && o < 0) {
                            this.func_148132_a(this.mouseX - m, this.mouseY - this.top + (int)this.amountScrolled - 4);
                            bl = false;
                        }

                        int q = this.getScrollBarX();
                        int r = q + 6;
                        if (this.mouseX >= q && this.mouseX <= r) {
                            this.scrollMultiplier = -1.0F;
                            int s = this.func_148135_f();
                            if (s < 1) {
                                s = 1;
                            }

                            int t = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                            t = MathHelper.clamp_int(t, 32, this.bottom - this.top - 8);
                            this.scrollMultiplier /= (float)(this.bottom - this.top - t) / (float)s;
                        } else {
                            this.scrollMultiplier = 1.0F;
                        }

                        if (bl) {
                            this.initialClickY = this.mouseY;
                        } else {
                            this.initialClickY = -2;
                        }
                    } else {
                        this.initialClickY = -2;
                    }
                } else if (this.initialClickY >= 0) {
                    this.amountScrolled -= (float)(this.mouseY - this.initialClickY) * this.scrollMultiplier;
                    this.initialClickY = this.mouseY;
                }
            } else {
                this.initialClickY = -1;
            }

            u = Mouse.getEventDWheel();
            if (u != 0) {
                if (u > 0) {
                    u = -1;
                } else {
                    u = 1;
                }

                this.amountScrolled += (float)(u * this.slotHeight / 2);
            }

        }
    }

    public void setEnabled(boolean bl) {
        this.enabled = bl;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public int getListWidth() {
        return 220;
    }

    protected void drawSelectionBox(int i, int j, int k, int l) {
        int m = this.getSize();
        BackportedTessellator tessellator = BackportedTessellator.getInstance();
        BackportedWorldRenderer worldRenderer = tessellator.getWorldRenderer();

        for(int n = 0; n < m; ++n) {
            int o = j + n * this.slotHeight + this.headerPadding;
            int p = this.slotHeight - 4;
            if (o > this.bottom || o + p < this.top) {
                this.drawSelectionBox(n, i, o);
            }

            if (this.showSelectionBox && this.isSelected(n)) {
                int q = this.left + (this.width / 2 - this.getListWidth() / 2);
                int r = this.left + this.width / 2 + this.getListWidth() / 2;
                BackportedGlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                BackportedGlStateManager.disableTexture2D();
                worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
                worldRenderer.pos(q, o + p + 2, 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(r, o + p + 2, 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(r, o - 2, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(q, o - 2, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                worldRenderer.pos(q + 1, o + p + 1, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(r - 1, o + p + 1, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(r - 1, o - 1, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldRenderer.pos(q + 1, o - 1, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                BackportedGlStateManager.enableTexture2D();
            }

            this.drawSlot(n, i, o, p, k, l);
        }

    }

    protected int getScrollBarX() {
        return this.width / 2 + 124;
    }

    protected void overlayBackground(int i, int j, int k, int l) {
        BackportedTessellator tessellator = BackportedTessellator.getInstance();
        BackportedWorldRenderer worldRenderer = tessellator.getWorldRenderer();
        this.mc.renderEngine.bindTexture("/gui/background.png");
        BackportedGlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        worldRenderer.begin(7, BackportedDefaultVertexFormats.POSITION_TEX_COLOR);
        worldRenderer.pos(this.left, j, 0.0D).tex(0.0D, (float)j / 32.0F).color(64, 64, 64, l).endVertex();
        worldRenderer.pos(this.left + this.width, j, 0.0D).tex((float)this.width / 32.0F, (float)j / 32.0F).color(64, 64, 64, l).endVertex();
        worldRenderer.pos(this.left + this.width, i, 0.0D).tex((float)this.width / 32.0F, (float)i / 32.0F).color(64, 64, 64, k).endVertex();
        worldRenderer.pos(this.left, i, 0.0D).tex(0.0D, (float)i / 32.0F).color(64, 64, 64, k).endVertex();
        tessellator.draw();
    }

    public void setSlotXBoundsFromLeft(int i) {
        this.left = i;
        this.right = i + this.width;
    }

    public int getSlotHeight() {
        return this.slotHeight;
    }
}
