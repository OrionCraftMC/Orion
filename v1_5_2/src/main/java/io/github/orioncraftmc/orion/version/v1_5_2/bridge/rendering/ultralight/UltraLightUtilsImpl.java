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

package io.github.orioncraftmc.orion.version.v1_5_2.bridge.rendering.ultralight;

import com.labymedia.ultralight.bitmap.UltralightBitmap;
import com.labymedia.ultralight.bitmap.UltralightBitmapSurface;
import com.labymedia.ultralight.math.IntRect;
import io.github.orioncraftmc.orion.api.bridge.rendering.ultralight.UltralightUtils;
import io.github.orioncraftmc.orion.ui.ultralight.OrionUltralightManager;
import java.nio.ByteBuffer;
import org.jetbrains.annotations.NotNull;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_8_8_8_8_REV;

public class UltraLightUtilsImpl implements UltralightUtils {
	private long lastJsGc = 0;
	private int glTexture = -1;
	@Override
	public long getLastJavascriptGarbageCollections() {
		return lastJsGc;
	}

	@Override
	public void setLastJavascriptGarbageCollections(long l) {
		lastJsGc = l;
	}

	@Override
	public void render(@NotNull OrionUltralightManager orionUltralightManager) {
		if(glTexture == -1) {
			createGLTexture();
		}

		// As we are using the CPU renderer, draw with a bitmap (we did not set a custom surface)
		UltralightBitmapSurface surface = (UltralightBitmapSurface) orionUltralightManager.getView().surface();
		UltralightBitmap bitmap = surface.bitmap();

		int width = (int) orionUltralightManager.getView().width();
		int height = (int) orionUltralightManager.getView().height();

		// Prepare OpenGL for 2D textures and bind our texture
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, glTexture);

		IntRect dirtyBounds = surface.dirtyBounds();

		if(dirtyBounds.isValid()) {
			ByteBuffer imageData = bitmap.lockPixels();

			glPixelStorei(GL_UNPACK_ROW_LENGTH, (int) bitmap.rowBytes() / 4);
			if(dirtyBounds.width() == width && dirtyBounds.height() == height) {
				// Update full image
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, imageData);
				glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);
			} else {
				// Update partial image
				int x = dirtyBounds.x();
				int y = dirtyBounds.y();
				int dirtyWidth = dirtyBounds.width();
				int dirtyHeight = dirtyBounds.height();
				int startOffset = (int) ((y * bitmap.rowBytes()) + x * 4);

				glTexSubImage2D(
						GL_TEXTURE_2D,
						0,
						x, y, dirtyWidth, dirtyHeight,
						GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
						(ByteBuffer) imageData.position(startOffset));
			}
			glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);

			bitmap.unlockPixels();
			surface.clearDirtyBounds();
		}

		// Set up the OpenGL state for rendering of a fullscreen quad
		glPushAttrib(GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT | GL_TRANSFORM_BIT);
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0, orionUltralightManager.getView().width(), orionUltralightManager.getView().height(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();

		// Disable lighting and scissoring, they could mess up th renderer
		glLoadIdentity();
		glDisable(GL_LIGHTING);
		glDisable(GL_SCISSOR_TEST);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// Make sure we draw with a neutral color
		// (so we don't mess with the color channels of the image)
		glColor4f(1, 1, 1, 1f);

		glBegin(GL_QUADS);

		// Lower left corner, 0/0 on the screen space, and 0/0 of the image UV
		glTexCoord2d(0, 0);
		glVertex2f(0, 0);

		// Upper left corner
		glTexCoord2f(0, 1);
		glVertex2i(0, height);

		// Upper right corner
		glTexCoord2f(1, 1);
		glVertex2i(width, height);

		// Lower right corner
		glTexCoord2f(1, 0);
		glVertex2i(width, 0);

		glEnd();

		glBindTexture(GL_TEXTURE_2D, 0);

		// Restore OpenGL state
		glPopMatrix();
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		glMatrixMode(GL_MODELVIEW);

		glDisable(GL_TEXTURE_2D);
		glPopAttrib();
	}


	/**
	 * Sets up the OpenGL texture for rendering
	 */
	private void createGLTexture() {
		glEnable(GL_TEXTURE_2D);
		glTexture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, glTexture);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glBindTexture(GL_TEXTURE_2D, 0);
		glDisable(GL_TEXTURE_2D);
	}
}
