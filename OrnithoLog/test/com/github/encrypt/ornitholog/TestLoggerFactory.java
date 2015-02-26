package com.github.encrypt.ornitholog;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestLoggerFactory {
	
	
	// we test that we can get the logger for a specific class
	@Test
	public void testGetLogger() {
		Logger logger = LoggerFactory.getLogger("com.example.Example");
		assertTrue(logger instanceof Logger);
	}
	
	@Test
	public void testPropertiesFile() {
		LoggerFactory.setPropertiesFilePath("test/com/github/encrypt/ornitholog/ornitholog.properties");
		LoggerFactory.initLoggers();
		Logger logger = LoggerFactory.getLogger("com.myProject");
		assertTrue(logger.level== LogLevel.DEBUG);
	}

}
