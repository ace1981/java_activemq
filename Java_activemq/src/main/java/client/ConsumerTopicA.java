package client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component  
public class ConsumerTopicA {  
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
    @JmsListener(destination = "mytest.topic",containerFactory = "jmsListenerContainerTopic")  
    public void receiveTopic(String text) {  
        System.out.println("ConsumerTopicA收到的报文为:"+text);  
    }  
} 