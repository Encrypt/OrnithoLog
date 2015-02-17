package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.Logger;

import java.util.Date;


public class DateFormatter extends LogFormatter {
	
	public DateFormatter(Logger logger) {
		super(logger);
	}

	public DateFormatter(Logger logger, LogFormatter formatter) {
		super(logger, formatter);
	}

	public String appendToMessage(String message) {
		Date date = new Date();
		return date.toString() + " " + message;
	}

}