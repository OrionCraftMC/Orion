package io.github.orioncraftmc.orion.version.v1_5_2.mixins.backport.skins.render;

import io.github.orioncraftmc.orion.version.v1_5_2.backport.skins.ducks.RenderPlayerDuck;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.render.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderLiving.class)
public class RenderLivingMixin {
	@Redirect(method = {"doRenderLiving", "renderModel", "renderArrowsStuckInEntity"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderLiving;mainModel:Lnet/minecraft/client/model/ModelBase;"))
	public ModelBase onGetEntityModel(RenderLiving instance, EntityLiving entityLiving) {
		if (entityLiving instanceof EntityPlayer player && instance instanceof RenderPlayerDuck duck) {
			return duck.getPlayerModelToRender(player);
		}
		return instance.mainModel;
	}
}
