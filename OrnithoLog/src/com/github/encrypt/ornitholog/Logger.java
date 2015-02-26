package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.logtargets.*;
import com.github.encrypt.ornitholog.formatters.*;

import java.util.ArrayList;

public class Logger {

	ArrayList<LogTarget> targets;
	private LogLevel level;
	private LogFormatter formatter;
	private LogSaveDAO saveDAO;
	protected String className;
	
	public Logger(String className){
		this.targets = new ArrayList<LogTarget>();
		this.saveDAO = LogSaveDAO.getInstance(targets);
		this.className = className;
	}
	
	private void saveToTargets(String string){
		String tmp = new String();
		tmp = formatter.format(string);
		saveDAO.save(tmp);
	}
	
	
	public void debug(String string){
		if(level.equals(LogLevel.DEBUG)){
			saveToTargets(string);
		}
	}
	
	public void info(String string){
		if(level.equals(LogLevel.INFO)){
			saveToTargets(string);
		}
	}
	
	public void error(String string){
		if(level.equals(LogLevel.ERROR)){
			saveToTargets(string);
		}
	}
	
	public void addTarget(LogTarget target){
		targets.add(target);
	}
	
	public void setFormatter(LogFormatter formatter) {
		this.formatter = formatter;
	}
	
	public void setLevel(LogLevel level){
		this.level = level;
	}
	
	public LogLevel getLevel() {
		return this.level;
	}
}
