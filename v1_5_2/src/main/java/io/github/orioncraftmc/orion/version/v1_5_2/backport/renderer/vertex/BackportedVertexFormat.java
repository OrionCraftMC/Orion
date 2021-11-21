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

package io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex;

import com.google.common.collect.Lists;
import java.util.List;

public class BackportedVertexFormat {
    private final List<BackportedVertexFormatElement> elements;
    private final List<Integer> offsets;
    private int nextOffset;
    private int colorElementOffset;
    private final List<Integer> uvOffsetsById;
    private int normalElementOffset;

    public BackportedVertexFormat(BackportedVertexFormat vertexFormat) {
        this();

        for(int i = 0; i < vertexFormat.getElementCount(); ++i) {
            this.addElement(vertexFormat.getElement(i));
        }

        this.nextOffset = vertexFormat.getNextOffset();
    }

    public BackportedVertexFormat() {
        this.elements = Lists.newArrayList();
        this.offsets = Lists.newArrayList();
        this.nextOffset = 0;
        this.colorElementOffset = -1;
        this.uvOffsetsById = Lists.newArrayList();
        this.normalElementOffset = -1;
    }

    public void clear() {
        this.elements.clear();
        this.offsets.clear();
        this.colorElementOffset = -1;
        this.uvOffsetsById.clear();
        this.normalElementOffset = -1;
        this.nextOffset = 0;
    }

    public BackportedVertexFormat addElement(BackportedVertexFormatElement N2OVertexFormatElement) {
        if (N2OVertexFormatElement.isPositionElement() && this.hasPosition()) {
//            LOGGER.warn("VertexFormat error: Trying to add a position N2OVertexFormatElement when one already exists, ignoring.");
            return this;
        } else {
            this.elements.add(N2OVertexFormatElement);
            this.offsets.add(this.nextOffset);
            switch (N2OVertexFormatElement.getUsage()) {
                case NORMAL -> this.normalElementOffset = this.nextOffset;
                case COLOR -> this.colorElementOffset = this.nextOffset;
                case UV -> this.uvOffsetsById.add(N2OVertexFormatElement.getIndex(), this.nextOffset);
            }

            this.nextOffset += N2OVertexFormatElement.getSize();
            return this;
        }
    }

    public boolean hasNormal() {
        return this.normalElementOffset >= 0;
    }

    public int getNormalOffset() {
        return this.normalElementOffset;
    }

    public boolean hasColor() {
        return this.colorElementOffset >= 0;
    }

    public int getColorOffset() {
        return this.colorElementOffset;
    }

    public boolean hasUvOffset(int i) {
        return this.uvOffsetsById.size() - 1 >= i;
    }

    public int getUvOffsetById(int i) {
        return this.uvOffsetsById.get(i);
    }

    public String toString() {
        StringBuilder string = new StringBuilder("format: " + this.elements.size() + " elements: ");

        for(int i = 0; i < this.elements.size(); ++i) {
            string.append(this.elements.get(i).toString());
            if (i != this.elements.size() - 1) {
                string.append(" ");
            }
        }

        return string.toString();
    }

    private boolean hasPosition() {
        int i = 0;

        for(int j = this.elements.size(); i < j; ++i) {
            BackportedVertexFormatElement N2OVertexFormatElement = this.elements.get(i);
            if (N2OVertexFormatElement.isPositionElement()) {
                return true;
            }
        }

        return false;
    }

    public int getIntegerSize() {
        return this.getNextOffset() / 4;
    }

    public int getNextOffset() {
        return this.nextOffset;
    }

    public List<BackportedVertexFormatElement> getElements() {
        return this.elements;
    }

    public int getElementCount() {
        return this.elements.size();
    }

    public BackportedVertexFormatElement getElement(int i) {
        return this.elements.get(i);
    }

    public int getOffset(int i) {
        return this.offsets.get(i);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            BackportedVertexFormat vertexFormat = (BackportedVertexFormat)object;
            if (this.nextOffset != vertexFormat.nextOffset) {
                return false;
            } else if (!this.elements.equals(vertexFormat.elements)) {
                return false;
            } else {
                return this.offsets.equals(vertexFormat.offsets);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i = this.elements.hashCode();
        i = 31 * i + this.offsets.hashCode();
        i = 31 * i + this.nextOffset;
        return i;
    }
}
