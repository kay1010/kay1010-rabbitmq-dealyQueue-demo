package fkk.application.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("sendMsg")
    public void sendMag() throws Exception {
        Map<String,Object> msg=new HashMap<>();
        msg.put("code",0);
        msg.put("data","40s延迟消息");
        rabbitTemplate.convertAndSend("msg.send.exchange", "msg.send.routing.key", JSON.toJSONString(msg), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties=message.getMessageProperties();
                properties.setExpiration("40000");
                properties.setContentType("fkk/application/json");
                return message;
            }
        });
    }

    @GetMapping("sendMsg1")
    public void sendMag1() throws Exception {
        Map<String,Object> msg=new HashMap<>();
        msg.put("code",0);
        msg.put("data","20s延迟消息");
        rabbitTemplate.convertAndSend("msg.send.exchange", "msg.send.routing.key", JSON.toJSONString(msg), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties=message.getMessageProperties();
                properties.setExpiration("20000");
                properties.setContentType("fkk/application/json");
                return message;
            }
        });
    }
}
