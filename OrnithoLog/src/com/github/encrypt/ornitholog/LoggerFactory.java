package com.github.encrypt.ornitholog;

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
	// Logger format example: fr.esiea.maclasse:cible2.path = "truc"
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
				
				// TODO
				
			}
			
			br.close();
	
		}
		catch(FileNotFoundException e) {
			System.err.println("Impossible d'accéder au fichier properties");
		}
		catch(IOException e) {
			System.err.println("Erreur lors de la lecture du fichier properties");
		}
	}
	
}
