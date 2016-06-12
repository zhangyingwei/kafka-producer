package com.zhangyw.kafka.producer.server;

import java.util.List;

import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.impl.IKPServer;

public class KPServer implements IKPServer{

	public void start() throws KPSException {
		// TODO Auto-generated method stub
	}

	public void send(String topic, String message) throws KPSException {
		// TODO Auto-generated method stub
	}

	public void send(List<String> topics, String message) throws KPSException {
		// TODO Auto-generated method stub
	}

	public void send(String topic, List<String> messages) throws KPSException {
		// TODO Auto-generated method stub
	}
	
}
