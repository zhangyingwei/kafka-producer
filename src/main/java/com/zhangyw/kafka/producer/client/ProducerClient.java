package com.zhangyw.kafka.producer.client;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

public class ProducerClient {
	Properties properties = null;
	public static final String ZOOKEEPER_CONNECT="zookeeper.connect";
	public static final String METADATA_BROKER_LIST="metadata.broker.list";
	public static final String SERIALIZER_CLASS="serializer.class";
	public static final String KEY_SERIALIZER_CLASS="key.serializer.class";
	
	private ProducerClient(){}
	
	private static class ProducerClientHandler{
		private static ProducerClient client = new ProducerClient();
	}
	public static ProducerClient getIS(){
		return ProducerClientHandler.client;
	}
	public void initConf(Properties conf){
		this.properties = conf;
	}
	public Producer createProducer(){
		return new Producer(new ProducerConfig(properties));
	}
}
