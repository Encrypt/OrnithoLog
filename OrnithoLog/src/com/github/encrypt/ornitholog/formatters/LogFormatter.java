package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;


public abstract class LogFormatter {

	protected Logger logger;
	private LogFormatter formatter;

	public LogFormatter(Logger logger) {
		this.logger = logger;
	}

	public LogFormatter(Logger logger, LogFormatter formatter) {
		this.logger = logger;
		this.formatter = formatter;
	}

	public String format(String message) {
		if (this.formatter != null)
			message = this.formatter.format(message);

		return this.appendToMessage(message);
	}

	protected abstract String appendToMessage(String message);

}