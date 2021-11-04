package io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.extruded;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ExtrudedModelRender extends ModelRenderer {
	public ExtrudedModelRender(ModelBase modelBase, int i, int j) {
		super(modelBase, i, j);
	}

	@Override
	public void addBox(float f, float g, float h, int i, int j, int k, float l) {
		this.cubeList.add(new ExtrudedModelBox(this, this.textureOffsetX, this.textureOffsetY, f, g, h, i, j, k, l));
	}
}
