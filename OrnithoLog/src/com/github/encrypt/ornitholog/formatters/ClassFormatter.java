package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;


public class ClassFormatter extends LogFormatter {

	protected Logger logger;
	
	public ClassFormatter(Logger logger) {
		super();
		this.logger = logger;
	}

	public ClassFormatter(LogFormatter formatter, Logger logger) {
		super(formatter);
		this.logger = logger;
	}

	public String appendToMessage(String message) {
		return "[" + this.logger.getClassName() + "] " + message;
	}

}