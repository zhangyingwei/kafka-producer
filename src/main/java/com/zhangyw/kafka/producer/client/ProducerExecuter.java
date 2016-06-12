package com.zhangyw.kafka.producer.client;

import java.util.List;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

public class ProducerExecuter {
	Producer worker = null;
	ProducerPool pool = null;
	public ProducerExecuter(ProducerPool pool){
		this.pool = pool;
	}
	public void send(List messages){
		this.worker = this.pool.getProducer();
		this.worker.send(messages);
		this.pool.returnProducer(worker);
	}
	public void send(KeyedMessage message){
		this.worker = this.pool.getProducer();
		this.worker.send(message);
		this.pool.returnProducer(worker);
	}
}
