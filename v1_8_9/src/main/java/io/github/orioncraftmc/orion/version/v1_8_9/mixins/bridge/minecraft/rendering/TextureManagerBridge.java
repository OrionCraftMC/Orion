package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft.rendering;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureManager.class)
public abstract class TextureManagerBridge implements RenderEngineBridge {
	@Shadow
	public abstract void bindTexture(ResourceLocation resource);

	@Override
	public void bindTexture(@NotNull ResourceLocationBridge resourceLocationBridge) {
		this.bindTexture((ResourceLocation) resourceLocationBridge);
	}
}
