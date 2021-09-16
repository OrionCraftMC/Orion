package io.github.orioncraftmc.orion.version.v1_7_10.bridge.resources;

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ResourceLocationUtilsImpl implements ResourceLocationUtils {
	@NotNull
	@Override
	public ResourceLocationBridge createNewMinecraftResourceLocation(@NotNull String s) {
		return (ResourceLocationBridge) new ResourceLocation(s);
	}

	@NotNull
	@Override
	public ResourceLocationBridge createNewOrionResourceLocation(@NotNull String s) {
		return (ResourceLocationBridge) new ResourceLocation("orion", s);
	}
}
