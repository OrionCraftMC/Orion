package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.gui.OrionGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
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
	public GameSettings gameSettings;

	@NotNull
	@Override
	public ScaledResolutionBridge getScaledResolution() {
		//noinspection ConstantConditions
		return (ScaledResolutionBridge) new ScaledResolution(gameSettings, displayWidth, displayHeight);
	}

	@Shadow
	public abstract void displayGuiScreen(GuiScreen guiScreen);

	@Shadow
	public GuiScreen currentScreen;

	@Override
	public void openScreen(@NotNull OrionScreen orionScreen) {
		displayGuiScreen(new OrionGuiScreen(orionScreen));
	}

	@Override
	public void drawDefaultBackground() {
		if (currentScreen != null) {
			currentScreen.drawDefaultBackground();
		}
	}

	@Override
	public int getGameHeight() {
		return displayHeight;
	}

	@Override
	public int getGameWidth() {
		return displayWidth;
	}
}
