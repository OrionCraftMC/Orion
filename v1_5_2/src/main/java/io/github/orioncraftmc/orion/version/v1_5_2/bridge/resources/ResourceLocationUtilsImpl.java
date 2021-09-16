package io.github.orioncraftmc.orion.version.v1_5_2.bridge.resources;

import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils;
import org.jetbrains.annotations.NotNull;

public class ResourceLocationUtilsImpl implements ResourceLocationUtils {
	@NotNull
	@Override
	public ResourceLocationBridge createNewMinecraftResourceLocation(@NotNull String s) {
		return new MinecraftResourceLocation(s);
	}

	@NotNull
	@Override
	public ResourceLocationBridge createNewOrionResourceLocation(@NotNull String s) {
		return new ResourceLocation(OrionCraftConstants.ORION_RESOURCE_LOCATION_NS, s);
	}
}
