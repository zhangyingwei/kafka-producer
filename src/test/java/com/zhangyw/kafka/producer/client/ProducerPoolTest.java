package com.zhangyw.kafka.producer.client;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

public class ProducerPoolTest {
	public static void main(String[] args) {
		ProducerExecuter executer = new ProducerExecuter(ProducerPool.getIS());
		executer.send(new KeyedMessage("test",null,"ÕâÀïÊÇkafka-producer-¹þ¹þ"));
	} 
}
