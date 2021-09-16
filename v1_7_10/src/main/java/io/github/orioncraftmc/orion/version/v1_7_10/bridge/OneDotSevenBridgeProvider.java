package io.github.orioncraftmc.orion.version.v1_7_10.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils;
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.rendering.OpenGlBridgeImpl;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.resources.ResourceLocationUtilsImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import org.jetbrains.annotations.NotNull;

public class OneDotSevenBridgeProvider implements OrionCraftBridgeProvider {
	private final OpenGlBridge openGlBridge = new OpenGlBridgeImpl();
	private final ResourceLocationUtilsImpl resourceLocationUtils  = new ResourceLocationUtilsImpl();

	@NotNull
	@Override
	public MinecraftBridge getMinecraftBridge() {
		return (MinecraftBridge) Minecraft.getMinecraft();
	}

	@NotNull
	@Override
	public OpenGlBridge getOpenGlBridge() {
		return openGlBridge;
	}

	@NotNull
	@Override
	public TessellatorBridge getTessellator() {
		return (TessellatorBridge) Tessellator.instance;
	}

	@NotNull
	@Override
	public ResourceLocationUtils getResourceLocationUtils() {
		return resourceLocationUtils;
	}
}
