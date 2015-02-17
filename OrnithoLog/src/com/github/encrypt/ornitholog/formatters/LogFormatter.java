package com.github.encrypt.ornitholog.formatters;


public abstract class LogFormatter {

	private LogFormatter formatter;

	public LogFormatter() {}

	public LogFormatter(LogFormatter formatter) {
		this.formatter = formatter;
	}

	public String format(String message) {
		if (this.formatter != null)
			message = this.formatter.format(message);

		return this.appendToMessage(message);
	}

	protected abstract String appendToMessage(String message);

}