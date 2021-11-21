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

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.EXTBlendFuncSeparate;
import org.lwjgl.opengl.GL11;

public class BackportedGlStateManager {
    private static final AlphaState alphaState = new AlphaState();
    private static final BooleanState lightingState = new BooleanState(2896);
    private static final BooleanState[] lightState = new BooleanState[8];
    private static final ColorMaterialState colorMaterialState = new ColorMaterialState();
    private static final BlendState blendState = new BlendState();
    private static final DepthState depthState = new DepthState();
    private static final FogState fogState = new FogState();
    private static final CullState cullState = new CullState();
    private static final PolygonOffsetState polygonOffsetState = new PolygonOffsetState();
    private static final ColorLogicState colorLogicState = new ColorLogicState();
    private static final TexGenState texGenState = new TexGenState();
    private static final ClearState clearState = new ClearState();
    private static final StencilState stencilState = new StencilState();
    private static final BooleanState normalizeState = new BooleanState(2977);
    private static int activeTextureUnit = 0;
    private static final TextureState[] textureState = new TextureState[8];
    private static int activeShadeModel = 7425;
    private static final BooleanState rescaleNormalState = new BooleanState(32826);
    private static final ColorMask colorMaskState = new ColorMask();
    private static final Color colorState = new Color();

    public static void pushAttrib() {
        GL11.glPushAttrib(8256);
    }

    public static void popAttrib() {
        GL11.glPopAttrib();
    }

    public static void disableAlpha() {
        alphaState.field_179208_a.func_179198_a();
    }

    public static void enableAlpha() {
        alphaState.field_179208_a.func_179200_b();
    }

    public static void alphaFunc(int i, float f) {
        if (i != alphaState.field_179206_b || f != alphaState.field_179207_c) {
            alphaState.field_179206_b = i;
            alphaState.field_179207_c = f;
            GL11.glAlphaFunc(i, f);
        }

    }

    public static void enableLighting() {
        lightingState.func_179200_b();
    }

    public static void disableLighting() {
        lightingState.func_179198_a();
    }

    public static void enableLight(int i) {
        lightState[i].func_179200_b();
    }

    public static void disableLight(int i) {
        lightState[i].func_179198_a();
    }

    public static void enableColorMaterial() {
        colorMaterialState.field_179191_a.func_179200_b();
    }

    public static void disableColorMaterial() {
        colorMaterialState.field_179191_a.func_179198_a();
    }

    public static void colorMaterial(int i, int j) {
        if (i != colorMaterialState.field_179189_b || j != colorMaterialState.field_179190_c) {
            colorMaterialState.field_179189_b = i;
            colorMaterialState.field_179190_c = j;
            GL11.glColorMaterial(i, j);
        }

    }

    public static void disableDepth() {
        depthState.field_179052_a.func_179198_a();
    }

    public static void enableDepth() {
        depthState.field_179052_a.func_179200_b();
    }

    public static void depthFunc(int i) {
        if (i != depthState.field_179051_c) {
            depthState.field_179051_c = i;
            GL11.glDepthFunc(i);
        }

    }

    public static void depthMask(boolean bl) {
        if (bl != depthState.field_179050_b) {
            depthState.field_179050_b = bl;
            GL11.glDepthMask(bl);
        }

    }

    public static void disableBlend() {
        blendState.field_179213_a.func_179198_a();
    }

    public static void enableBlend() {
        blendState.field_179213_a.func_179200_b();
    }

    public static void blendFunc(int i, int j) {
        if (i != blendState.field_179211_b || j != blendState.field_179212_c) {
            blendState.field_179211_b = i;
            blendState.field_179212_c = j;
            GL11.glBlendFunc(i, j);
        }

    }

    public static void tryBlendFuncSeparate(int i, int j, int k, int l) {
        if (i != blendState.field_179211_b || j != blendState.field_179212_c || k != blendState.field_179209_d || l != blendState.field_179210_e) {
            blendState.field_179211_b = i;
            blendState.field_179212_c = j;
            blendState.field_179209_d = k;
            blendState.field_179210_e = l;
            glBlendFunc(i, j, k, l);
        }

    }

    public static void glBlendFunc(int i, int j, int k, int l) {
        EXTBlendFuncSeparate.glBlendFuncSeparateEXT(i, j, k, l);

    }

    public static void enableFog() {
        fogState.field_179049_a.func_179200_b();
    }

    public static void disableFog() {
        fogState.field_179049_a.func_179198_a();
    }

    public static void setFog(int i) {
        if (i != fogState.field_179047_b) {
            fogState.field_179047_b = i;
            GL11.glFogi(2917, i);
        }

    }

