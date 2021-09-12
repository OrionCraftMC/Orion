package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public class MinecraftMixin implements MinecraftBridge {
	@Shadow
	public int displayWidth;

	@Shadow
	public int displayHeight;

	@NotNull
	@Override
	public ScaledResolutionBridge getScaledResolution() {
		return (ScaledResolutionBridge) new ScaledResolution((Minecraft) ((Object) this), displayWidth, displayHeight);
	}
}
