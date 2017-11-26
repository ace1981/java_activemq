package client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;  
  
// 双向队列 实现应答模式
@Component  
public class ConsumerAckQueue {  
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息   
    @JmsListener(destination = "mytest.ackqueue",containerFactory = "jmsListenerContainerQueue")  
    @SendTo("mytest.ackreplyqueue")
    public String receiveAckQueue(String text) {  
        System.out.println("ConsumerAckQueue收到的报文为:"+text); 
        return "return message "+text;
    }
}