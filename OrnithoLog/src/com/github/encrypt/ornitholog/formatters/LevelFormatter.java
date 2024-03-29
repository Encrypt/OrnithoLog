package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;


public class LevelFormatter extends LogFormatter {

	protected Logger logger;
	
	public LevelFormatter(Logger logger) {
		super();
		this.logger = logger;
	}

	public LevelFormatter(LogFormatter formatter, Logger logger) {
		super(formatter);
		this.logger = logger;
	}

	@Override
	public String appendToMessage(String message) {
		return "[" + this.logger.getLevel().toString() + "] " + message;
	}

}