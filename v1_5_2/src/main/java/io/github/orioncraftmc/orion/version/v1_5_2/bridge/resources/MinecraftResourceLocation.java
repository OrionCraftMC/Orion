package io.github.orioncraftmc.orion.version.v1_5_2.bridge.resources;

import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import org.jetbrains.annotations.NotNull;

public class MinecraftResourceLocation extends ResourceLocation {
	public MinecraftResourceLocation(String resourcePath) {
		super(OrionCraftConstants.MINECRAFT_RESOURCE_LOCATION_NS, resourcePath);
	}

	@NotNull
	@Override
	public String getFullPath() {
		// TODO: Implement new -> old path conversion
		return "/" + getResource();
	}
}
