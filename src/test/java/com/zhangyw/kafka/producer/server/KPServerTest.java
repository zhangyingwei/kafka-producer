package com.zhangyw.kafka.producer.server;

import java.util.Properties;
import java.util.UUID;

import com.zhangyw.kafka.producer.exception.KPSException;

public class KPServerTest {
	public static void main(String[] args){
//		final KPServer server = new KPServer("D:/work/zhangyingwei.com/MyEclipseWS/kafka-producer/src/main/conf.properties");
		Properties properties = new Properties();
		properties.put("producer.zookeeper.connect", "192.168.58.130:2181");
		properties.put("producer.metadata.broker.list", "192.168.58.130:9092");
		properties.put("producer.serializer.class", "kafka.serializer.StringEncoder");
		properties.put("producer.key.serializer.class", "kafka.serializer.StringEncoder");
		final KPServer server = new KPServer(properties);
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						server.send("test", UUID.randomUUID().toString());
						Thread.sleep(1000);
					} catch (KPSException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();;
	}
}
