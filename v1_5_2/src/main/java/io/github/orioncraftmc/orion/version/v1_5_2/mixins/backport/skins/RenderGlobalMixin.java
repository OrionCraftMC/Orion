package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins;

import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.RenderGlobal;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.Entity;
import net.optifine.Config;
import net.optifine.RandomMobs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {
	@Shadow
	@Final
	public RenderEngine renderEngine;

	/**
	 * @reason Skins and Cloaks are fetched manually
	 * @author OrionCraftMc
	 */
	@Overwrite
	public void onEntityCreate(Entity par1Entity) {
		par1Entity.updateCloak();
		if (par1Entity.skinUrl != null) {
			this.renderEngine.obtainImageData(par1Entity.skinUrl, new ImageBufferDownload());
		}

		if (par1Entity.cloakUrl != null) {
			this.renderEngine.obtainImageData(par1Entity.cloakUrl, new ImageBufferDownload());
		}

		if (Config.isRandomMobs()) {
			RandomMobs.entityLoaded(par1Entity);
		}

	}
}
