package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.resources;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

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
	public String getFullPath() {
		return "/assets/" + getNamespace() + "/" + getResource();
	}

	@NotNull
	@Override
	public String getNamespace() {
		return this.resourceDomain;
	}

	@NotNull
	@Override
	public String getResource() {
		return this.resourcePath;
	}
}
