package com.github.encrypt.ornitholog.formatters;

import java.util.Date;


public class DateFormatter extends LogFormatter {
	
	public DateFormatter() {
		super();
	}

	public DateFormatter(LogFormatter formatter) {
		super(formatter);
	}

	@Override
	public String appendToMessage(String message) {
		Date date = new Date();
		return "[" + date.toString() + "] " + message;
	}

}