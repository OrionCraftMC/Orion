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


public class BackportedVertexFormatElement {
    private final EnumType type;
    private final EnumUsage usage;
    private final int index;
    private final int elementCount;

    public BackportedVertexFormatElement(int i, EnumType enumType, EnumUsage enumUsage, int j) {
        if (!this.isUv(i, enumUsage)) {
            this.usage = EnumUsage.UV;
        } else {
            this.usage = enumUsage;
        }

        this.type = enumType;
        this.index = i;
        this.elementCount = j;
    }

    private boolean isUv(int i, EnumUsage enumUsage) {
        return i == 0 || enumUsage == EnumUsage.UV;
    }

    public final EnumType getType() {
        return this.type;
    }

    public final EnumUsage getUsage() {
        return this.usage;
    }

    public final int getElementCount() {
        return this.elementCount;
    }

    public final int getIndex() {
        return this.index;
    }

    public String toString() {
        return this.elementCount + "," + this.usage.func_177384_a() + "," + this.type.func_177396_b();
    }

    public final int getSize() {
        return this.type.func_177395_a() * this.elementCount;
    }

    public final boolean isPositionElement() {
        return this.usage == EnumUsage.POSITION;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            BackportedVertexFormatElement vertexFormatElement = (BackportedVertexFormatElement)object;
            if (this.elementCount != vertexFormatElement.elementCount) {
                return false;
            } else if (this.index != vertexFormatElement.index) {
                return false;
            } else if (this.type != vertexFormatElement.type) {
                return false;
            } else {
                return this.usage == vertexFormatElement.usage;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i = this.type.hashCode();
        i = 31 * i + this.usage.hashCode();
        i = 31 * i + this.index;
        i = 31 * i + this.elementCount;
        return i;
    }

    public enum EnumType {
        FLOAT(4, "Float", 5126),
        UBYTE(1, "Unsigned Byte", 5121),
        BYTE(1, "Byte", 5120),
        USHORT(2, "Unsigned Short", 5123),
        SHORT(2, "Short", 5122),
        UINT(4, "Unsigned Int", 5125),
        INT(4, "Int", 5124);

        private final int field_177407_h;
        private final String field_177408_i;
        private final int field_177405_j;

        EnumType(int j, String string2, int k) {
            this.field_177407_h = j;
            this.field_177408_i = string2;
            this.field_177405_j = k;
        }

        public int func_177395_a() {
            return this.field_177407_h;
        }

        public String func_177396_b() {
            return this.field_177408_i;
        }

        public int func_177397_c() {
            return this.field_177405_j;
        }
    }

    public enum EnumUsage {
        POSITION("Position"),
        NORMAL("Normal"),
        COLOR("Vertex Color"),
        UV("UV"),
        MATRIX("Bone Matrix"),
        BLEND_WEIGHT("Blend Weight"),
        PADDING("Padding");

        private final String field_177392_h;

        EnumUsage(String string2) {
            this.field_177392_h = string2;
        }

        public String func_177384_a() {
            return this.field_177392_h;
        }
    }
}
