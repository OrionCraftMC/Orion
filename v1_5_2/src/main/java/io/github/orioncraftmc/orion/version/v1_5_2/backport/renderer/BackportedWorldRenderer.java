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

import com.google.common.primitives.Floats;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex.BackportedVertexFormat;
import io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex.BackportedVertexFormatElement;
import java.nio.*;
import java.util.Arrays;
import java.util.BitSet;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.MathHelper;

public class BackportedWorldRenderer {
    private ByteBuffer byteBuffer;
    private IntBuffer rawIntBuffer;
    private ShortBuffer rawShortBuffer;
    private FloatBuffer rawFloatBuffer;
    private int vertexCount;
    private BackportedVertexFormatElement vertexFormatElement;
    private int vertexFormatIndex;
    private boolean noColor;
    private int drawMode;
    private double xOffset;
    private double yOffset;
    private double zOffset;
    private BackportedVertexFormat vertexFormat;
    private boolean isDrawing;

    public BackportedWorldRenderer(int i) {
        this.byteBuffer = GLAllocation.createDirectByteBuffer(i * 4);
        this.rawIntBuffer = this.byteBuffer.asIntBuffer();
        this.rawShortBuffer = this.byteBuffer.asShortBuffer();
        this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
    }

    private void growBuffer(int i) {
        if (i > this.rawIntBuffer.remaining()) {
            int j = this.byteBuffer.capacity();
            int k = j % 2097152;
            int l = k + (((this.rawIntBuffer.position() + i) * 4 - k) / 2097152 + 1) * 2097152;
//            LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + j + " bytes, new size " + l + " bytes.");
            int m = this.rawIntBuffer.position();
            ByteBuffer byteBuffer = GLAllocation.createDirectByteBuffer(l);
            this.byteBuffer.position(0);
            byteBuffer.put(this.byteBuffer);
            byteBuffer.rewind();
            this.byteBuffer = byteBuffer;
            this.rawFloatBuffer = this.byteBuffer.asFloatBuffer().asReadOnlyBuffer();
            this.rawIntBuffer = this.byteBuffer.asIntBuffer();
            this.rawIntBuffer.position(m);
            this.rawShortBuffer = this.byteBuffer.asShortBuffer();
            this.rawShortBuffer.position(m << 1);
        }
    }

    public void sortVertexData(float f, float g, float h) {
        int i = this.vertexCount / 4;
        final float[] fs = new float[i];

        for(int j = 0; j < i; ++j) {
            fs[j] = getDistanceSq(this.rawFloatBuffer, (float)((double)f + this.xOffset), (float)((double)g + this.yOffset), (float)((double)h + this.zOffset), this.vertexFormat.getIntegerSize(), j * this.vertexFormat.getNextOffset());
        }

        Integer[] integers = new Integer[i];

        for(int k = 0; k < integers.length; ++k) {
            integers[k] = k;
        }

        Arrays.sort(integers, (integer, integer2) -> Floats.compare(fs[integer2], fs[integer]));
        BitSet bitSet = new BitSet();
        int l = this.vertexFormat.getNextOffset();
        int[] is = new int[l];

        for(int m = 0; (m = bitSet.nextClearBit(m)) < integers.length; ++m) {
            int n = integers[m];
            if (n != m) {
                this.rawIntBuffer.limit(n * l + l);
                this.rawIntBuffer.position(n * l);
                this.rawIntBuffer.get(is);
                int o = n;

                for(int p = integers[n]; o != m; p = integers[p]) {
                    this.rawIntBuffer.limit(p * l + l);
                    this.rawIntBuffer.position(p * l);
                    IntBuffer intBuffer = this.rawIntBuffer.slice();
                    this.rawIntBuffer.limit(o * l + l);
                    this.rawIntBuffer.position(o * l);
                    this.rawIntBuffer.put(intBuffer);
                    bitSet.set(o);
                    o = p;
                }

                this.rawIntBuffer.limit(m * l + l);
                this.rawIntBuffer.position(m * l);
                this.rawIntBuffer.put(is);
            }

            bitSet.set(m);
        }

    }

    public State getVertexState() {
        this.rawIntBuffer.rewind();
        int i = this.getBufferSize();
        this.rawIntBuffer.limit(i);
        int[] is = new int[i];
        this.rawIntBuffer.get(is);
        this.rawIntBuffer.limit(this.rawIntBuffer.capacity());
        this.rawIntBuffer.position(i);
        return new State(is, new BackportedVertexFormat(this.vertexFormat));
    }

    private int getBufferSize() {
        return this.vertexCount * this.vertexFormat.getIntegerSize();
    }

