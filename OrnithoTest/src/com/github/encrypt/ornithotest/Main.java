package com.github.encrypt.ornithotest;

import com.github.encrypt.ornitholog.*;
import com.github.encrypt.ornitholog.formatters.*;
import com.github.encrypt.ornitholog.logtargets.*;

public class Main {

	public static void main(String[] args) {
		
		// Creates the logger
		Logger logger = LoggerFactory.getLogger("Main");

		// Sets the attributes
		logger.setLevel(LogLevel.DEBUG);
		logger.addTarget(new LogToConsole());
		logger.setFormatter(new DateFormatter());
		
		// Logs data
		logger.debug("Test 1");
		logger.debug("Test 2");
		
	}

}