package com.github.encrypt.ornitholog;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import com.github.encrypt.ornitholog.logtargets.LogToConsole;
import com.github.encrypt.ornitholog.logtargets.LogToFile;


public class TestLogger {
	
	private Logger logger;
	
	@Before
	public void before() {
		this.logger = LoggerFactory.getLogger("com.example.TestLogger");
	}

	// test that the className attribute is not null
	@Test
	public void testClassName() {
		assertNotNull(logger.className);
	}
	
	// we test that the targets are properly sets
	@Test
	public void testTargets() {
		this.logger.addTarget(new LogToConsole());
		this.logger.addTarget(new LogToFile(".testLogToFile.txt"));
		assertEquals(2, this.logger.targets.size());
	}
	
	// we test that the log level is properly set
	@Test
	public void testLogLevel() {
		this.logger.setLevel(LogLevel.WARN);
		assertEquals(LogLevel.WARN.ordinal(), this.logger.level.ordinal());
	}

}
