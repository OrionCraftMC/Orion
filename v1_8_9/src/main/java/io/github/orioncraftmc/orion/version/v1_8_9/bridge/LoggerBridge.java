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

package io.github.orioncraftmc.orion.version.v1_8_9.bridge;

import io.github.orioncraftmc.orion.api.OrionCraftConstants;
import io.github.orioncraftmc.orion.api.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public enum LoggerBridge implements Logger {
	INSTANCE;

	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("OrionCraft");

	@Override
	public void debug(@NotNull String s, @NotNull Object... objects) {
		if (OrionCraftConstants.INSTANCE.isDevEnvironment()) {
			LOGGER.debug(s, objects);
		}
	}

	@Override
	public void error(@NotNull String s, @NotNull Object... objects) {
		LOGGER.error(s, objects);
	}

	@Override
	public void fatal(@NotNull String s, @NotNull Object... objects) {
		LOGGER.fatal(s, objects);
	}

	@Override
	public void info(@NotNull String s, @NotNull Object... objects) {
		LOGGER.info(s, objects);
	}

	@Override
	public void warn(@NotNull String s, @NotNull Object... objects) {
		LOGGER.warn(s, objects);
	}
}
