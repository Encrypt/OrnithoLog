package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.formatters.*;
import com.github.encrypt.ornitholog.logtargets.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoggerFactory {

	private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();
	private static String propertiesFilePath = "ornitholog.properties";
	
	// Gets a logger, if we give it a name
	public static Logger getLogger(String className) {
		// first, we check if the logger already exists
		if (loggers.containsKey(className))
			return loggers.get(className);

		// if it does not exist, we create one, we add it to the HashMap, and we return it
		Logger logger = new Logger(className);
		loggers.put(className, logger);
		return logger;	
	}
	
	// Inits the loggers with the values of the properties file
	// Logger format example: fr.esiea.maclasse:target2.path = "/path/to/stuff"
	public static void initLoggers() {
		
		String readLine;
		
		// Reads the properties file
		try {
			BufferedReader br = new BufferedReader(new FileReader(propertiesFilePath));
	
			while ((readLine = br.readLine()) != null) {
				try {
					// Splits the line into separate strings
					String logger_classname  = readLine.split(":")[0].trim();
					String nested_attributes = readLine.split(":")[1].split("=")[0].trim();
					String value             = readLine.split("=")[1].trim();
					
					// Gets the associated logger
					Logger logger = getLogger(logger_classname);
					
					// Switch on the value of the 1st token before "="
					if(nested_attributes.equals("level"))
						readLoglevel(logger, value);

					else if(nested_attributes.equals("formatter"))
						readFormatter(logger, value);
					
					else if(nested_attributes.startsWith("target"))
						readTarget(logger, nested_attributes, value);
					
					else
						System.err.println("Error: " + nested_attributes + " isn't a supported parameter.");
				}
				catch (Exception ex) {
					System.err.println("Error: Invalid line in .properties file");
				}
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
	
	// Reads the formater
	private static void readFormatter(Logger logger, String value) {
		try {
			logger.setFormatter((LogFormatter)Class.forName("com.github.encrypt.ornitholog.formatters." + value).newInstance());
		}
		catch(ClassNotFoundException e) {
			System.err.println("Error: The LogFormatter " + value + " doesn't exist.");
		}
		catch (InstantiationException | IllegalAccessException e) {
			System.err.println("Error: The LogFormatter " + value + " couldn't be instanciated.");
		}
	}
	
	// Reads the target
	private static void readTarget(Logger logger, String nested_attributes, String value) {
		
		String dotSplit[] = nested_attributes.split("\\.");
		
		// If there are two arguments with "path" -> sets the target path
		if(dotSplit.length == 2) {
		
			int targetValue = Integer.parseInt(dotSplit[0].substring(6));
			
			if(dotSplit[1].equals("path"))
				readTargetPath(logger, targetValue - 1, value);
			if(dotSplit[1].equals("maxSize"))
				readTargetMaxFileSize(logger, targetValue, value);
		}
		
		// Else, there is no dot -> new target given in the file
		else if(dotSplit.length == 1) {
			
			try {
				int targetsSize = logger.targets.size();
				int targetValue = Integer.parseInt(nested_attributes.substring(6));
		
				// If the targets doesn't exist in the logger (n-1 loggers), creates it & adds it to the logger
				if(targetsSize == targetValue - 1) {
					
					try {
						logger.addTarget((LogTarget)Class.forName("com.github.encrypt.ornitholog.logtargets." + value).newInstance());					
					}
					catch(ClassNotFoundException e) {
						System.err.println("Error: The LogTarget " + value + " doesn't exist.");
					}
					catch (InstantiationException | IllegalAccessException e) {
						System.err.println("Error: The LogTarget " + value + " couldn't be instanciated.");
					}
				}
				
				// Else, the number of loggers is incorrect, warns the user
				else
					System.err.println("Error: Got LogTarget #" + targetValue + " while expecting #" + (targetsSize + 1));
				
			}
			catch(NumberFormatException e) {
				System.err.println("Error: Expected target<value> but got: target" + nested_attributes.substring(6));
			}
		}
		
		// Else, we don't know what the user has done
		else
			System.err.println("Error: Bad parameter");
	}
	
	// Reads the target path
	private static void readTargetPath(Logger logger, int targetIndex, String path) {
		System.out.println("# of targets: " + logger.targets.size());
		LogTarget logTarget = logger.targets.get(targetIndex);
		
		if(logTarget instanceof LogToFile)
			((LogToFile)logTarget).setTargetFile(path.replace("\"", ""));
		else if(logTarget instanceof LogToRotatingFile)
			((LogToRotatingFile)logTarget).setTargetFile(path.replace("\"", ""));
		else
			System.err.println("Error: Can't set a path to target: " + logTarget);
	}
	
	// Reads the target maxFileSize
	private static void readTargetMaxFileSize(Logger logger, int targetIndex, String doubleSize) {
		LogTarget logTarget = logger.targets.get(targetIndex);
		
		if(logTarget instanceof LogToRotatingFile)
			((LogToRotatingFile)logTarget).setMaxFileSize(Double.parseDouble(doubleSize));
		else
			System.err.println("Error: Can't set maxFileSize " + doubleSize + " to target: " + logTarget);
	}
	
	// the following function is useful only for unit testing purposes
	protected static void setPropertiesFilePath(String propertiesFilePath) {
		LoggerFactory.propertiesFilePath = propertiesFilePath;
	}
}
