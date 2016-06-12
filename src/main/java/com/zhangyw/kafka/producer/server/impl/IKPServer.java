package com.zhangyw.kafka.producer.server.impl;

import java.util.List;

import com.zhangyw.kafka.producer.exception.KPSException;

public interface IKPServer {
	public void send(String topic,String message) throws KPSException;
	public void send(List<String> topics,String message) throws KPSException;
	public void send(String topic,List<String> messages) throws KPSException;
}
