package io.github.orioncraftmc.orion.version.v1_7_10.bridge;

import io.github.orioncraftmc.orion.api.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public enum LoggerBridge implements Logger {
	INSTANCE;

	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("OrionCraft");

	@Override
	public void debug(@NotNull String s, @NotNull Object... objects) {
		LOGGER.debug(s, objects);
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
