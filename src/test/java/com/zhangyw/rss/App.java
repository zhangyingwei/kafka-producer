package com.zhangyw.rss;

import net.sf.json.JSONObject;

import com.zhangyingwei.rssreader4j.handler.RssHandler;
import com.zhangyingwei.rssreader4j.model.RssModel;
import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.KPServer;

public class App {
	public static void main(String[] args) throws InterruptedException {
		String[] urls = {"https://luolei.org/rss/","http://barretlee.com/rss2.xml","http://www.aliog.com/feed/","http://www.iteblog.com/feed"};
		KPServer server = new KPServer("D:/work/zhangyingwei.com/MyEclipseWS/kafka-producer/src/main/resources/conf.properties");
		for(String url:urls){
			RssModel rssmodel = RssHandler.buildRssModel(url);
			JSONObject json = JSONObject.fromObject(rssmodel);
			try {
				server.send("test", json.toString());
			} catch (KPSException e) {
				e.printStackTrace();
			}
			Thread.sleep(2000);
		}
	}
}
