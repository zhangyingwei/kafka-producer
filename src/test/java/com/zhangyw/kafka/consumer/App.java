package com.zhangyw.kafka.consumer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class App {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Properties conf = new Properties();
		conf.put("zookeeper.connect", "192.168.58.130:2181");
		conf.put("metadata.broker.list", "192.168.58.130:9092");
		conf.put("group.id", "haha2");
		ConsumerConnector consumer = Consumer
				.createJavaConsumerConnector(new ConsumerConfig(conf));

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put("spark-stream-kafka", 1); // 一次从主题中获取一个数据
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = messageStreams.get("spark-stream-kafka").get(0);// 获取每次接收到的这个数据
		ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
		while (iterator.hasNext()) {
			String message = new String(iterator.next().message(),"utf-8");
			System.out.println("接收到: " + message);
		}
	}

}
