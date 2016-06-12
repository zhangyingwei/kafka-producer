package com.zhangyw.kafka.producer.server.impl;

import com.zhangyw.kafka.producer.exception.KPSException;

public interface IKPServer {
	public void start() throws KPSException;
	public void send(String topic,String message) throws KPSException;
}
