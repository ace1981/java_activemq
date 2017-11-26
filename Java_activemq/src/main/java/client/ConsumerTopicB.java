package client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerTopicB {
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.topic")
	public void receiveTopic(String text) {
		System.out.println("ConsumerTopicB收到的报文为:" + text);
	}
}