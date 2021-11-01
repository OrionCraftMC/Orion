/*
 * Copyright (C) 2021 OrionCraftMC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.orioncraftmc.orion.version.v1_5_2.mixins.bridge.minecraft;

import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.gui.GuiScreenBridge;
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.gui.OrionGuiScreen;
import java.io.File;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

	@Shadow
	public abstract void displayGuiScreen(GuiScreen guiScreen);

	@Shadow
	public GuiScreen currentScreen;

	@Shadow
	public FontRenderer fontRenderer;

	@Shadow
	public File mcDataDir;

	@Shadow
	public EntityClientPlayerMP thePlayer;

	@NotNull
	@Override
	public ScaledResolutionBridge getScaledResolution() {
		//noinspection ConstantConditions
		return (ScaledResolutionBridge) new ScaledResolution(gameSettings, displayWidth, displayHeight);
	}

	@NotNull
	@Override
	public FontRendererBridge getFontRenderer() {
		return (FontRendererBridge) fontRenderer;
	}

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
}
