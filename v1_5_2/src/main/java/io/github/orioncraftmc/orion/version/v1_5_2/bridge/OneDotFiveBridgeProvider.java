package io.github.orioncraftmc.orion.version.v1_5_2.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.rendering.OpenGlBridgeImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import org.jetbrains.annotations.NotNull;

public class OneDotFiveBridgeProvider implements OrionCraftBridgeProvider {
	private final OpenGlBridge openGlBridge = new OpenGlBridgeImpl();

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
}
