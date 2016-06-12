package com.zhangyw.kafka.producer.main;

import java.util.UUID;

import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.KPServer;

public class App {
	public static void main(String[] args){
		final KPServer server = new KPServer();
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
