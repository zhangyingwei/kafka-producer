package com.zhangyw.kafka.producer.main;

import java.util.Arrays;
import java.util.UUID;

import com.zhangyw.kafka.producer.exception.KPSConfigNotFoundException;
import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.KPServer;
import com.zhangyw.kafka.producer.utils.PropertiesUtil;

public class App {
	public static void main(String[] args) throws KPSConfigNotFoundException {
		System.out.println(Arrays.toString(args));
		final KPServer server = new KPServer(args[0]);
//		final KPServer server = new KPServer("D:/work/zhangyingwei.com/MyEclipseWS/kafka-producer/src/main/resources/conf.properties");
		Integer arg = Integer.parseInt(args[1]);
		final int timers = arg==null?1000:arg;
//		final int timers = 5000;
		final String topic = PropertiesUtil.getIS().getConf("topic");
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						server.send(topic, "我是UUID，我的值是->"+UUID.randomUUID().toString());
						Thread.sleep(timers);
					} catch (KPSException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
