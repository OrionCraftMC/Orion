package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft.resources;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin implements ResourceLocationBridge {

	@Shadow
	@Final
	public String resourceDomain;

	@Shadow
	@Final
	public String resourcePath;

	@NotNull
	@Override
	public String getNamespace() {
		return resourceDomain;
	}

	@NotNull
	@Override
	public String getResource() {
		return resourcePath;
	}

	@NotNull
	@Override
	public String getFullPath() {
		return "/assets/" + getNamespace() + "/" + getResource();
	}
}
