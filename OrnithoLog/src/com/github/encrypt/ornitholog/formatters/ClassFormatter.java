package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;


public class ClassFormatter extends LogFormatter {
	
	public ClassFormatter(Logger logger) {
		super(logger);
	}

	public ClassFormatter(Logger logger, LogFormatter formatter) {
		super(logger, formatter);
	}

	public String appendToMessage(String message) {
		return this.logger.getClass() + " " + message;
	}

}