    public static void setFogDensity(float f) {
        if (f != fogState.field_179048_c) {
            fogState.field_179048_c = f;
            GL11.glFogf(2914, f);
        }

    }

    public static void setFogStart(float f) {
        if (f != fogState.field_179045_d) {
            fogState.field_179045_d = f;
            GL11.glFogf(2915, f);
        }

    }

    public static void setFogEnd(float f) {
        if (f != fogState.field_179046_e) {
            fogState.field_179046_e = f;
            GL11.glFogf(2916, f);
        }

    }

    public static void enableCull() {
        cullState.field_179054_a.func_179200_b();
    }

    public static void disableCull() {
        cullState.field_179054_a.func_179198_a();
    }

    public static void cullFace(int i) {
        if (i != cullState.field_179053_b) {
            cullState.field_179053_b = i;
            GL11.glCullFace(i);
        }

    }

    public static void enablePolygonOffset() {
        polygonOffsetState.field_179044_a.func_179200_b();
    }

    public static void disablePolygonOffset() {
        polygonOffsetState.field_179044_a.func_179198_a();
    }

    public static void doPolygonOffset(float f, float g) {
        if (f != polygonOffsetState.field_179043_c || g != polygonOffsetState.field_179041_d) {
            polygonOffsetState.field_179043_c = f;
            polygonOffsetState.field_179041_d = g;
            GL11.glPolygonOffset(f, g);
        }

    }

    public static void enableColorLogic() {
        colorLogicState.field_179197_a.func_179200_b();
    }

    public static void disableColorLogic() {
        colorLogicState.field_179197_a.func_179198_a();
    }

    public static void colorLogicOp(int i) {
        if (i != colorLogicState.field_179196_b) {
            colorLogicState.field_179196_b = i;
            GL11.glLogicOp(i);
        }

    }

    public static void enableTexGenCoord(TexGen texGen) {
        texGenCoord(texGen).field_179067_a.func_179200_b();
    }

    public static void disableTexGenCoord(TexGen texGen) {
        texGenCoord(texGen).field_179067_a.func_179198_a();
    }

    public static void texGen(TexGen texGen, int i) {
        TexGenCoord texGenCoord = texGenCoord(texGen);
        if (i != texGenCoord.field_179066_c) {
            texGenCoord.field_179066_c = i;
            GL11.glTexGeni(texGenCoord.field_179065_b, 9472, i);
        }

    }

    public static void texGen(TexGen texGen, int i, FloatBuffer floatBuffer) {
        GL11.glTexGen(texGenCoord(texGen).field_179065_b, i, floatBuffer);
    }

    private static TexGenCoord texGenCoord(TexGen texGen) {
        return switch (texGen) {
            case S -> texGenState.field_179064_a;
            case T -> texGenState.field_179062_b;
            case R -> texGenState.field_179063_c;
            case Q -> texGenState.field_179061_d;
        };
    }

    public static void setActiveTexture(int i) {
        if (activeTextureUnit != i - OpenGlHelper.defaultTexUnit) {
            activeTextureUnit = i - OpenGlHelper.defaultTexUnit;
            OpenGlHelper.setActiveTexture(i);
        }

    }

    public static void enableTexture2D() {
        textureState[activeTextureUnit].field_179060_a.func_179200_b();
    }

    public static void disableTexture2D() {
        textureState[activeTextureUnit].field_179060_a.func_179198_a();
    }

    public static int generateTexture() {
        return GL11.glGenTextures();
    }

    public static void deleteTexture(int i) {
        GL11.glDeleteTextures(i);
        TextureState[] textureStates = BackportedGlStateManager.textureState;
        int j = textureStates.length;

        for (TextureState textureState : textureStates) {
            if (textureState.field_179059_b == i) {
                textureState.field_179059_b = -1;
            }
        }

    }

    public static void bindTexture(int i) {
        if (i != textureState[activeTextureUnit].field_179059_b) {
            textureState[activeTextureUnit].field_179059_b = i;
            GL11.glBindTexture(3553, i);
        }

    }

    public static void enableNormalize() {
        normalizeState.func_179200_b();
    }

    public static void disableNormalize() {
        normalizeState.func_179198_a();
    }

    public static void shadeModel(int i) {
        if (i != activeShadeModel) {
            activeShadeModel = i;
            GL11.glShadeModel(i);
        }

    }

    public static void enableRescaleNormal() {
        rescaleNormalState.func_179200_b();
    }

    public static void disableRescaleNormal() {
        rescaleNormalState.func_179198_a();
    }

    public static void viewport(int i, int j, int k, int l) {
        GL11.glViewport(i, j, k, l);
    }

