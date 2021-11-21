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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer;

import io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex.BackportedVertexFormatElement;
import java.util.List;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class BackportedWorldVertexBufferUploader {
    public BackportedWorldVertexBufferUploader() {
    }

    public void draw(BackportedWorldRenderer worldRenderer) {
        if (worldRenderer.getVertexCount() > 0) {
            var vertexFormat = worldRenderer.getVertexFormat();
            var nextOffset = vertexFormat.getNextOffset();
            var byteBuffer = worldRenderer.getByteBuffer();
            List<BackportedVertexFormatElement> list = vertexFormat.getElements();

            int i;
            int currentIndex;
            for (i = 0; i < list.size(); ++i) {
                var vertexFormatElement = (BackportedVertexFormatElement) list.get(i);
                var enumUsage = vertexFormatElement.getUsage();
                var k = vertexFormatElement.getType().func_177397_c();
                currentIndex = vertexFormatElement.getIndex();
                byteBuffer.position(vertexFormat.getOffset(i));
                switch (enumUsage) {
                    case POSITION -> {
                        GL11.glVertexPointer(vertexFormatElement.getElementCount(), k, nextOffset, byteBuffer);
                        GL11.glEnableClientState(32884);
                    }
                    case UV -> {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + currentIndex);
                        GL11.glTexCoordPointer(vertexFormatElement.getElementCount(), k, nextOffset, byteBuffer);
                        GL11.glEnableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                    }
                    case COLOR -> {
                        GL11.glColorPointer(vertexFormatElement.getElementCount(), k, nextOffset, byteBuffer);
                        GL11.glEnableClientState(32886);
                    }
                    case NORMAL -> {
                        GL11.glNormalPointer(k, nextOffset, byteBuffer);
                        GL11.glEnableClientState(32885);
                    }
                }
            }

            GL11.glDrawArrays(worldRenderer.getDrawMode(), 0, worldRenderer.getVertexCount());
            i = 0;

            for (var n = list.size(); i < n; ++i) {
                var vertexFormatElement2 = (BackportedVertexFormatElement) list.get(i);
                BackportedVertexFormatElement.EnumUsage enumUsage2 = vertexFormatElement2.getUsage();
                currentIndex = vertexFormatElement2.getIndex();
                switch (enumUsage2) {
                    case POSITION -> GL11.glDisableClientState(32884);
                    case UV -> {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + currentIndex);
                        GL11.glDisableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                    }
                    case COLOR -> {
                        GL11.glDisableClientState(32886);
                        BackportedGlStateManager.resetColor();
                    }
                    case NORMAL -> GL11.glDisableClientState(32885);
                }
            }
        }

        worldRenderer.reset();
    }
}
