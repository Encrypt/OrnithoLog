package com.github.encrypt.ornitholog.logtargets;

import java.util.ArrayList;

public class LogSaveDAO {

	private static LogSaveDAO instance = null;
	private ArrayList<LogTarget> target;
	
	private LogSaveDAO(ArrayList<LogTarget> target) {
		this.target = target;
	}
	
	public static LogSaveDAO getInstance(ArrayList<LogTarget> targets) {

		if(instance == null) {
			instance = new LogSaveDAO(targets);
		}
		
		return instance;
	}
	
	public void save(String logData) {
		for(int i = 0 ; i < target.size() ; i++)
			target.get(i).save(logData);
	}
	
}
