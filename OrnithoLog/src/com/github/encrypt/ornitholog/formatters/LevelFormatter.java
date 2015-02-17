package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;


public class LevelFormatter extends LogFormatter {
	
	public LevelFormatter(Logger logger) {
		super(logger);
	}

	public LevelFormatter(Logger logger, LogFormatter formatter) {
		super(logger, formatter);
	}

	public String appendToMessage(String message) {
		return this.logger.getLevel().toString() + " " + message;
	}

}