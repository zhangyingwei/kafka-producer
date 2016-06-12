package com.zhangyw.kafka.producer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.zhangyw.kafka.producer.exception.KPSConfigNotFoundException;

public class PropertiesUtil {
	private Properties prop = null;
	private PropertiesUtil(){
		this.prop = new Properties();
	}
	private static class PropertiesUtilHandler{
		static PropertiesUtil conf = new PropertiesUtil();
	}
	public static PropertiesUtil getIS(){
		return PropertiesUtilHandler.conf;
	}
	public PropertiesUtil load(){
		String path = System.getProperty("user.dir")+"\\src\\main\\"+"resources\\conf.properties";
		try {
			this.prop.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	public PropertiesUtil load(String path){
		try {
			this.prop.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	public PropertiesUtil load(Properties prop){
		this.prop = prop;
		return this;
	}
	public String getConf(String key) throws KPSConfigNotFoundException{
		if(!this.prop.containsKey(key)){
			throw new KPSConfigNotFoundException(key+"(≈‰÷√≤ª¥Ê‘⁄)");
		}
		return this.prop.getProperty(key);
	}
	public Properties getProp() {
		return prop;
	}
}
