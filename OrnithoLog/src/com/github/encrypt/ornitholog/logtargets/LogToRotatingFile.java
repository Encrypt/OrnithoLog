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

	// Gets the size of the file (in bytes!)
	private double getFileSize() {
		File logFile = new File(targetFile);
		
		if(logFile.exists()) {
			return logFile.length();
		}
		else {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Couldn't create file: " + targetFile);
			}
			return -1;
		}
	}
	
	// Rotates the log file
	private void rotateFile() {
		
		int maxFileInt = 0;
		boolean fileExists = true;
		File logFile;
		
		// Finds the file with the greatest number
		do {
			switch(maxFileInt) {
				case 0:
					logFile = new File(targetFile);
					break;
	
				default:
					logFile = new File(targetFile + "." + maxFileInt);
					break;
				}
			
			if(!logFile.exists())
				fileExists = false;
			maxFileInt++;
			
		} while(fileExists);
		
		// Creates the new log file (.maxFileInt + 1)
		logFile = new File(targetFile + "." + maxFileInt + 1);
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			System.err.println("Couldn't create file: " + targetFile);
		}
		
		// Moves each file for targetFile to be the new fresh log file
		for(int i = maxFileInt ; i >= 0 ; i--) {
			
			switch(i) {
				case 0:
					logFile = new File(targetFile);
					break;
	
				default:
					logFile = new File(targetFile + "." + i);
					break;
			}
			
			logFile.renameTo(new File(targetFile + "." + (i+1)));
		}
	}
	
	// Saves a line of log in the file
	@Override
	public void save(String logData) {
		
		// Gets the file size and rotates if necessary
		if(getFileSize() >= maxFileSize)
			rotateFile();
		
		try {
			FileWriter writer = new FileWriter(targetFile, true);
			writer.write(logData);
			writer.close();
		} catch(IOException e) {
			System.err.println("Error:" + e.getMessage());
		}
		
	}

}