    public static void colorMask(boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        if (bl != colorMaskState.field_179188_a || bl2 != colorMaskState.field_179186_b || bl3 != colorMaskState.field_179187_c || bl4 != colorMaskState.field_179185_d) {
            colorMaskState.field_179188_a = bl;
            colorMaskState.field_179186_b = bl2;
            colorMaskState.field_179187_c = bl3;
            colorMaskState.field_179185_d = bl4;
            GL11.glColorMask(bl, bl2, bl3, bl4);
        }

    }

    public static void clearDepth(double d) {
        if (d != clearState.field_179205_a) {
            clearState.field_179205_a = d;
            GL11.glClearDepth(d);
        }

    }

    public static void clearColor(float f, float g, float h, float i) {
        if (f != clearState.field_179203_b.field_179195_a || g != clearState.field_179203_b.field_179193_b || h != clearState.field_179203_b.field_179194_c || i != clearState.field_179203_b.field_179192_d) {
            clearState.field_179203_b.field_179195_a = f;
            clearState.field_179203_b.field_179193_b = g;
            clearState.field_179203_b.field_179194_c = h;
            clearState.field_179203_b.field_179192_d = i;
            GL11.glClearColor(f, g, h, i);
        }

    }

    public static void clear(int i) {
        GL11.glClear(i);
    }

    public static void matrixMode(int i) {
        GL11.glMatrixMode(i);
    }

    public static void loadIdentity() {
        GL11.glLoadIdentity();
    }

    public static void pushMatrix() {
        GL11.glPushMatrix();
    }

    public static void popMatrix() {
        GL11.glPopMatrix();
    }

    public static void getFloat(int i, FloatBuffer floatBuffer) {
        GL11.glGetFloat(i, floatBuffer);
    }

    public static void ortho(double d, double e, double f, double g, double h, double i) {
        GL11.glOrtho(d, e, f, g, h, i);
    }

    public static void rotate(float f, float g, float h, float i) {
        GL11.glRotatef(f, g, h, i);
    }

    public static void scale(float f, float g, float h) {
        GL11.glScalef(f, g, h);
    }

    public static void scale(double d, double e, double f) {
        GL11.glScaled(d, e, f);
    }

    public static void translate(float f, float g, float h) {
        GL11.glTranslatef(f, g, h);
    }

    public static void translate(double d, double e, double f) {
        GL11.glTranslated(d, e, f);
    }

    public static void multMatrix(FloatBuffer floatBuffer) {
        GL11.glMultMatrix(floatBuffer);
    }

    public static void color(float f, float g, float h, float i) {
        if (f != colorState.field_179195_a || g != colorState.field_179193_b || h != colorState.field_179194_c || i != colorState.field_179192_d) {
            colorState.field_179195_a = f;
            colorState.field_179193_b = g;
            colorState.field_179194_c = h;
            colorState.field_179192_d = i;
            GL11.glColor4f(f, g, h, i);
        }

    }

    public static void color(float f, float g, float h) {
        color(f, g, h, 1.0F);
    }

    public static void resetColor() {
        colorState.field_179195_a = colorState.field_179193_b = colorState.field_179194_c = colorState.field_179192_d = -1.0F;
    }

    public static void callList(int i) {
        GL11.glCallList(i);
    }

    static {
        int j;
        for(j = 0; j < 8; ++j) {
            lightState[j] = new BooleanState(16384 + j);
        }

        for(j = 0; j < 8; ++j) {
            textureState[j] = new TextureState();
        }

    }

    static class BooleanState {
        private final int field_179202_a;
        private boolean field_179201_b = false;

        public BooleanState(int i) {
            this.field_179202_a = i;
        }

        public void func_179198_a() {
            this.func_179199_a(false);
        }

        public void func_179200_b() {
            this.func_179199_a(true);
        }

        public void func_179199_a(boolean bl) {
            if (bl != this.field_179201_b) {
                this.field_179201_b = bl;
                if (bl) {
                    GL11.glEnable(this.field_179202_a);
                } else {
                    GL11.glDisable(this.field_179202_a);
                }
            }

        }
    }

    static class Color {
        public float field_179195_a = 1.0F;
        public float field_179193_b = 1.0F;
        public float field_179194_c = 1.0F;
        public float field_179192_d = 1.0F;

        public Color() {
        }

        public Color(float f, float g, float h, float i) {
            this.field_179195_a = f;
            this.field_179193_b = g;
            this.field_179194_c = h;
            this.field_179192_d = i;
        }
    }

    static class ColorMask {
        public boolean field_179188_a;
        public boolean field_179186_b;
        public boolean field_179187_c;
        public boolean field_179185_d;

        private ColorMask() {
            this.field_179188_a = true;
            this.field_179186_b = true;
            this.field_179187_c = true;
            this.field_179185_d = true;
        }
    }

