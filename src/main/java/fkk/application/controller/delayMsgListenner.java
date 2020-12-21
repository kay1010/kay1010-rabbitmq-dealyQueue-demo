package fkk.application.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class delayMsgListenner {

    private static final Logger log= LoggerFactory.getLogger(delayMsgListenner.class);

    @RabbitListener(queues = "${mq.delay-consumerQueue}", containerFactory = "listenerContainer")
    public void consumeUserLogQueue(Message message){
        try {
            Object msg=JSON.parse(message.getBody());
            MessageProperties properties= message.getMessageProperties();
            Thread.sleep(2000);
            log.info("监听到延迟消息->【消息体body: {},消息properties: {}】",JSON.toJSONString(msg),JSON.toJSONString(properties));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
