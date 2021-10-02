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

package io.github.orioncraftmc.orion.version.v1_8_9.bridge.rendering;

import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;

public class OpenGlBridgeImpl implements OpenGlBridge {
	@Override
	public void disableBlend() {
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void disableTexture2D() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void enableBlend() {
		GL11.glEnable(GL11.GL_BLEND);
	}

	@Override
	public void enableBlendAlphaMinusSrcAlpha() {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void enableTexture2D() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void popMatrix() {
		GL11.glPopMatrix();
	}

	@Override
	public void pushMatrix() {
		GL11.glPushMatrix();
	}

	@Override
	public void translate(double x, double y, double z) {
		GL11.glTranslated(x, y, z);
	}

	@Override
	public void scale(double x, double y, double z) {
		GL11.glScaled(x, y, z);
	}

	@Override
	public void setLineWidth(float v) {
		GL11.glLineWidth(v);
	}

	@Override
	public void setColor(int i, int i1, int i2, int i3) {
		GL11.glColor4b((byte) i, (byte) i1, (byte) i2, (byte) i3);
	}

	@Override
	public void disableRescaleNormal() {
		GL11.glDisable(0x803A);
	}

	@Override
	public void disableStandardItemLighting() {
		RenderHelper.disableStandardItemLighting();
	}

	@Override
	public void enableGUIStandardItemLighting() {
		RenderHelper.enableGUIStandardItemLighting();
	}

	@Override
	public void enableRescaleNormal() {
		GL11.glEnable(0x803A);
	}

	@Override
	public void enableStandardItemLighting() {
		RenderHelper.enableStandardItemLighting();
	}
}
