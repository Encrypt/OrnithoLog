package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.logtargets.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoggerFactory {

	private static ArrayList<Logger> loggers;
	
	// Gets a logger, if we give it a name
	public static Logger getLogger(String maClasse) {
		for(Logger logger : loggers){
			
			if(logger.className.equals(maClasse)){
				return logger;
			}
		}
		Logger logger = new Logger(maClasse);
		return logger;	
	}
	
	// Inits the loggers with the values of the properties file
	// Logger format example: fr.esiea.maclasse:target2.path = "/path/to/stuff"
	public static void initLoggers() {
		
		String readLine;
		String[] tokens = new String[2];
		String[] attributes = new String[2];
		
		// Reads the properties file
		try {
			BufferedReader br = new BufferedReader(new FileReader("properties"));
	
			while ((readLine = br.readLine()) != null) {
				tokens = readLine.split(":");
				
				// Gets the associated logger
				Logger logger = getLogger(tokens[0]);
				
				// Gets the attributes to set
				attributes = readLine.split("=");
				
				// Switch on the value of the 1st token before "="
				if(attributes[0].equals("level"))
					readLoglevel(logger, attributes[1]);
				
				else if(attributes[0].startsWith("target"))
					readTarget(logger, attributes);
				
				else
					System.err.println("Error: " + attributes[0] + " isn't a supported parameter.");
			}
			
			br.close();
	
		}
		catch(FileNotFoundException e) {
			System.err.println("Error: Couldn't open the properties file");
		}
		catch(IOException e) {
			System.err.println("Error while reading the properties file");
		}
	}
	
	// Reads the loglevel value
	private static void readLoglevel(Logger logger, String logLevel) {
		try {
			logger.setLevel(LogLevel.valueOf(logLevel));
		}
		catch(IllegalArgumentException e) {
			System.err.println("Error: The value " + logLevel + " is not a level.");
		}
		catch(NullPointerException e) {
			System.err.println("Error: No level value has been set for the logger " + logLevel);
		}
	}
	
	// Reads the target
	private static void readTarget(Logger logger, String attributes[]) {
		
		String dotSplit[] = attributes[0].split(".");
		
		// If there are two arguments with "path" -> sets the target path
		if(dotSplit.length == 2) {
			if(dotSplit[1].equals("path")) {
				int targetValue = Integer.parseInt(dotSplit[0].substring(6));
				readTargetPath(logger, targetValue, attributes[1]);
			}
		}
		
		// Else, there is no dot -> new target given in the file
		else if(dotSplit.length == 0) {
			
			try {
				int targetsSize = logger.targets.size();
				int targetValue = Integer.parseInt(attributes[0].substring(6));
		
				// If the targets doesn't exist in the logger (n-1 loggers), creates it & adds it to the logger
				if(targetsSize == targetValue - 1) {
					
					try {
						logger.addTarget((LogTarget)Class.forName(attributes[1]).newInstance());					
					}
					catch(ClassNotFoundException e) {
						System.err.println("Error: The LogTarget " + attributes[1] + " doesn't exist.");
					}
					catch (InstantiationException | IllegalAccessException e) {
						System.err.println("Error: The LogTarget " + attributes[1] + " couldn't be instanciated.");
					}
				}
				
				// Else, the number of loggers is incorrect, warns the user
				else
					System.err.println("Error: LogTarget #" + targetValue + " ; expected #" + (targetsSize + 1));
				
			}
			catch(NumberFormatException e) {
				System.err.println("Error: Expected target<value> but got: target" + attributes[0].substring(6));
			}
		}
		
		// Else, we don't know what the user has done
		else
			System.err.println("Error: Couldn't read parameter" + attributes);
	}
	
	// Reads the target path
	private static void readTargetPath(Logger logger, int targetIndex, String path) {
		LogTarget logTarget = logger.targets.get(targetIndex);
		
		if(logTarget instanceof LogToFile)
			((LogToFile)logTarget).setTargetFile(path.replace("\"", ""));
		else if(logTarget instanceof LogToRotatingFile)
			((LogToRotatingFile)logTarget).setTargetFile(path.replace("\"", ""));
		else
			System.err.println("Error: Can't set a path to target: " + logTarget);
	}
}