    private static float getDistanceSq(FloatBuffer floatBuffer, float f, float g, float h, int i, int j) {
        float k = floatBuffer.get(j);
        float l = floatBuffer.get(j + 1);
        float m = floatBuffer.get(j + 2);
        float n = floatBuffer.get(j + i);
        float o = floatBuffer.get(j + i + 1);
        float p = floatBuffer.get(j + i + 2);
        float q = floatBuffer.get(j + i * 2);
        float r = floatBuffer.get(j + i * 2 + 1);
        float s = floatBuffer.get(j + i * 2 + 2);
        float t = floatBuffer.get(j + i * 3);
        float u = floatBuffer.get(j + i * 3 + 1);
        float v = floatBuffer.get(j + i * 3 + 2);
        float w = (k + n + q + t) * 0.25F - f;
        float x = (l + o + r + u) * 0.25F - g;
        float y = (m + p + s + v) * 0.25F - h;
        return w * w + x * x + y * y;
    }

    public void setVertexState(State state) {
        this.rawIntBuffer.clear();
        this.growBuffer(state.func_179013_a().length);
        this.rawIntBuffer.put(state.func_179013_a());
        this.vertexCount = state.func_179014_c();
        this.vertexFormat = new BackportedVertexFormat(state.func_179016_d());
    }

    public void reset() {
        this.vertexCount = 0;
        this.vertexFormatElement = null;
        this.vertexFormatIndex = 0;
    }

    public void begin(int i, BackportedVertexFormat vertexFormat) {
        if (this.isDrawing) {
            throw new IllegalStateException("Already building!");
        } else {
            this.isDrawing = true;
            this.reset();
            this.drawMode = i;
            this.vertexFormat = vertexFormat;
            this.vertexFormatElement = vertexFormat.getElement(this.vertexFormatIndex);
            this.noColor = false;
            this.byteBuffer.limit(this.byteBuffer.capacity());
        }
    }

    public BackportedWorldRenderer tex(double d, double e) {
        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
        switch (this.vertexFormatElement.getType()) {
            case FLOAT -> {
                this.byteBuffer.putFloat(i, (float) d);
                this.byteBuffer.putFloat(i + 4, (float) e);
            }
            case UINT, INT -> {
                this.byteBuffer.putInt(i, (int) d);
                this.byteBuffer.putInt(i + 4, (int) e);
            }
            case USHORT, SHORT -> {
                this.byteBuffer.putShort(i, (short) ((int) e));
                this.byteBuffer.putShort(i + 2, (short) ((int) d));
            }
            case UBYTE, BYTE -> {
                this.byteBuffer.put(i, (byte) ((int) e));
                this.byteBuffer.put(i + 1, (byte) ((int) d));
            }
        }

        this.nextVertexFormatIndex();
        return this;
    }

    public BackportedWorldRenderer lightmap(int i, int j) {
        int k = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
        switch (this.vertexFormatElement.getType()) {
            case FLOAT -> {
                this.byteBuffer.putFloat(k, (float) i);
                this.byteBuffer.putFloat(k + 4, (float) j);
            }
            case UINT, INT -> {
                this.byteBuffer.putInt(k, i);
                this.byteBuffer.putInt(k + 4, j);
            }
            case USHORT, SHORT -> {
                this.byteBuffer.putShort(k, (short) j);
                this.byteBuffer.putShort(k + 2, (short) i);
            }
            case UBYTE, BYTE -> {
                this.byteBuffer.put(k, (byte) j);
                this.byteBuffer.put(k + 1, (byte) i);
            }
        }

        this.nextVertexFormatIndex();
        return this;
    }

    public void putBrightness4(int i, int j, int k, int l) {
        int m = (this.vertexCount - 4) * this.vertexFormat.getIntegerSize() + this.vertexFormat.getUvOffsetById(1) / 4;
        int n = this.vertexFormat.getNextOffset() >> 2;
        this.rawIntBuffer.put(m, i);
        this.rawIntBuffer.put(m + n, j);
        this.rawIntBuffer.put(m + n * 2, k);
        this.rawIntBuffer.put(m + n * 3, l);
    }

    public void putPosition(double d, double e, double f) {
        int i = this.vertexFormat.getIntegerSize();
        int j = (this.vertexCount - 4) * i;

        for(int k = 0; k < 4; ++k) {
            int l = j + k * i;
            int m = l + 1;
            int n = m + 1;
            this.rawIntBuffer.put(l, Float.floatToRawIntBits((float)(d + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(l))));
            this.rawIntBuffer.put(m, Float.floatToRawIntBits((float)(e + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(m))));
            this.rawIntBuffer.put(n, Float.floatToRawIntBits((float)(f + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(n))));
        }

    }

    private int getColorIndex(int i) {
        return ((this.vertexCount - i) * this.vertexFormat.getNextOffset() + this.vertexFormat.getColorOffset()) / 4;
    }

