package client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;  
  
@Component  
public class ConsumerQueue {  
        // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
    @JmsListener(destination = "mytest.queue",containerFactory = "jmsListenerContainerQueue")  
    public void receiveQueue(String text) {  
        System.out.println("ConsumerQueue收到的报文为:"+text);  
    }  
}