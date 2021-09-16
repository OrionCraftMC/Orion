package io.github.orioncraftmc.orion.version.v1_5_2.bridge.resources;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import org.jetbrains.annotations.NotNull;

public class ResourceLocation implements ResourceLocationBridge {
	private final String namespace;
	private final String resourcePath;

	public ResourceLocation(String namespace, String resourcePath) {
		this.namespace = namespace;
		this.resourcePath = resourcePath;
	}

	@NotNull
	@Override
	public String getFullPath() {
		return "/assets/" + getNamespace() + "/" + getResource();
	}

	@NotNull
	@Override
	public String getNamespace() {
		return namespace;
	}

	@NotNull
	@Override
	public String getResource() {
		return resourcePath;
	}
}
