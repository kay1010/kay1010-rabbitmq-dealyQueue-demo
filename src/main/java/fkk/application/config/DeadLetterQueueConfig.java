package fkk.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadLetterQueueConfig {
    //基本交换机
    @Bean
    public TopicExchange msgSendExchange(){
        // 基本交换机名称：dead.prod.exchange
        return new TopicExchange("msg.send.exchange",true,false);
    }
    //死信队列设置队列内消息过期时间，指定死信路由和死信交换机
    @Bean
    public Queue deadQueue(){
        Map<String, Object> argsMap= new HashMap();
        //死信交换机
        //死信路由
        argsMap.put("x-dead-letter-exchange","dead.exchange");
        argsMap.put("x-dead-letter-routing-key","dead.routing.key");
        //过期时间 30分钟
        argsMap.put("x-message-ttl",60000*30);
        //死信队列名称：dead.queue
        return new Queue("dead.queue",true,false,false,argsMap);
    }
    //创建基本交换机+基本路由 -> 死信队列 的绑定
    @Bean
    public Binding deadProdBinding(){

        // msgSendExchange() 基本交换机
        // msg.send.routing.key 基本路由
        // deadQueue() 死信队列
        return BindingBuilder.bind(deadQueue()).to(msgSendExchange()).with("msg.send.routing.key");
    }



    //死信交换机
    @Bean
    public TopicExchange deadExchange(){
        return new TopicExchange("dead.exchange",true,false);
    }

    //真正的消费队列(用于消费者监听消费)
    @Bean
    public Queue consumerQueue(){
        return new Queue("dead.real.queue",true);
    }



    //死信交换机+死信路由->消费队列 的绑定
    @Bean
    public Binding deadBinding(){

        //deadExchange     死信交换机
        //dead.routing.key 死信路由
        //consumerQueue        真正用于消费的队列
        return BindingBuilder.bind(consumerQueue()).to(deadExchange()).with("dead.routing.key");
    }
}
