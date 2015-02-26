package com.github.encrypt.ornitholog;

import com.github.encrypt.ornitholog.logtargets.*;
import com.github.encrypt.ornitholog.formatters.*;

import java.util.ArrayList;

public class Logger {

	protected ArrayList<LogTarget> targets;
	protected LogLevel level = LogLevel.DEBUG;  // DEBUG is default log level
	private LogFormatter formatter;
	private LogSaveDAO saveDAO;
	protected String className;
	
	public Logger(String className){
		this.targets = new ArrayList<LogTarget>();
		this.saveDAO = LogSaveDAO.getInstance(targets);
		this.className = className;
	}
	
	public void debug(String message){
		if(level.ordinal() >= LogLevel.DEBUG.ordinal())
			saveDAO.save(this.formatter.format(message));
	}
	
	public void info(String message){
		if(level.ordinal() >= LogLevel.INFO.ordinal())
			saveDAO.save(this.formatter.format(message));
	}
	
	public void warn(String message){
		if(level.ordinal() >= LogLevel.WARN.ordinal())
			saveDAO.save(this.formatter.format(message));
	}
	
	public void error(String message){
		if(level.ordinal() >= LogLevel.ERROR.ordinal())
			saveDAO.save(this.formatter.format(message));
	}
	
	public void addTarget(LogTarget target){
		targets.add(target);
	}
	
	public void setFormatter(LogFormatter formatter) {
		this.formatter = formatter;
	}
	
	public LogFormatter getFormatter() {
		return this.formatter;
	}
	
	public void setLevel(LogLevel level){
		this.level = level;
	}
	
	public LogLevel getLevel() {
		return this.level;
	}
	
	public String getClassName() {
		return this.className;
	}
}
