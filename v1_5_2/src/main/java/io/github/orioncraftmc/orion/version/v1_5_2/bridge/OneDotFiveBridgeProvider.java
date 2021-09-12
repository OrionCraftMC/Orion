package io.github.orioncraftmc.orion.version.v1_5_2.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class OneDotFiveBridgeProvider implements OrionCraftBridgeProvider {
	@NotNull
	@Override
	public MinecraftBridge getMinecraft() {
		return (MinecraftBridge) Minecraft.getMinecraft();
	}
}
