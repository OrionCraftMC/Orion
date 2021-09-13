package io.github.orioncraftmc.orion.version.v1_7_10.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.gui.OrionGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements MinecraftBridge {
	@Shadow
	public int displayWidth;

	@Shadow
	public int displayHeight;

	@Shadow
	public abstract void displayGuiScreen(GuiScreen guiScreen);

	@NotNull
	@Override
	public ScaledResolutionBridge getScaledResolution() {
		return (ScaledResolutionBridge) new ScaledResolution((Minecraft) ((Object) this), displayWidth, displayHeight);
	}


	@Override
	public void openScreen(@NotNull OrionScreen orionScreen) {
		displayGuiScreen(new OrionGuiScreen(orionScreen));
	}
}
