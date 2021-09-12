package io.github.orioncraftmc.orion.version.v1_7_10.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class OneDotSevenBridgeProvider implements OrionCraftBridgeProvider {
	@NotNull
	@Override
	public MinecraftBridge getMinecraft() {
		return (MinecraftBridge) Minecraft.getMinecraft();
	}
}
