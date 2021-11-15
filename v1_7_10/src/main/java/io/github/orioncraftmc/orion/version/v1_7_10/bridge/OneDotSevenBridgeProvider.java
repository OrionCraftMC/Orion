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

package io.github.orioncraftmc.orion.version.v1_7_10.bridge;

import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider;
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge;
import io.github.orioncraftmc.orion.api.bridge.minecraft.menu.main.MainMenuUtils;
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils;
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge;
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge;
import io.github.orioncraftmc.orion.api.logging.Logger;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.main.MainMenuUtilsImpl;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.rendering.OpenGlBridgeImpl;
import io.github.orioncraftmc.orion.version.v1_7_10.bridge.resources.ResourceLocationUtilsImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import org.jetbrains.annotations.NotNull;

public class OneDotSevenBridgeProvider implements OrionCraftBridgeProvider {
	private final OpenGlBridge openGlBridge = new OpenGlBridgeImpl();
	private final ResourceLocationUtilsImpl resourceLocationUtils  = new ResourceLocationUtilsImpl();
	private final MainMenuUtilsImpl mainMenuUtils = new MainMenuUtilsImpl();


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
		return LoggerBridge.INSTANCE;
	}

	@NotNull
	@Override
	public MainMenuUtils getMainMenuUtils() {
		return mainMenuUtils;
	}
}
