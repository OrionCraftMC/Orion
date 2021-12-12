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

package io.github.orioncraftmc.orion.version.v1_5_2.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.input.KeybindingUtils;
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuUtils;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils;
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import io.github.orioncraftmc.orion.api.logging.FallbackLogger;
import io.github.orioncraftmc.orion.api.logging.Logger;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.input.KeybindingUtilsImpl;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.menu.main.MainMenuUtilsImpl;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.rendering.OpenGlBridgeImpl;
import io.github.orioncraftmc.orion.version.v1_5_2.bridge.resources.ResourceLocationUtilsImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import org.jetbrains.annotations.NotNull;

public class OneDotFiveBridgeProvider implements OrionCraftBridgeProvider {
	private final OpenGlBridge openGlBridge = new OpenGlBridgeImpl();
	private final MainMenuUtilsImpl mainMenuUtils = new MainMenuUtilsImpl();
	private final KeybindingUtilsImpl keybindingUtilsImpl = new KeybindingUtilsImpl();
	private final ResourceLocationUtilsImpl resourceLocationUtils = new ResourceLocationUtilsImpl();

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

	@NotNull
	@Override
	public Logger getLogger() {
		return FallbackLogger.INSTANCE;
	}

	@NotNull
	@Override
	public MainMenuUtils getMainMenuUtils() {
		return mainMenuUtils;
	}

	@NotNull
	@Override
	public KeybindingUtils getKeybindingUtils() {
		return keybindingUtilsImpl;
	}
}
