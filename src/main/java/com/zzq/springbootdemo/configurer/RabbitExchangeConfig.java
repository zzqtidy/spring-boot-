package com.zzq.springbootdemo.configurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author TYLER
 * @title: RabbitExchangeConfig
 * @description: TODO
 * @date 2019/5/29
 */
@Configuration
public class RabbitExchangeConfig {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    public RabbitExchangeConfig() {
    }

    /*
    多播（广播）
     */
    @Bean
    FanoutExchange contractFanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange("com.exchange.fanout");
        this.rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }
    /*
    主题
     */
    @Bean
    TopicExchange contractTopicExchangeDurable() {
        TopicExchange contractTopicExchange = new TopicExchange("com.exchange.topic");
        this.rabbitAdmin.declareExchange(contractTopicExchange);
        return contractTopicExchange;
    }

    /*
    点对点
     */
    @Bean
    DirectExchange contractDirectExchange() {
        DirectExchange contractDirectExchange = new DirectExchange("com.exchange.direct");
        this.rabbitAdmin.declareExchange(contractDirectExchange);
        return contractDirectExchange;
    }

    @Bean
    Queue queueHello() {
        Queue queue = new Queue("com.queue.notify.hello", true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

}