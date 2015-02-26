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

}
