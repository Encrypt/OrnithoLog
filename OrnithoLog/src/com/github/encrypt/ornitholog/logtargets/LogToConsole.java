package com.github.encrypt.ornitholog.logtargets;

public class LogToConsole extends LogTarget {

	// Constructor
	public LogToConsole() {}

	// Displays the log in the console
	@Override
	public void save(String logData) {
		System.out.println(logData);	
	}

}
