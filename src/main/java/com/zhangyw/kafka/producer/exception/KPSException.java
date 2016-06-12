package com.zhangyw.kafka.producer.exception;

public class KPSException extends Exception{
	public KPSException(){
		super();
	}
	public KPSException(Exception e){
		super(e);
	}
	public KPSException(String message){
		super(message);
	}
	public KPSException(String message,Exception e){
		super(message,e);
	}
}
