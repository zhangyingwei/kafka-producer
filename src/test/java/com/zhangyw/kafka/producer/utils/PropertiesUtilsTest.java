package com.zhangyw.kafka.producer.utils;

import java.util.Properties;

import com.zhangyw.kafka.producer.client.ProducerClient;
import com.zhangyw.kafka.producer.exception.KPSConfigNotFoundException;

public class PropertiesUtilsTest {
	public static void main(String[] args) throws KPSConfigNotFoundException {
		String path = System.getProperty("user.dir")+"\\src\\main\\"+"resources\\conf.properties";
		PropertiesUtil util = PropertiesUtil.getIS().load(path);
		System.out.println(util.getConf(ProducerClient.ZOOKEEPER_CONNECT));
	}
}
