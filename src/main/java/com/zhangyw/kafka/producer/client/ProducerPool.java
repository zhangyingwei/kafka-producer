package com.zhangyw.kafka.producer.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zhangyw.kafka.producer.utils.PropertiesUtil;

import kafka.javaapi.producer.Producer;

@SuppressWarnings("deprecation")
public class ProducerPool {
	Properties conf = null;
	Properties clientConf = null;
	private ProducerClient client = ProducerClient.getIS();
	private Integer reInitSize = 10;
	private Integer InitSize = 20;
	@SuppressWarnings("rawtypes")
	List<Producer> waitPool;
	@SuppressWarnings("rawtypes")
	List<Producer> wokePool;
	@SuppressWarnings("rawtypes")
	private ProducerPool(){
		conf = new Properties();
		this.waitPool = new ArrayList<Producer>();
		this.wokePool = new ArrayList<Producer>();
	}
	private static class ProducerPoolHandler{
		private static ProducerPool pool = new ProducerPool();
	}
	public static ProducerPool getIS(){
		return ProducerPoolHandler.pool;
	}
	public void initConf(){
		String confPath = System.getProperty("user.dir")+"\\src\\main\\resources\\conf.properties";
		this.initConf(confPath);
	}
	public void initConf(String path){
		this.conf = PropertiesUtil.getIS().load(path).getProp();
		this.InitSize = conf.getProperty("InitSize")==null?this.InitSize:Integer.parseInt(conf.getProperty("InitSize"));
		this.reInitSize = conf.getProperty("reInitSize")==null?this.reInitSize:Integer.parseInt(conf.getProperty("reInitSize"));
		this.clientConf = PropertiesUtil.getIS().load(conf.getProperty("producer.conf")).getProp();
		this.client.initConf(clientConf);
		this.initPool();
	}
	@SuppressWarnings("rawtypes")
	public synchronized Producer getProducer(){
		if(this.waitPool.size()==0){
			this.reInitPool();
		}
		Producer p = waitPool.remove(waitPool.size()-1);
		this.wokePool.add(p);
		return p;
	}
	@SuppressWarnings("rawtypes")
	public synchronized void returnProducer(Producer pro){
		this.waitPool.add(pro);
		this.wokePool.remove(pro);
		this.closeProducers();
	}
	private void closeProducers(){
		if(this.overMax()){
			while(waitPool.size()>=InitSize){
				this.closeProducer(waitPool.remove(waitPool.size()-1));
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	private void closeProducer(Producer pro){
		if(pro!=null){
			pro.close();
		}
	}
	private Boolean overMax(){
		if(this.waitPool.size()>=this.InitSize){
			return true;
		}
		return false;
	}
	private synchronized void reInitPool(){
		for(int i = 0;i<reInitSize;i++){
			this.waitPool.add(this.client.createProducer());
		}
	}
	private synchronized void initPool(){
		for(int i = 0;i<InitSize;i++){
			this.waitPool.add(this.client.createProducer());
		}
	}
}