    public enum TexGen {
        S,
        T,
        R,
        Q;

        TexGen() {
        }
    }

    static class TexGenCoord {
        public BooleanState field_179067_a;
        public int field_179065_b;
        public int field_179066_c = -1;

        public TexGenCoord(int i, int j) {
            this.field_179065_b = i;
            this.field_179067_a = new BooleanState(j);
        }
    }

    static class TexGenState {
        public TexGenCoord field_179064_a;
        public TexGenCoord field_179062_b;
        public TexGenCoord field_179063_c;
        public TexGenCoord field_179061_d;

        private TexGenState() {
            this.field_179064_a = new TexGenCoord(8192, 3168);
            this.field_179062_b = new TexGenCoord(8193, 3169);
            this.field_179063_c = new TexGenCoord(8194, 3170);
            this.field_179061_d = new TexGenCoord(8195, 3171);
        }
    }

    static class StencilState {
        public StencilFunc field_179078_a;
        public int field_179076_b;
        public int field_179077_c;
        public int field_179074_d;
        public int field_179075_e;

        private StencilState() {
            this.field_179078_a = new StencilFunc();
            this.field_179076_b = -1;
            this.field_179077_c = 7680;
            this.field_179074_d = 7680;
            this.field_179075_e = 7680;
        }
    }

    static class StencilFunc {
        public int field_179081_a;
        public int field_179079_b;
        public int field_179080_c;

        private StencilFunc() {
            this.field_179081_a = 519;
            this.field_179079_b = 0;
            this.field_179080_c = -1;
        }
    }

    static class ClearState {
        public double field_179205_a;
        public Color field_179203_b;
        public int field_179204_c;

        private ClearState() {
            this.field_179205_a = 1.0D;
            this.field_179203_b = new Color(0.0F, 0.0F, 0.0F, 0.0F);
            this.field_179204_c = 0;
        }
    }

    static class ColorLogicState {
        public BooleanState field_179197_a;
        public int field_179196_b;

        private ColorLogicState() {
            this.field_179197_a = new BooleanState(3058);
            this.field_179196_b = 5379;
        }
    }

    static class PolygonOffsetState {
        public BooleanState field_179044_a;
        public BooleanState field_179042_b;
        public float field_179043_c;
        public float field_179041_d;

        private PolygonOffsetState() {
            this.field_179044_a = new BooleanState(32823);
            this.field_179042_b = new BooleanState(10754);
            this.field_179043_c = 0.0F;
            this.field_179041_d = 0.0F;
        }
    }

    static class CullState {
        public BooleanState field_179054_a;
        public int field_179053_b;

        private CullState() {
            this.field_179054_a = new BooleanState(2884);
            this.field_179053_b = 1029;
        }
    }

    static class FogState {
        public BooleanState field_179049_a;
        public int field_179047_b;
        public float field_179048_c;
        public float field_179045_d;
        public float field_179046_e;

        private FogState() {
            this.field_179049_a = new BooleanState(2912);
            this.field_179047_b = 2048;
            this.field_179048_c = 1.0F;
            this.field_179045_d = 0.0F;
            this.field_179046_e = 1.0F;
        }
    }

    static class DepthState {
        public BooleanState field_179052_a;
        public boolean field_179050_b;
        public int field_179051_c;

        private DepthState() {
            this.field_179052_a = new BooleanState(2929);
            this.field_179050_b = true;
            this.field_179051_c = 513;
        }
    }

    static class BlendState {
        public BooleanState field_179213_a;
        public int field_179211_b;
        public int field_179212_c;
        public int field_179209_d;
        public int field_179210_e;

        private BlendState() {
            this.field_179213_a = new BooleanState(3042);
            this.field_179211_b = 1;
            this.field_179212_c = 0;
            this.field_179209_d = 1;
            this.field_179210_e = 0;
        }
    }

    static class ColorMaterialState {
        public BooleanState field_179191_a;
        public int field_179189_b;
        public int field_179190_c;

        private ColorMaterialState() {
            this.field_179191_a = new BooleanState(2903);
            this.field_179189_b = 1032;
            this.field_179190_c = 5634;
        }
    }

    static class AlphaState {
        public BooleanState field_179208_a;
        public int field_179206_b;
        public float field_179207_c;

        private AlphaState() {
            this.field_179208_a = new BooleanState(3008);
            this.field_179206_b = 519;
            this.field_179207_c = -1.0F;
        }
    }

    static class TextureState {
        public BooleanState field_179060_a;
        public int field_179059_b;

        private TextureState() {
            this.field_179060_a = new BooleanState(3553);
            this.field_179059_b = 0;
        }
    }
}
