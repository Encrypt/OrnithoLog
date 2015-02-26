package com.github.encrypt.ornitholog.logtargets;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.encrypt.ornitholog.LogLevel;
import com.github.encrypt.ornitholog.Logger;
import com.github.encrypt.ornitholog.LoggerFactory;
import com.github.encrypt.ornitholog.formatters.DateFormatter;

public class TestLogToFile {

	private Logger logger;
	private String myPath = "/Users/patouz/Documents/TestLogToFile.txt";
	
	
	@Before
	public void before() {
		this.logger = LoggerFactory.getLogger("com.example.Examples");
		logger.addTarget(new LogToFile(myPath));
		logger.setFormatter(new DateFormatter());
		logger.setLevel(LogLevel.DEBUG);
		logger.debug("Ceci est un test!");
		logger.debug("Ceci est le 2e test!");
	}
	
	@Test
	public void fileExists() {
		File file = new File(myPath);
		assertTrue(file.exists());
	}
	
	@Test
	public void fileContainsNumberOfLines(){
		try {
			List<String> lines = Files.readAllLines(Paths.get(myPath), Charset.defaultCharset());
			assertEquals(2,lines.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Test Failed : File doesn't exists.");
		}
	}
	
	
	@After
	public void clean(){
		File file = new File(myPath);
		if(file.exists()){
			file.delete();
		}
	}
	

}
