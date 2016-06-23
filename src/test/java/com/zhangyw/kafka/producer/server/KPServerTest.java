package com.zhangyw.kafka.producer.server;

import java.util.UUID;

import com.zhangyw.kafka.producer.exception.KPSException;

public class KPServerTest {
	public static void main(String[] args){
		final KPServer server = new KPServer("D:/work/zhangyingwei.com/MyEclipseWS/kafka-producer/src/main/conf.properties");
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
