package com.github.encrypt.ornitholog;

import java.util.ArrayList;

public class LoggerFactory extends Logger {

	private ArrayList<Logger> loggers;
	
	public LoggerFactory(String className) {
		super(className);
	}
	
	public Logger getLogger(String maClasse){
		for(Logger logger : loggers){
			
			if(logger.className.equals(maClasse)){
				return logger;
			}
		}
		Logger logger = new Logger(maClasse);
		return logger;	
	}
	
}
