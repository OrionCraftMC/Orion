package io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.extruded;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemRenderer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class ExtrudedModelBox extends ModelBox {

	private final float textureWidth;
	private final float textureHeight;

	public ExtrudedModelBox(ModelRenderer modelRenderer, int i, int j, float f, float g, float h, int k, int l, int m, float n) {
		super(modelRenderer, i, j, f, g, h, k, l, m, n);
		textureWidth = modelRenderer.textureWidth;
		textureHeight = modelRenderer.textureHeight;
	}

	@Override
	public void render(Tessellator tessellator, float f) {
		TexturedQuad[] list = this.quadList;
		for (int i = 0, listLength = list.length; i < listLength; i++) {
			if (i != 0/* && i != 4*/) continue;
			TexturedQuad quad = list[i];
			if (false) {
				quad.draw(tessellator, f);
			} else{


			Vec3 var3 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[0].vector3D);
			Vec3 var4 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[2].vector3D);
			Vec3 var5 = var4.crossProduct(var3).normalize();

			if (quad.invertNormal) {
				GL11.glNormal3f(-((float)var5.xCoord), -((float)var5.yCoord), -((float)var5.zCoord));
			} else {
				GL11.glNormal3f((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
			}

			List<PositionTextureVertex> textureVertices = Arrays.stream(quad.vertexPositions).toList();
			Comparator<PositionTextureVertex> comparator = (p1, p2) -> {
				if (p1.texturePositionX < p2.texturePositionX || p1.texturePositionY < p2.texturePositionY)
					return -1;
				if (p1.texturePositionX > p2.texturePositionX || p1.texturePositionY > p2.texturePositionY)
					return 1;
				return 0;
			};
			PositionTextureVertex smallestPos = Collections.min(textureVertices, comparator);
			PositionTextureVertex biggestPos = Collections.max(textureVertices, comparator);

			Vec3 size = smallestPos.vector3D.subtract(biggestPos.vector3D);
			Vec3 sizeVector = size.normalize();
			boolean isMirroredSide = i == 0 || i == 1;
			float xScaleSign = sizeVector.xCoord == 0.0 ? -1 : 1;
			float zScaleSign = sizeVector.zCoord == 0.0 ? -1 : 1;

			if (isMirroredSide) {
				xScaleSign *= -1.0;
				zScaleSign *= -1.0;
			}

			boolean shouldScaleX = sizeVector.xCoord != 0.0;
			boolean shouldScaleZ = sizeVector.zCoord != 0.0;


			if (false && isMirroredSide) {
				shouldScaleX ^= true;
				shouldScaleZ ^= true;
			}

			boolean shouldTranslateX = sizeVector.xCoord == 0.0;
			boolean shouldTranslateZ = sizeVector.zCoord == 0.0;

			System.out.println("i = " + i);
			System.out.println("size = " + size);
			System.out.println("sizeVector = " + sizeVector);
			System.out.println("xScaleSign = " + xScaleSign);
			System.out.println("zScaleSign = " + zScaleSign);
			System.out.println("shouldScaleX = " + shouldScaleX);
			System.out.println("shouldScaleZ = " + shouldScaleZ);

			System.out.println("shouldTranslateX = " + shouldTranslateX);
			System.out.println("shouldTranslateZ = " + shouldTranslateZ);
			System.out.println("quad.invertNormal = " + quad.invertNormal);

			PositionTextureVertex firstPos = shouldTranslateX ? biggestPos : smallestPos;
			PositionTextureVertex secondPos = biggestPos;
			PositionTextureVertex thirdPos = shouldTranslateZ ? biggestPos : smallestPos;


			GL11.glPushMatrix();
			if (isMirroredSide) {
				GL11.glRotatef(90, 0, 1, 0);
			}
			GL11.glTranslatef(((float) firstPos.vector3D.xCoord * f),
					((float) secondPos.vector3D.yCoord * f),
					((float) thirdPos.vector3D.zCoord * f));

			GL11.glScaled(shouldScaleX ? getScale(smallestPos, biggestPos, v -> v.xCoord) * xScaleSign : 1,
					-getScale(smallestPos, biggestPos, v -> v.yCoord), shouldScaleZ ? getScale(smallestPos, biggestPos, v -> v.zCoord) * zScaleSign : 1);

			ItemRenderer.renderItemIn2D(tessellator, smallestPos.texturePositionX, smallestPos.texturePositionY,
					biggestPos.texturePositionX,
					biggestPos.texturePositionY, (int) textureWidth, (int) textureHeight, 1 / 16f);

			GL11.glPopMatrix();

			}
		}

	}

	private double getScale(PositionTextureVertex smallestPos, PositionTextureVertex biggestPos, Function<Vec3, Double> getter) {
		return (getter.apply(biggestPos.vector3D) - getter.apply(smallestPos.vector3D)) / 16;
	}
}
