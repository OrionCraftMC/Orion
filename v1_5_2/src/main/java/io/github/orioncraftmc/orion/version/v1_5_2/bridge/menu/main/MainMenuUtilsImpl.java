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

package io.github.orioncraftmc.orion.version.v1_5_2.bridge.menu.main;

import io.github.orioncraftmc.orion.api.bridge.main.MainMenuAction;
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.StringTranslate;
import org.jetbrains.annotations.NotNull;

public class MainMenuUtilsImpl implements MainMenuUtils {
	@Override
	public void executeMainMenuAction(@NotNull MainMenuAction mainMenuAction) {
		var mc = Minecraft.getMinecraft();
		var previousScreen = mc.currentScreen;

		switch (mainMenuAction) {
			case SINGLEPLAYER -> mc.displayGuiScreen(new GuiSelectWorld(previousScreen));
			case MULTIPLAYER -> mc.displayGuiScreen(new GuiMultiplayer(previousScreen));
			case LANGUAGE_PICKER -> mc.displayGuiScreen(new GuiLanguage(previousScreen, mc.gameSettings));
			case OPTIONS -> mc.displayGuiScreen(new GuiOptions(previousScreen, mc.gameSettings));
			case EXIT_GAME -> mc.shutdown();
			default -> {
			}
		}
	}

	@NotNull
	@Override
	public String getTranslationForMainMenuAction(@NotNull MainMenuAction mainMenuAction) {
		var stringTranslate = StringTranslate.getInstance();

		String key = switch (mainMenuAction) {
			case SINGLEPLAYER -> "menu.singleplayer";
			case MULTIPLAYER -> "menu.multiplayer";
			case LANGUAGE_PICKER -> "options.language";
			case OPTIONS -> "menu.options";
			case EXIT_GAME -> "menu.quit";
			default -> throw new IllegalArgumentException();
		};

		return stringTranslate.translateKey(key);
	}
}
