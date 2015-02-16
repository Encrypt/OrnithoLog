package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.logtargets.*;
import java.util.ArrayList;

public class Logger {

	private ArrayList<LogTarget> target;
	private String confFile;
	private LogLevel level;
	private LogFormatter formatter;
	LogSaveDAO saveDAO;
	
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
	
	public void setLevel(LogLevel level){
		
	}
	
	
}
