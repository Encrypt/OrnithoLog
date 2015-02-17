package com.github.encrypt.ornitholog.logtargets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogToRotatingFile extends LogTarget {

	// Target file: path to the file
	private String targetFile;
	private double maxFileSize;
	
	// Constructor
	public LogToRotatingFile(String targetFile, int maxFileSize) {
		this.targetFile = targetFile;
		this.maxFileSize = maxFileSize; 
	}
	
	// Creates a given file
	private void createFile(File fileToCreate) {
		
		if(fileToCreate.exists()) {
			System.err.println("The file: " + fileToCreate.getPath() + " already exists!");
		}
		else {
			try {
				fileToCreate.createNewFile();
			} catch (IOException e) {
				System.err.println("Couldn't create file: " + fileToCreate.getPath());
				System.err.println("Attempt returned the error: " + e.getMessage());
			}
		}
		
	}
	
	// Rotates the log file
	private void rotateFile() {
		
		int maxFileInt = 0;
		boolean fileExists = true;
		File logFile;
		
		// Finds the file with the greatest number
		do {
			if(maxFileInt == 0)
				logFile = new File(targetFile);
			else 
				logFile = new File(targetFile + "." + maxFileInt);
			
			if(!logFile.exists())
				fileExists = false;
			else
				maxFileInt++;
			
		} while(fileExists);
		
		// Creates the new log file (.maxFileInt)
		logFile = new File(targetFile + "." + maxFileInt);
		createFile(logFile);
		
		// Moves each file for targetFile to be the new fresh log file
		for(int i = maxFileInt - 1 ; i >= 0 ; i--) {
			
			if(i == 0)
				logFile = new File(targetFile);
			else
				logFile = new File(targetFile + "." + i);
			
			logFile.renameTo(new File(targetFile + "." + (i+1)));
		}
	}
	
	// Saves a line of log in the file
	@Override
	public void save(String logData) {
		
		// Creates the file if it doesn't exist
		File logFile = new File(targetFile);
		createFile(logFile);
		
		// Gets the file size and rotates if necessary
		if(logFile.length() >= maxFileSize)
			rotateFile();
		
		// Writes the log line in the file
		try {
			FileWriter writer = new FileWriter(targetFile, true);
			writer.write(logData);
			writer.close();
		} catch(IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
	}

}
