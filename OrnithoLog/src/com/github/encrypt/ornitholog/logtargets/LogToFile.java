package com.github.encrypt.ornitholog.logtargets;

import java.io.FileWriter;
import java.io.IOException;

public class LogToFile extends LogTarget {

	// Target file: path to the file
	private String targetFile;
	
	// Constructor
	public LogToFile(String targetFile) {
		this.targetFile = targetFile;
	}

	// Saves a line of log in the file
	@Override
	public void save(String logData) {

		try {
			FileWriter writer = new FileWriter(targetFile, true);
			writer.write(logData);
			writer.close();
		} catch(IOException e) {
			System.err.println("Error:" + e.getMessage());
		}
		
	}

}
