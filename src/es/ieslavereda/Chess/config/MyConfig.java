package es.ieslavereda.Chess.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {
	
	private static MyConfig instancia = new MyConfig();
	
	private String defaultFile = "default.properties";
	private String appFile = "app.properties";
	private Properties properties;
	
	private MyConfig() {
		
		Properties defaultProperties = new Properties();
		
		try(FileInputStream fis = new FileInputStream(new File(defaultFile))){
			
			defaultProperties.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		properties = new Properties(defaultProperties);
		
		try(FileInputStream fis = new FileInputStream(new File(appFile))){
			
			properties.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public static MyConfig getInstancia() {
		return instancia;
	}
	
	public int getWhiteCellColor() {
		return Integer.parseInt(properties.getProperty("color_celda_blanca"));
	}
	
	public int getBlackCellColor() {
		return Integer.parseInt(properties.getProperty("color_celda_negra"));
	}

}







