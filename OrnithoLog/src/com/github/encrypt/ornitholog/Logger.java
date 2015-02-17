package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.logtargets.*;
import com.github.encrypt.ornitholog.formatters.*;
import java.util.ArrayList;

public class Logger {

	private ArrayList<LogTarget> target;
	private String confFile;
	private LogLevel level;
	private LogFormatter formatter;
	//LogSaveDAO saveDAO;
	
	public Logger(){
		this.target = new ArrayList<LogTarget>();
	}
	
	public void debug(String string){
		
	}
	
	public void info(String string){
		
	}
	
	public void error(String string){
		
	}
	
	public void addTarget(LogTarget target){
		
	}
	
	public LogLevel getLevel() {
		return this.level;
	}
	public void setLevel(LogLevel level){
		this.level = level;
	}
	
	
}
