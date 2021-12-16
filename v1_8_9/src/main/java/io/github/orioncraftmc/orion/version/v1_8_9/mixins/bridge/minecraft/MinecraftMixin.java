package io.github.orioncraftmc.orion.version.v1_8_9.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.gui.GuiScreenBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.*;
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge;
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import io.github.orioncraftmc.orion.version.common.bridge.gui.OrionGuiScreen;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements MinecraftBridge {

	@Shadow
	public FontRenderer fontRendererObj;
	@Shadow
	public GameSettings gameSettings;
	@Shadow
	public int displayWidth;
	@Shadow
	public int displayHeight;
	@Shadow
	public GuiScreen currentScreen;
	@Final
	@Shadow
	public File mcDataDir;
	@Shadow
	public TextureManager renderEngine;

	@NotNull
	@Override
	public FontRendererBridge getFontRenderer() {
		return (FontRendererBridge) fontRendererObj;
	}

	@NotNull
	@Override
	public ScaledResolutionBridge getScaledResolution() {
		return (ScaledResolutionBridge) new ScaledResolution((Minecraft) (Object) this);
	}

	@Shadow
	public abstract void displayGuiScreen(GuiScreen guiScreen);

	@Override
	public void openScreen(OrionScreen orionScreen) {
		if (orionScreen == null) {
			displayGuiScreen(null);
		} else {
			displayGuiScreen(new OrionGuiScreen(orionScreen));
		}
	}

	@Override
	public void drawDefaultBackground() {
		if (currentScreen != null) {
			currentScreen.drawDefaultBackground();
		}
	}

	@NotNull
	@Override
	public File getGameAppDirectory() {
		return mcDataDir;
	}

	@Override
	public int getGameHeight() {
		return displayHeight;
	}

	@Override
	public int getGameWidth() {
		return displayWidth;
	}

	@NotNull
	@Override
	public GameSettingsBridge getGameSettings() {
		return (GameSettingsBridge) gameSettings;
	}

	@Nullable
	@Override
	public GuiScreenBridge getCurrentOpenedScreen() {
		if (currentScreen instanceof OrionGuiScreen orionGuiScreen) {
			return orionGuiScreen.getScreen();
		}
		return (GuiScreenBridge) currentScreen;
	}

	@Override
	public int getCurrentFps() {
		return Minecraft.debugFPS;
	}

	@NotNull
	@Override
	public RenderEngineBridge getRenderEngine() {
		return (RenderEngineBridge) this.renderEngine;
	}
}