    public void putColorMultiplier(float f, float g, float h, int i) {
        int j = this.getColorIndex(i);
        int k = -1;
        if (!this.noColor) {
            k = this.rawIntBuffer.get(j);
            int l;
            int m;
            int n;
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                l = (int)((float)(k & 255) * f);
                m = (int)((float)(k >> 8 & 255) * g);
                n = (int)((float)(k >> 16 & 255) * h);
                k &= -16777216;
                k |= n << 16 | m << 8 | l;
            } else {
                l = (int)((float)(k >> 24 & 255) * f);
                m = (int)((float)(k >> 16 & 255) * g);
                n = (int)((float)(k >> 8 & 255) * h);
                k &= 255;
                k |= l << 24 | m << 16 | n << 8;
            }
        }

        this.rawIntBuffer.put(j, k);
    }

    private void putColor(int i, int j) {
        int k = this.getColorIndex(j);
        int l = i >> 16 & 255;
        int m = i >> 8 & 255;
        int n = i & 255;
        int o = i >> 24 & 255;
        this.putColorRGBA(k, l, m, n, o);
    }

    public void putColorRGB_F(float f, float g, float h, int i) {
        int j = this.getColorIndex(i);
        int k = MathHelper.clamp_int((int)(f * 255.0F), 0, 255);
        int l = MathHelper.clamp_int((int)(g * 255.0F), 0, 255);
        int m = MathHelper.clamp_int((int)(h * 255.0F), 0, 255);
        this.putColorRGBA(j, k, l, m, 255);
    }

    private void putColorRGBA(int i, int j, int k, int l, int m) {
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            this.rawIntBuffer.put(i, m << 24 | l << 16 | k << 8 | j);
        } else {
            this.rawIntBuffer.put(i, j << 24 | k << 16 | l << 8 | m);
        }

    }

    public void noColor() {
        this.noColor = true;
    }

    public BackportedWorldRenderer color(float f, float g, float h, float i) {
        return this.color((int)(f * 255.0F), (int)(g * 255.0F), (int)(h * 255.0F), (int)(i * 255.0F));
    }

    public BackportedWorldRenderer color(int i, int j, int k, int l) {
        if (this.noColor) {
            return this;
        } else {
            int m = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
            switch(this.vertexFormatElement.getType()) {
            case FLOAT:
                this.byteBuffer.putFloat(m, (float)i / 255.0F);
                this.byteBuffer.putFloat(m + 4, (float)j / 255.0F);
                this.byteBuffer.putFloat(m + 8, (float)k / 255.0F);
                this.byteBuffer.putFloat(m + 12, (float)l / 255.0F);
                break;
            case UINT:
            case INT:
                this.byteBuffer.putFloat(m, (float)i);
                this.byteBuffer.putFloat(m + 4, (float)j);
                this.byteBuffer.putFloat(m + 8, (float)k);
                this.byteBuffer.putFloat(m + 12, (float)l);
                break;
            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(m, (short)i);
                this.byteBuffer.putShort(m + 2, (short)j);
                this.byteBuffer.putShort(m + 4, (short)k);
                this.byteBuffer.putShort(m + 6, (short)l);
                break;
            case UBYTE:
            case BYTE:
                if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                    this.byteBuffer.put(m, (byte)i);
                    this.byteBuffer.put(m + 1, (byte)j);
                    this.byteBuffer.put(m + 2, (byte)k);
                    this.byteBuffer.put(m + 3, (byte)l);
                } else {
                    this.byteBuffer.put(m, (byte)l);
                    this.byteBuffer.put(m + 1, (byte)k);
                    this.byteBuffer.put(m + 2, (byte)j);
                    this.byteBuffer.put(m + 3, (byte)i);
                }
            }

            this.nextVertexFormatIndex();
            return this;
        }
    }

    public void addVertexData(int[] is) {
        this.growBuffer(is.length);
        this.rawIntBuffer.position(this.getBufferSize());
        this.rawIntBuffer.put(is);
        this.vertexCount += is.length / this.vertexFormat.getIntegerSize();
    }

    public void endVertex() {
        ++this.vertexCount;
        this.growBuffer(this.vertexFormat.getIntegerSize());
    }

    public BackportedWorldRenderer pos(double d, double e, double f) {
        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
        switch (this.vertexFormatElement.getType()) {
            case FLOAT -> {
                this.byteBuffer.putFloat(i, (float) (d + this.xOffset));
                this.byteBuffer.putFloat(i + 4, (float) (e + this.yOffset));
                this.byteBuffer.putFloat(i + 8, (float) (f + this.zOffset));
            }
            case UINT, INT -> {
                this.byteBuffer.putInt(i, Float.floatToRawIntBits((float) (d + this.xOffset)));
                this.byteBuffer.putInt(i + 4, Float.floatToRawIntBits((float) (e + this.yOffset)));
                this.byteBuffer.putInt(i + 8, Float.floatToRawIntBits((float) (f + this.zOffset)));
            }
            case USHORT, SHORT -> {
                this.byteBuffer.putShort(i, (short) ((int) (d + this.xOffset)));
                this.byteBuffer.putShort(i + 2, (short) ((int) (e + this.yOffset)));
                this.byteBuffer.putShort(i + 4, (short) ((int) (f + this.zOffset)));
            }
            case UBYTE, BYTE -> {
                this.byteBuffer.put(i, (byte) ((int) (d + this.xOffset)));
                this.byteBuffer.put(i + 1, (byte) ((int) (e + this.yOffset)));
                this.byteBuffer.put(i + 2, (byte) ((int) (f + this.zOffset)));
            }
        }

        this.nextVertexFormatIndex();
        return this;
    }

    public void putNormal(float f, float g, float h) {
        int i = (byte)((int)(f * 127.0F)) & 255;
        int j = (byte)((int)(g * 127.0F)) & 255;
        int k = (byte)((int)(h * 127.0F)) & 255;
        int l = i | j << 8 | k << 16;
        int m = this.vertexFormat.getNextOffset() >> 2;
        int n = (this.vertexCount - 4) * m + this.vertexFormat.getNormalOffset() / 4;
        this.rawIntBuffer.put(n, l);
        this.rawIntBuffer.put(n + m, l);
        this.rawIntBuffer.put(n + m * 2, l);
        this.rawIntBuffer.put(n + m * 3, l);
    }

    private void nextVertexFormatIndex() {
        ++this.vertexFormatIndex;
        this.vertexFormatIndex %= this.vertexFormat.getElementCount();
        this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);
        if (this.vertexFormatElement.getUsage() == BackportedVertexFormatElement.EnumUsage.PADDING) {
            this.nextVertexFormatIndex();
        }

    }

    public BackportedWorldRenderer normal(float f, float g, float h) {
        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
        switch (this.vertexFormatElement.getType()) {
            case FLOAT -> {
                this.byteBuffer.putFloat(i, f);
                this.byteBuffer.putFloat(i + 4, g);
                this.byteBuffer.putFloat(i + 8, h);
            }
            case UINT, INT -> {
                this.byteBuffer.putInt(i, (int) f);
                this.byteBuffer.putInt(i + 4, (int) g);
                this.byteBuffer.putInt(i + 8, (int) h);
            }
            case USHORT, SHORT -> {
                this.byteBuffer.putShort(i, (short) ((int) f * 32767 & '\uffff'));
                this.byteBuffer.putShort(i + 2, (short) ((int) g * 32767 & '\uffff'));
                this.byteBuffer.putShort(i + 4, (short) ((int) h * 32767 & '\uffff'));
            }
            case UBYTE, BYTE -> {
                this.byteBuffer.put(i, (byte) ((int) f * 127 & 255));
                this.byteBuffer.put(i + 1, (byte) ((int) g * 127 & 255));
                this.byteBuffer.put(i + 2, (byte) ((int) h * 127 & 255));
            }
        }

        this.nextVertexFormatIndex();
        return this;
    }

    public void setTranslation(double d, double e, double f) {
        this.xOffset = d;
        this.yOffset = e;
        this.zOffset = f;
    }

    public void finishDrawing() {
        if (!this.isDrawing) {
            throw new IllegalStateException("Not building!");
        } else {
            this.isDrawing = false;
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.getBufferSize() * 4);
        }
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    public BackportedVertexFormat getVertexFormat() {
        return this.vertexFormat;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

    public int getDrawMode() {
        return this.drawMode;
    }

    public void putColor4(int i) {
        for(int j = 0; j < 4; ++j) {
            this.putColor(i, j + 1);
        }

    }

    public void putColorRGB_F4(float f, float g, float h) {
        for(int i = 0; i < 4; ++i) {
            this.putColorRGB_F(f, g, h, i + 1);
        }

    }

    public class State {
        private final int[] field_179019_b;
        private final BackportedVertexFormat field_179018_e;

        public State(int[] is, BackportedVertexFormat vertexFormat) {
            this.field_179019_b = is;
            this.field_179018_e = vertexFormat;
        }

        public int[] func_179013_a() {
            return this.field_179019_b;
        }

        public int func_179014_c() {
            return this.field_179019_b.length / this.field_179018_e.getIntegerSize();
        }

        public BackportedVertexFormat func_179016_d() {
            return this.field_179018_e;
        }
    }
}
