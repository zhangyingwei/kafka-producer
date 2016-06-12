package com.zhangyw.kafka.producer.exception;

public class KPSConfigNotFoundException extends Exception{
	public KPSConfigNotFoundException(){
		super();
	}
	public KPSConfigNotFoundException(Exception e){
		super(e);
	}
	public KPSConfigNotFoundException(String message){
		super(message);
	}
	public KPSConfigNotFoundException(String message,Exception e){
		super(message,e);
	}
}
