package org.spoutcraft.api.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractLogger {
	protected final Logger logger;

	public AbstractLogger(Logger logger) {
		this.logger = logger;
	}

	public abstract void init();

	public void log(Level level, String message) {
		logger.log(level, message);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void warning(String message) {
		logger.warning(message);
	}

	public void severe(String message) {
		logger.severe(message);
	}
}
