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

	public boolean[] getCorrectVertexOrderPositionsForTranslate(int index) {
		switch (index) {
			//case 0: return new boolean[]{true, true, true, true, false, false, true};
			case 4: return new boolean[]{false, true, false, true, true, true, true};
			case 5: return new boolean[]{false, true, true, true, true, true, true};
		}
		// Translate X, Translate Y, Translate Z, Scale X, Scale Z, Positive Scale X, Positive Scale Y

		return null;
	}

	@Override
	public void render(Tessellator tessellator, float f) {
		TexturedQuad[] list = this.quadList;
		for (int i = 0, listLength = list.length; i < listLength; i++) {
			TexturedQuad quad = list[i];
			Vec3 emptyVec = Vec3.createVectorHelper(0, 0, 0);
			List<PositionTextureVertex> textureVertices = Arrays.stream(quad.vertexPositions).toList();
			Comparator<PositionTextureVertex> comparator = Comparator.comparingDouble(
					o -> o.vector3D.distanceTo(emptyVec));

			PositionTextureVertex smallestPos = Collections.min(textureVertices, comparator);
			PositionTextureVertex biggestPos = Collections.max(textureVertices, comparator);

			boolean[] translationOrder = getCorrectVertexOrderPositionsForTranslate(i
			);
			if (translationOrder == null) continue;

			PositionTextureVertex firstPos = translationOrder[0] ? biggestPos : smallestPos;
			PositionTextureVertex secondPos = translationOrder[1] ? biggestPos : smallestPos;
			PositionTextureVertex thirdPos = translationOrder[2] ? biggestPos : smallestPos;


			GL11.glPushMatrix();
			GL11.glTranslatef(((float) firstPos.vector3D.xCoord * f),
					((float) secondPos.vector3D.yCoord * f),
					((float) thirdPos.vector3D.zCoord * f));

			GL11.glScaled(translationOrder[3] ? getScale(smallestPos, biggestPos, v -> v.xCoord) * (translationOrder[5] ? 1 : -1) : 1,
					-getScale(smallestPos, biggestPos, v -> v.yCoord), translationOrder[4] ? getScale(smallestPos, biggestPos, v -> v.zCoord) * (translationOrder[6] ? 1 : -1) : 1);

			ItemRenderer.renderItemIn2D(tessellator, smallestPos.texturePositionX, smallestPos.texturePositionY,
					biggestPos.texturePositionX,
					biggestPos.texturePositionY, (int) textureWidth, (int) textureHeight, 1 / 16f);
			GL11.glPopMatrix();

		}

	}

	private double getScale(PositionTextureVertex smallestPos, PositionTextureVertex biggestPos, Function<Vec3, Double> getter) {
		return (getter.apply(biggestPos.vector3D) - getter.apply(smallestPos.vector3D)) / 16;
	}
}
