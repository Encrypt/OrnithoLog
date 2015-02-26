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
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.encrypt.ornitholog.LogLevel;
import com.github.encrypt.ornitholog.Logger;
import com.github.encrypt.ornitholog.LoggerFactory;
import com.github.encrypt.ornitholog.formatters.DateFormatter;
import com.github.encrypt.ornitholog.formatters.LevelFormatter;

public class TestLogToRotatingFile {

	private static boolean setupDone = false;  // because we need to setup the tests only once
	private static int testsDone = 0;   // the number of tests done
	
	private Logger logger;
	private String myPath = "TestLogToRotatingFile.txt";
	
	
	@Before
	public void before() {
		if (!this.setupDone) {
			this.logger = LoggerFactory.getLogger("com.example.TestLogToRotatingFile");
			logger.addTarget(new LogToRotatingFile(myPath, 1024000.0));
			logger.setFormatter(new LevelFormatter(logger));
			logger.setLevel(LogLevel.ERROR);
			logger.debug("Ceci est un test d'erreur!");
			logger.debug("Ceci est le 2e test d'erreur !");
			this.setupDone = true;
		}
	}
	
	@Test
	public void fileExists() {
		File file = new File(myPath);
		assertTrue(file.exists());
		
		testsDone++;
	}
	
	@Test
	public void fileContainsNumberOfLines(){
		try {
			List<String> lines = Files.readAllLines(Paths.get(myPath), Charset.defaultCharset());
			assertEquals(2,lines.size());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Test Failed : File doesn't exists.");
		}
		
		testsDone++;
	}
	
	
	@After
	public void clean(){
		// we should delete the file only when all the tests are done
		if (testsDone == 2) {
			File file = new File(myPath);
			if(file.exists()){
				file.delete();
			}
		}
	}
}
