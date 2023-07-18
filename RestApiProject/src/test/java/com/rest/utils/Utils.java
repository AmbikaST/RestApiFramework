package com.rest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.rest.constants.SourcePath;


public class Utils {
	private Properties prop = null;
	private static Utils ut = null;
	
	public Utils() {
		
	}
	
	public static Utils getInstance() {
		if(ut == null) {
			ut = new Utils();
		}
		return ut;
	}
	public Properties loadFile() {
		prop = new Properties();
		String propertyFilePath = SourcePath.USER_DATA_PROPERTIES;
		FileInputStream file = null;
		try {
			file = new FileInputStream(propertyFilePath);
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return prop;
		
	}

	public String getProperty(String key) { 
		
		String value = prop.getProperty(key);
		return value;
		
	}
}
