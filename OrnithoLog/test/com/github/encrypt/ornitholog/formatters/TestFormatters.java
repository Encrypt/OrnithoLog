package com.github.encrypt.ornitholog.formatters;

import com.github.encrypt.ornitholog.LogLevel;
import com.github.encrypt.ornitholog.Logger;
import com.github.encrypt.ornitholog.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class TestFormatters {
	
	private String className = "com.example.Example";
	private LogFormatter formatter;
	private Logger logger;
	
	// we create a Logger and we set his formatter
	@Before
	public void Before() {
		this.logger = LoggerFactory.getLogger(this.className);
		logger.setLevel(LogLevel.WARN);
		this.formatter = new ClassFormatter(new DateFormatter(new LevelFormatter(logger)), logger);
	}
	
	// we check that the formatting is correct
	@Test
	public void testFormatting() {
		String message = "test formatting";
		String message_formatted = this.formatter.format(message);
		//System.out.println(message_formatted);  // debug
		
		// we check that the formatted message contains the original message
		if (! message_formatted.contains(message))
			fail("Test failed: formatted message does not contain original message");
		
		// we check that the formatted message contains the class name
		if (! message_formatted.contains(className))
			fail("Test failed: formatted message with CLassFormatter does not contain class name");
		
		// we check that the formatted message contains the log level
		if (! message_formatted.contains(this.logger.getLevel().toString()))
			fail("Test failed: formatted message does not contain log level");
	}

}
