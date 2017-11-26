package com.hujao;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import server.Producer;

@SpringBootApplication
@ComponentScan("com.hujao,server,client")
public class JavaActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaActivemqApplication.class, args);
		Destination destination_queue = new ActiveMQQueue("mytest.queue,mytest.ackqueue");
		Destination destination_topic = new ActiveMQTopic("mytest.topic");
		Producer producer = SpringUtil.getBean(Producer.class);
		for (int i = 0; i < 3; i++) {
			// spring.jms.pub-sub-domain=false
			producer.sendMessage(destination_queue, "ssss");
		}
		for (int i = 0; i < 5; i++) {
			// spring.jms.pub-sub-domain=true
			producer.sendMessage(destination_topic, "1111");
		}
		// 概要
		// Topic 主题
		// 1 概要:Publish Subscribe messaging 发布订阅消息
		// 2 有无状态:topic数据默认不落地，是无状态的。
		// 3 完整性保障:并不保证publisher发布的每条数据，Subscriber都能接受到。
		// 注：producer可以设置deliverMode为持久化方式防止丢失
		// 4
		// 消息是否会丢失:一般来说publisher发布消息到某一个topic时，只有正在监听该topic地址的sub能够接收到消息；如果没有sub在监听，该topic就丢失了。
		// 5
		// 消息发布接收策略:一对多的消息发布接收策略，监听同一个topic地址的多个sub都能收到publisher发送的消息。Sub接收完通知mq服务器
		// Queue 队列
		// 1 概要:Point-to-Point 点对点
		// 2 有无状态:Queue数据默认会在mq服务器上以文件形式保存，比如Active
		// MQ一般保存在$AMQ_HOME\data\kr-store\data下面。也可以配置成DB存储。
		// 3 完整性保障:Queue保证每条数据都能被receiver接收。
		// 4
		// 消息是否会丢失:Sender发送消息到目标Queue，receiver可以异步接收这个Queue上的消息。Queue上的消息如果暂时没有receiver来取，也不会丢失。
		// 5
		// 消息发布接收策略:一对一的消息发布接收策略，一个sender发送的消息，只能有一个receiver接收。receiver接收完后，通知mq服务器已接收，mq服务器对queue里的消息采取删除或其他操作。

		// request-response 应答模式 通过queue实现
	}
}
